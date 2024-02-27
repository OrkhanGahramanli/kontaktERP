package POM;

import io.cucumber.java.sl.In;
import lombok.Data;
import org.openqa.selenium.By;

import static POM.ElementsMap.elementsMap;
@Data
public class SalePOM {

    private SalePOM(){
    }

    private static SalePOM INSTANCE;

    public static SalePOM getInstance(){
        if (INSTANCE == null){
            INSTANCE = new SalePOM();
            return INSTANCE;
        }
        return INSTANCE;
    }

    static {
        elementsMap.put("saleModule", By.id("satis_menu"));
        elementsMap.put("newCreditSale", By.id("kreditsatis_link"));
        elementsMap.put("orderSearchBtn", By.cssSelector(".btn.btn-primary.btn-sm.form-control.form-control-sm"));
        elementsMap.put("customerSearchBtn", By.cssSelector(".btn.btn-primary.mt-3"));
        elementsMap.put("calculateCreditBtn", By.xpath("//*[@onclick='satisKreditHesabla(2,1)']"));
        elementsMap.put("orderCode", By.id("sifaris_No"));
        elementsMap.put("regionCode", By.id("caribolge-selectized"));
        elementsMap.put("customerGroupCode", By.id("cariqrup-selectized"));
        elementsMap.put("creditMonths", By.id("taksitSayi"));
        elementsMap.put("Akb", By.id("akb"));
        elementsMap.put("asanFinance", By.id("asanfinance"));
        elementsMap.put("sendSMSBtn", By.id("smsSendBtn"));
        elementsMap.put("SMSCode", By.id("smsCode"));
        elementsMap.put("completeSaleBtn", By.id("satisTamam"));
        elementsMap.put("expandCustomerSectionBtn", By.xpath("//*[@data-target='#collapsumusteri']"));
    }

    private final By customerSelectBtn = By.xpath("//td/button[text()='Seç']");
    private final By productBrandNameField = By.id("stokMarka-selectized");
    private final By invoiceNumber = By.xpath("//*[text()='Faktura Nömrəsi :']");
    private final By productSellerBtn = By.xpath("//*[@class='far fa-user']");
    private final By selectSellerBtn = By.xpath("//*[@id='tableSatici']//tr[1]/td[3]/button");
}
