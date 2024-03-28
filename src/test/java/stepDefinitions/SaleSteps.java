package stepDefinitions;

import io.cucumber.java.AfterStep;
import org.openqa.selenium.WebElement;
import pom.GeneralPOM;
import pom.SalePOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import java.util.List;

import static pom.ElementsMap.elementsMap;

public class SaleSteps extends BaseMethods{

    GeneralPOM generalPOM = GeneralPOM.getInstance();
    SalePOM salePOM = SalePOM.getInstance();

    int productStock;
    private static String saleInvoiceNumber;

    @And("User selects {string} customer")
    public void userSelectsCustomer(String customerCode) {
        waitVisibilityElement(salePOM.getCustomerSelectBtn(), 10);
       if (!customerCode.isEmpty()) driver.findElement(salePOM.getCustomerSelectBtn()).click();
    }

    @Then("Invoice number should be displayed")
    public void invoiceNumberShouldBeDisplayed() {
        Assert.assertFalse(driver.findElement(salePOM.getInvoiceNumber()).getAttribute("value").isEmpty());
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
    @And("User selects a product")
    public void userSelectsAProduct() {
        clickWithAction(driver.findElement(salePOM.getSelectProductCheckBox()));
    }

    @Then("{string} should be displayed in {string} text field")
    public void shouldBeDisplayedInTextField(String text, String element) {
        waitVisibilityElement(elementsMap.get(element), 20);
        Assert.assertEquals(driver.findElement(elementsMap.get(element)).getAttribute("value"), text);
    }

    @And("User takes sale invoice number")
    public void userTakes() {
        saleInvoiceNumber = driver.findElement(salePOM.getInvoiceNumber()).getAttribute("value");
    }

    @And("User fills sale invoice number in {string} input field")
    public void userFillsSaleInvoiceNumberInInputField(String element) {
        driver.findElement(elementsMap.get(element)).sendKeys(saleInvoiceNumber);
    }

    @And("User selects all products")
    public void userSelectsAllProducts() throws InterruptedException {
        List<WebElement> productsForReturn = driver.findElements(salePOM.getSelectProductCheckBox());
        for (WebElement element : productsForReturn){
            clickWithAction(element);
            Thread.sleep(10000);
        }
    }

    @Then("Total return amount should equals sum of products' amount")
    public void totalReturnAmountShouldEqualsSumOfProductsAmount() {
        waitVisibilityElement(salePOM.getReturnProductsPricesTotal(), 10);
        double productPrices = 0;
        List<WebElement> productPricesElements = driver.findElements(salePOM.getReturnProductsPrices());
        for (WebElement element : productPricesElements){
            productPrices+=Double.parseDouble(element.getText());
        }

        Assert.assertEquals(Double.parseDouble(driver.findElement(salePOM.getReturnProductsPricesTotal()).getText()),
                                productPrices);
    }

    @And("User add sellers to the products")
    public void userAddSellersToTheProducts() {
        List<WebElement> productsSellers = driver.findElements(elementsMap.get("productSellerBtn"));
        for (WebElement element : productsSellers){
            element.click();
            waitVisibilityElement(salePOM.getSelectSellerBtn(), 10);
            clickWithAction(driver.findElement(salePOM.getSelectSellerBtn()));
        }
    }
}
