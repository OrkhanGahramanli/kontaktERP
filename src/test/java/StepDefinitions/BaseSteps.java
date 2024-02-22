package StepDefinitions;

import POM.GeneralPOM;
import POM.LoginPOM;
import POM.OrderPOM;
import POM.SalePOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
        waitVisibilityLocator(elementsMap.get(element),10);
        driver.findElement(elementsMap.get(element)).click();
    }

    @And("User clicks {string} page link")
    public void userClicksPageLink(String element) {
        driver.findElement(elementsMap.get(element)).click();
    }

    @And("User add {string} product")
    public void userAddProduct(String product) {
        driver.findElement(generalPOM.getProductAreaExpandBtn()).click();
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
}
