package stepDefinitions;

import io.cucumber.java.en.And;
import pom.PaymentPOM;

public class PaymentSteps extends BaseMethods{

    PaymentPOM paymentPOM = PaymentPOM.getInstance();

@And("User selects any sale invoice and clicks {string} button")
    public void userSelectsInvoice(String btnText){
    if (!btnText.isEmpty()) findElementByText(btnText).click();
}

    @And("User fills invoice remaining amount in payment")
    public void userFillsInvoiceRemainingAmountInPayment() {
    String remainingAmount = driver.findElement(paymentPOM.getInvoiceRemainingAmount()).getText();
    driver.findElement(paymentPOM.getCashInflowPaymentInput()).clear();
    driver.findElement(paymentPOM.getCashInflowPaymentInput()).sendKeys(remainingAmount);
    }
}
