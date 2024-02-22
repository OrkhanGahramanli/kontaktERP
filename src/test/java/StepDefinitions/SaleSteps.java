package StepDefinitions;

import POM.GeneralPOM;
import POM.SalePOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.testng.Assert;

import static POM.ElementsMap.elementsMap;

public class SaleSteps extends BaseMethods{

    GeneralPOM generalPOM = GeneralPOM.getInstance();
    SalePOM salePOM = SalePOM.getInstance();

    @When("User fills {string} in {string} input field")
    public void userFillsInputField(String text, String element){
        driver.findElement(elementsMap.get(element)).click();
        driver.findElement(elementsMap.get(element)).clear();
        driver.findElement(elementsMap.get(element)).sendKeys(text);
    }

    @And("User selects customer")
    public void userSelectsCustomer() {
        driver.findElement(salePOM.getCustomerSelectBtn()).click();
    }

    @And("User selects {string} option from {string}")
    public void userSelectsOptionFrom(String text, String element) {
        try {
            selectVisibleText(driver.findElement(elementsMap.get(element)), text);
        }catch (UnexpectedTagNameException u){
            driver.findElement(elementsMap.get(element)).sendKeys(text);
            driver.findElement(generalPOM.selectFieldValue(text)).click();
        }
    }

    @And("User expands customer section")
    public void userExpandsCustomerSection() {
        driver.findElement(salePOM.getCustomerAreaExpandBtn()).click();
    }

    @Then("Invoice number should be displayed")
    public void invoiceNumberShouldBeDisplayed() {
        Assert.assertFalse(driver.findElement(salePOM.getInvoiceNumber()).getAttribute("value").isEmpty());
    }
}
