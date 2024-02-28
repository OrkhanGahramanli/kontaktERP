package StepDefinitions;

import POM.GeneralPOM;
import POM.SalePOM;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.testng.Assert;

import static POM.ElementsMap.elementsMap;

public class SaleSteps extends BaseMethods{

    GeneralPOM generalPOM = GeneralPOM.getInstance();
    SalePOM salePOM = SalePOM.getInstance();

    @When("User fills {string} in {string} input field")
    public void userFillsInputField(String text, String element){
        if (!text.isEmpty()) {
            waitVisibilityElement(elementsMap.get(element), 5);
            driver.findElement(elementsMap.get(element)).click();
            driver.findElement(elementsMap.get(element)).clear();
            driver.findElement(elementsMap.get(element)).sendKeys(text);
        }
    }

    @And("User selects {string} customer")
    public void userSelectsCustomer(String customerCode) {
       if (!customerCode.isEmpty()) driver.findElement(salePOM.getCustomerSelectBtn()).click();
    }

    @And("User selects {string} option from {string}")
    public void userSelectsOptionFrom(String text, String element) {
        if (!text.isEmpty()) {
            waitVisibilityElement(elementsMap.get(element), 10);
            try {
                selectVisibleText(driver.findElement(elementsMap.get(element)), text);
            } catch (UnexpectedTagNameException u) {
                driver.findElement(elementsMap.get(element)).sendKeys(text);
                driver.findElement(generalPOM.selectFieldValue(text)).click();
            }
        }
    }

    @Then("Invoice number should be displayed")
    public void invoiceNumberShouldBeDisplayed() {
        Assert.assertFalse(driver.findElement(salePOM.getInvoiceNumber()).getText().isEmpty());
    }

    @And("User add seller to the product")
    public void userAddSellerToTheProduct() {
        driver.findElement(salePOM.getProductSellerBtn()).click();
        waitVisibilityElement(salePOM.getSelectSellerBtn(), 5);
        driver.findElement(salePOM.getSelectSellerBtn()).click();
    }

    @Then("New sale should be created")
    public void newSaleShouldBeCreated() {
        Assert.assertTrue(driver.findElement(generalPOM.getSuccessIcon()).isDisplayed());
    }

    @Then("{string} button should be disabled")
    public void buttonShouldBeDisabled(String element) {
            Assert.assertFalse(driver.findElement(elementsMap.get(element)).isEnabled());
    }
}
