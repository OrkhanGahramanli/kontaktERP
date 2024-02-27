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
        if (myElement.isDisplayed()) myElement.click();
        else {
            moveToElement(myElement);
            myElement.click();
        }
    }

    @And("User add {string} product")
    public void userAddProduct(String product) {
        driver.findElement(generalPOM.getProductNameField()).sendKeys(product);
        if (!product.isEmpty()) {
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
}
