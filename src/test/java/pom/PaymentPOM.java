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
        elementsMap.put("cashInflowCompletePaymentBtn", By.xpath("//button[@onclick='odenisQeydEt()']"));
        elementsMap.put("paymentType", By.xpath("//*[@onchange='odemeNovuYukle(this)']"));
        elementsMap.put("paymentCode", By.xpath("//*[@id='faqs-row0']//td[5]/select"));
        elementsMap.put("paymentSearchField", By.xpath("//input[@aria-controls='tableMexaricSenedleri']"));
        elementsMap.put("cashOutflowPageLink", By.id("kassaodeme_link"));
        elementsMap.put("selectCashInInvoiceBtn", By.xpath("//*[@id='tableMexaricSenedleri']//td/button[text()='Seç']"));
        elementsMap.put("paymentReasonOptionsField", By.id("odenis_sebebi"));
        elementsMap.put("completeCashOutflowPaymentBtn", By.xpath("//*[@id='mexaric-form']//button[@class='btn btn-primary']"));
    }

    private final By invoiceRemainingAmount = By.xpath("//*[@id='tableFakturaListe']//td[11]");
    private final By cashInflowPaymentInput = By.xpath("//*[@id='odenisler']//*[@class='form-control form-control-sm inputmask']");
    private final By cashInflowTitleText = By.className("pt-1");
    private final By invoiceSerialNum = By.xpath("//*[@id='tableFakturaListe']//td[1]");
    private final By invoiceNum = By.xpath("//*[@id='tableFakturaListe']//td[2]");
    private final By invoiceNumColumnHeader = By.xpath("//th[text()='Faktura Nömrəsi']");
    private final By cashInflowAmountValue = By.xpath("//*[@id='tableMexaricSenedleri']//td[6]");
    private final By cashOutflowPaymentInput = By.id("odeme_meblegi");
    private final By cashOutflowPaymentMaximumLimit = By.xpath("//*[@id='tableMexaricSenedleri']//td[6]");
}
