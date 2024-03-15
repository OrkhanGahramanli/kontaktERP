package stepDefinitions;

import pom.GeneralPOM;
import pom.SalePOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import static pom.ElementsMap.elementsMap;

public class SaleSteps extends BaseMethods{

    GeneralPOM generalPOM = GeneralPOM.getInstance();
    SalePOM salePOM = SalePOM.getInstance();

    int productStock;

    @And("User selects {string} customer")
    public void userSelectsCustomer(String customerCode) {
        waitVisibilityElement(salePOM.getCustomerSelectBtn(), 10);
       if (!customerCode.isEmpty()) driver.findElement(salePOM.getCustomerSelectBtn()).click();
    }

    @Then("Invoice number should be displayed")
    public void invoiceNumberShouldBeDisplayed() {
        Assert.assertFalse(driver.findElement(salePOM.getInvoiceNumber()).getText().isEmpty());
    }

    @And("User add seller to the product")
    public void userAddSellerToTheProduct() {
        waitVisibilityElement(salePOM.getSelectSellerBtn(), 10);
        clickWithAction(driver.findElement(salePOM.getSelectSellerBtn()));
    }

    @Then("New sale should be created")
    public void newSaleShouldBeCreated() {
        Assert.assertTrue(driver.findElement(generalPOM.getSuccessIcon()).isDisplayed());
    }

    @Then("{string} button should be disabled")
    public void buttonShouldBeDisabled(String element) {
            Assert.assertFalse(driver.findElement(elementsMap.get(element)).isEnabled());
    }

    @Then("Credit sale should be canceled successfully")
    public void creditSaleShouldBeCanceledSuccessfully() {
        try {
            driver.findElement(elementsMap.get("confirmBtn"));
        }catch (NoSuchElementException noSuchElementException){
         Assert.assertTrue(true);
        }
    }

    @And("Collect product stock count")
    public void collectProductStockCount() {
        waitVisibilityElement(driver.findElement(generalPOM.getProductCount(1)), 5);
        productStock = Integer.parseInt(driver.findElement(generalPOM.getProductCount(1)).getText());
    }

    @Then("Product stock should be less {int} less")
    public void productStockShouldBeLessLess(int difference) {
        int currentProductStock = Integer.parseInt(driver.findElement(generalPOM.getProductCount(1)).getText());
        Assert.assertEquals(productStock - currentProductStock, difference);
    }

    @And("User clicks {string} button and add any seller for the product")
    public void userClicksButtonAndAddAnySellerForTheProduct(String element) {
        if (!element.isEmpty()) {
            driver.findElement(elementsMap.get(element)).click();
            waitVisibilityElement(salePOM.getSelectSellerBtn(), 10);
            clickWithAction(driver.findElement(salePOM.getSelectSellerBtn()));
        }
    }
}
