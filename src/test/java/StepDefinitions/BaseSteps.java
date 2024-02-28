package StepDefinitions;

import POM.GeneralPOM;
import POM.LoginPOM;
import POM.OrderPOM;
import POM.SalePOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

import static POM.ElementsMap.elementsMap;

public class BaseSteps extends BaseMethods{
    GeneralPOM generalPOM = GeneralPOM.getInstance();
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
        WebElement myElement = driver.findElement(elementsMap.get(element));
        try {
            if (myElement.isDisplayed()) myElement.click();
            else {
                moveToElement(myElement);
                myElement.click();
            }
        }catch (ElementNotInteractableException e){
            try {
                if (myElement.isDisplayed()) clickWithAction(myElement);
                else {
                    moveToElement(myElement);
                    clickWithAction(myElement);
                }
            }catch (ElementNotInteractableException e2){
                if (myElement.isDisplayed()) getJsExecutor().executeScript("arguments[0].click();", myElement);
                else {
                    moveToElement(myElement);
                    getJsExecutor().executeScript("arguments[0].click();", myElement);
                }
            }
        }
    }

    @And("User add {string} product")
    public void userAddProduct(String product) {
        if (!product.isEmpty()) {
            driver.findElement(generalPOM.getProductNameField()).sendKeys(product);
            driver.findElement(generalPOM.getProductSearchBtn()).click();
            int productInStockIndex = 0;
            for (int i = 1; i < 11; i++) {
                String[] priceSplit = driver.findElement(generalPOM.getProductPrice(i)).getText().split("\\.");
                int price = Integer.parseInt(priceSplit[0]);
                if (price > 0
                        && Integer.parseInt(driver.findElement(generalPOM.getProductCount(i)).getText()) > 0) {
                    productInStockIndex = i;
                    break;
                }

            }
            driver.findElement(generalPOM.getAddProductBtn(productInStockIndex)).click();
        }
    }

    @Then("User should get {string} message")
    public void userShouldGetMessageInNewOrderPage(String expectedError) {
        if (expectedError.equals("Məhsul seçilməyib.")){
            WebElement productErrorMessage = driver.findElement(generalPOM.getProductEmptyErrorMessage());
            waitVisibilityElement(productErrorMessage, 5);
            Assert.assertEquals(productErrorMessage.getText(), expectedError);
        }else {
            WebElement errorMessage = driver.findElement(generalPOM.getErrorMessage());
            waitVisibilityElement(errorMessage, 5);
            Assert.assertEquals(errorMessage.getText(), expectedError);
        }
    }

    @And("User add {string} service")
    public void userAddService(String service) {
        driver.findElement(generalPOM.getServiceSearchField()).sendKeys(service);
        driver.findElement(generalPOM.getAddServiceBtn()).click();
    }

    @And("User add Bundle")
    public void userAddBundle() {
        waitVisibilityElement(generalPOM.getAddBundleBtn(), 10);
        driver.findElement(generalPOM.getAddBundleBtn()).click();
    }

    @Then("Total amount should be sum of all prices")
    public void totalAmountShouldBeSumOfAllPrices() {
        waitVisibilityElement(driver.findElements(generalPOM.getAddedProductsPrices()), 5);
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
}
