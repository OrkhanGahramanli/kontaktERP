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
        elementsMap.put("paymentType", By.xpath("//*[@onchange='odemeNovuYukle(this)']"));
        elementsMap.put("paymentCode", By.xpath("//*[@id='faqs-row0']//td[5]/select"));
    }

    private final By invoiceRemainingAmount = By.xpath("//*[@id='tableFakturaListe']//td[11]");
    private final By cashInflowPaymentInput = By.xpath("//*[@id='odenisler']//*[@class='form-control form-control-sm inputmask']");
    private final By cashInflowTitleText = By.className("pt-1");
    private final By invoiceSerialNum = By.xpath("//*[@id='tableFakturaListe']//td[1]");
    private final By invoiceNum = By.xpath("//*[@id='tableFakturaListe']//td[2]");
    private final By invoiceNumColumnHeader = By.xpath("//th[text()='Faktura Nömrəsi']");
}
