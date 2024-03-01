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

    @And("User selects {string} customer")
    public void userSelectsCustomer(String customerCode) {
       if (!customerCode.isEmpty()) driver.findElement(salePOM.getCustomerSelectBtn()).click();
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
