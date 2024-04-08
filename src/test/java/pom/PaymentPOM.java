package pom;

import lombok.Data;
import org.openqa.selenium.By;

import static pom.ElementsMap.elementsMap;

@Data
public class PaymentPOM {

    private PaymentPOM(){

    }

    private static PaymentPOM INSTANCE;

    public static PaymentPOM getInstance(){
        if (INSTANCE == null){
            INSTANCE = new PaymentPOM();
            return INSTANCE;
        }
        return INSTANCE;
    }

    static {
        elementsMap.put("paymentModule", By.id("odeme_menu"));
        elementsMap.put("cashInflowPageLink", By.id("kassamedaxil_link"));
        elementsMap.put("completePaymentBtn", By.xpath("//button[@onclick='odenisQeydEt()']"));
    }

    private final By invoiceRemainingAmount = By.xpath("//*[@id='tableFakturaListe']//td[11]");
    private final By cashInflowPaymentInput = By.xpath("//*[@id='odenisler']//*[@class='form-control form-control-sm inputmask']");
}
