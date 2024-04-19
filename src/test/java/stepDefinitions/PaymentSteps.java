package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import pom.LoginPOM;
import pom.PaymentPOM;
import pom.SalePOM;

import java.util.List;

public class PaymentSteps extends BaseMethods{

    PaymentPOM paymentPOM = PaymentPOM.getInstance();

@And("User selects the sale invoice and clicks {string} button")
    public void userSelectsInvoice(String btnText) throws InterruptedException {
    if (!btnText.isEmpty()) {
        Thread.sleep(2000);
        getJsExecutor().executeScript("arguments[0].click();", driver.findElement(paymentPOM.getInvoiceNumColumnHeader()));
        getJsExecutor().executeScript("arguments[0].click();", driver.findElement(paymentPOM.getInvoiceNumColumnHeader()));
        List<WebElement> invoiceSerialNums = driver.findElements(paymentPOM.getInvoiceSerialNum());
        List<WebElement> invoiceNums = driver.findElements(paymentPOM.getInvoiceNum());
        List<WebElement> payButtons = findElementsByText("Ödə");
        for (int i = 0; i < invoiceSerialNums.size(); i++) {
            if ((invoiceSerialNums.get(i).getText() + invoiceNums.get(i).getText()).equals(BaseSteps.getSaleInvoiceNumber().get())) {
                payButtons.get(i).click();
                break;
            }
        }
    }
}

    @And("User fills invoice remaining amount in payment")
    public void userFillsInvoiceRemainingAmountInPayment() {
    String remainingAmount = driver.findElement(paymentPOM.getInvoiceRemainingAmount()).getText();
    clearFieldWithAction(paymentPOM.getCashInflowPaymentInput());
    driver.findElement(paymentPOM.getCashInflowPaymentInput()).sendKeys(remainingAmount);
    }

    @Then("Cash inflow should be completed")
    public void cashInflowShouldBeCompleted() {
        Assert.assertFalse(driver.findElement(paymentPOM.getCashInflowTitleText()).isDisplayed());
    }

    @And("User fills invalid {string} in cash inflow")
    public void userFillsInvalid(String paymentAmount) {
    if (paymentAmount.equals("overload")) {
        String remainingAmount = driver.findElement(paymentPOM.getInvoiceRemainingAmount()).getText();
        int invalidRemainingAmount = Integer.parseInt(remainingAmount) + 1;
        clearFieldWithAction(paymentPOM.getCashInflowPaymentInput());
        driver.findElement(paymentPOM.getCashInflowPaymentInput()).sendKeys(String.valueOf(invalidRemainingAmount));
        }
    }

    @And("User fills inflow amount in pay amount field")
    public void userFillsInflowAmountInPayAmountField() {
        String inflowAmount = driver.findElement(paymentPOM.getCashInflowAmountValue()).getText();
        clearFieldWithAction(paymentPOM.getCashOutflowPaymentInput());
        driver.findElement(paymentPOM.getCashOutflowPaymentInput()).sendKeys(inflowAmount);
    }

    @And("User fills invalid {string} in cash outflow")
    public void userFillsInvalidInCashOutflow(String paymentAmount) {
        if (paymentAmount.equals("overload")) {
            String maximumPayAmount = driver.findElement(paymentPOM.getCashOutflowPaymentMaximumLimit()).getText();
            int invalidAmount = Integer.parseInt(maximumPayAmount) + 1;
            clearFieldWithAction(paymentPOM.getCashOutflowPaymentInput());
            driver.findElement(paymentPOM.getCashOutflowPaymentInput()).sendKeys(String.valueOf(invalidAmount));
        }
    }
}
