package stepDefinitions;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import pom.GeneralPOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.testng.Assert;
import pom.SalePOM;

import java.util.List;

import static pom.ElementsMap.elementsMap;

public class BaseSteps extends BaseMethods{
    GeneralPOM generalPOM;

    public BaseSteps(){
        generalPOM = GeneralPOM.getInstance();
    }
    @Getter
    private static ThreadLocal<String> saleInvoiceNumber = new ThreadLocal<>();

    @Given("User is in {string}")
    public void UserIsIn(String arg0){
    }
    @When("User selects {string} store")
    public void userSelectsStore(String storeName) {
        WebElement store = driver.findElement(generalPOM.getStoreSelect());
        Select select = new Select(store);
        select.selectByVisibleText(storeName);
    }
    @And("User clicks {string} module link")
    public void UserClicksMenu(String element) {
        waitVisibilityElement(elementsMap.get(element),10);
        driver.findElement(elementsMap.get(element)).click();
    }

    @And("User clicks {string} page link")
    public void userClicksPageLink(String pageLink) {
        WebElement element = driver.findElement(elementsMap.get(pageLink));
        element.click();
    }

    @And("User clicks {string} button")
    public void userClicksButton(String element) {
        if (!element.isEmpty()){
            WebElement myElement = driver.findElement(elementsMap.get(element));
            try {
                if (myElement.isDisplayed()) myElement.click();
                else {
                    moveToElement(myElement);
                    myElement.click();
                }
            } catch (Exception e) {
                try {
                    if (myElement.isDisplayed()) clickWithAction(myElement);
                    else {
                        moveToElement(myElement);
                        clickWithAction(myElement);
                    }
                } catch (Exception e2) {
                    if (myElement.isDisplayed()) getJsExecutor().executeScript("arguments[0].click();", myElement);
                    else {
                        moveToElement(myElement);
                        getJsExecutor().executeScript("arguments[0].click();", myElement);
                    }
                }
            }
        }
    }

    @And("User selects {string} option from {string}")
    public void userSelectsOptionFrom(String text, String element) {
        if (!text.isEmpty()) {
            waitVisibilityElement(elementsMap.get(element), 10);
            try {
                selectVisibleText(driver.findElement(elementsMap.get(element)), text);
            } catch (UnexpectedTagNameException u) {
                driver.findElement(elementsMap.get(element)).sendKeys(text);
                selectElementByText(text).click();
            }
        }
    }

    @When("User fills {string} in {string} input field")
    public void userFillsInputField(String text, String element){
        if (!text.isEmpty()) {
            waitVisibilityElement(elementsMap.get(element), 10);
            if (driver.findElement(elementsMap.get(element)).getAttribute("class").contains("inputmask")){
                driver.findElement(elementsMap.get(element)).click();
                driver.findElement(elementsMap.get(element)).clear();
            }
            driver.findElement(elementsMap.get(element)).sendKeys(text);
        }
    }

    @Then("User should get {string} message")
    public void userShouldGetMessageInNewOrderPage(String message) {;
        if (message.equals("Məhsul seçilməyib.")){
            WebElement productErrorMessage = driver.findElement(generalPOM.getProductEmptyErrorMessage());
            waitVisibilityElement(productErrorMessage, 5);
            Assert.assertEquals(productErrorMessage.getText(), message);
        } else if (message.equals("satıcı kodu seçilməyib.")) {
            WebElement errorMessage = driver.findElement(generalPOM.getPopUpMessage());
            waitVisibilityElement(errorMessage, 5);
            Assert.assertTrue(errorMessage.getText().contains(message));
        } else {
            WebElement messageElement = driver.findElement(generalPOM.getPopUpMessage());
            waitVisibilityElement(messageElement, 5);
            Assert.assertEquals(messageElement.getText(), message);
        }
    }

    @And("User add {string} service")
    public void userAddService(String service) {
        driver.findElement(generalPOM.getServiceSearchField()).sendKeys(service);
        driver.findElement(generalPOM.getAddServiceBtn()).click();
    }

    @Then("Total amount should be sum of all prices")
    public void totalAmountShouldBeSumOfAllPrices() throws InterruptedException {
        Thread.sleep(1000);
        List<WebElement> productsPrices = driver.findElements(generalPOM.getAddedProductsPrices());
        List<WebElement> productsDiscounts = driver.findElements(generalPOM.getAddedProductsDiscounts());

        double productsPricesSum = 0.00d;
        for (WebElement element : productsPrices){
            productsPricesSum += Double.parseDouble(element.getAttribute("value"));
        }
        double productsDiscountsSum = 0.00d;
        for (WebElement element : productsDiscounts){
            if (!element.getAttribute("value").isEmpty()) productsDiscountsSum += Double.parseDouble(element.getAttribute("value"));
        }

        WebElement productsAmount = driver.findElement(generalPOM.getProductsAmount());
        WebElement productsDiscount = driver.findElement(generalPOM.getProductsDiscount());
        WebElement productsTotalAmount = driver.findElement(generalPOM.getProductsTotalAmount());

        Assert.assertEquals(Double.parseDouble(productsAmount.getText()), Math.round(productsPricesSum*100.0)/100.0);
        Assert.assertEquals(Double.parseDouble(productsDiscount.getText()), Math.round(productsDiscountsSum*100.0)/100.0);
        Assert.assertEquals(Double.parseDouble(productsTotalAmount.getText()), Math.round((productsPricesSum-productsDiscountsSum)*100.0)/100.0);
    }

    @Then("Products should be displayed in bundle")
    public void productsShouldBeDisplayedInBundle() {
        waitVisibilityElement(generalPOM.getProductInBundle(), 10);
        Assert.assertFalse(driver.findElement(generalPOM.getProductInBundle()).getText().isEmpty());
    }

    @And("User search and add {string} product")
    public void userSearchAndAddProduct(String product) {
        if (!product.isEmpty()){
            driver.findElement(elementsMap.get("productSearchBtn")).click();
            waitVisibilityElement(elementsMap.get("addProductBtn"), 10);
            driver.findElement(elementsMap.get("addProductBtn")).click();

        }
    }

    @And("User clicks {string} button {int} times")
    public void userClicksButtonTimes(String element, int num) {
        for (int i=0; i<num; i++){
            driver.findElement(elementsMap.get(element)).click();
        }
    }

    @And("User's waiting visibility of {string} element for {int} seconds")
    public void userSWaitingElementForSeconds(String element, int time) {
       waitVisibilityElement(elementsMap.get(element), time);
    }

    @And("Wait {int} second for an element")
    public void waitingForAnElementUsingThreadSleep(int time) throws InterruptedException {
        Thread.sleep(time * 1000L);
    }

    @And("User clear {string} input field")
    public void userClearInputField(String element) {
        driver.findElement(elementsMap.get(element)).clear();
    }

    @And("User's waiting {int} seconds for visibility {string} message")
    public void userSWaitingSecondsForVisibilityText(int time, String text) {
        waitTextMessage(generalPOM.getPopUpMessage(), text, time);
    }

    @And("User takes sale invoice number")
    public void userTakes() {
        saleInvoiceNumber.set(driver.findElement(generalPOM.getInvoiceNumber()).getAttribute("value"));
    }
}
