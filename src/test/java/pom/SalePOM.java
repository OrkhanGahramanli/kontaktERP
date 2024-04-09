package pom;

import lombok.Data;
import org.openqa.selenium.By;

import static pom.ElementsMap.elementsMap;
@Data
public class SalePOM {

    private SalePOM() {
    }

    private static SalePOM INSTANCE;

    public static SalePOM getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SalePOM();
            return INSTANCE;
        }
        return INSTANCE;
    }

    static {
        elementsMap.put("saleModule", By.id("satis_menu"));
        elementsMap.put("newCreditSale", By.id("kreditsatis_link"));
        elementsMap.put("orderSearchBtn", By.cssSelector(".btn.btn-primary.btn-sm.form-control.form-control-sm"));
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
        elementsMap.put("cancelSubject", By.id("KreditImtinaAna"));
        elementsMap.put("cancelReason", By.id("kreditImtinaSebeb"));
        elementsMap.put("creditCancelBtn", By.id("BtnKreditImtina"));
        elementsMap.put("newCashSale", By.id("negdsatis_link"));
        elementsMap.put("addPaymentBtn", By.xpath("//*[@onclick='odemeElaveEt()']"));
        elementsMap.put("completeCashSaleBtn", By.xpath("//*[@onclick='negdSatisTamamla()']"));
        elementsMap.put("productSellerBtn", By.xpath("//*[@class='far fa-user']"));
        elementsMap.put("saleReturn", By.id("qaytarma_link"));
        elementsMap.put("saleInvoice", By.id("fakturanomresi"));
        elementsMap.put("invoiceSearchBtn", By.cssSelector(".btn.btn-primary.btn-sm.form-control.form-control-sm"));
        elementsMap.put("returnGroup", By.id("qaytarmastatusuqrup-selectized"));
        elementsMap.put("returnReason", By.id("qaytarmastatusu"));
        elementsMap.put("createdReturnReason", By.xpath("(//input[@id='sifaris_cariad'])[3]"));
        elementsMap.put("returnCompleteBtn", By.xpath("//*[@onclick='qaytarmaTamalma()']"));
        elementsMap.put("serviceCreditSaleLink", By.id("xidmetkreditsatisi_link"));
        elementsMap.put("stockActionsExpandBtn", By.xpath("//*[@data-target='#stokhereketleri-card']"));
        elementsMap.put("stockActionProductSearch", By.xpath("//*[@id='stokHereketTable_filter']//*[@type='search']"));
        elementsMap.put("stockActionProductSelectBtn", By.xpath("//*[@id='stokHereketTable']//button[text()='Seç']"));
        elementsMap.put("serviceTypeList", By.id("xidmetSecim"));
        elementsMap.put("selectServiceBtn", By.id("btnxidmet"));
        elementsMap.put("saveServiceCreditSaleBtn", By.xpath("//*[@onclick='birlesmeQeydEt()']"));
        elementsMap.put("serviceCreditSalePrice", By.id("targetValue"));
        elementsMap.put("deleteServiceBtn", By.xpath("//*[@onclick='stokXidmetBirlesmeSetrSil(this)']"));
        elementsMap.put("paymentTypeSelect", By.xpath("//*[@onchange='odemeNovuYukleNegd(this)']"));
        elementsMap.put("paymentCodeSelect", By.xpath("//*[@onchange='taksitAylarYukle(this)']"));
        elementsMap.put("paymentTaksitGroupSelect", By.xpath("//td[3]/select[@onchange='taksitAylarYukle(this)']"));
        elementsMap.put("paymentTaksitMonthsSelect", By.xpath("//td[4]/select[@class='form-control']"));
    }

    private final By customerSelectBtn = By.xpath("//td/button[text()='Seç']");
    private final By productBrandNameField = By.id("stokMarka-selectized");
    private final By selectSellerBtn = By.xpath("//*[@id='tableSatici']//tr[1]/td[3]/button");
    private final By selectProductCheckBox = By.className("custom-control-input");
    private final By returnProductsPrices = By.xpath("//*[@id='urunler']//tbody/tr/td[7]");
    private final By returnProductsPricesTotal = By.xpath("//*[@class='table-custom table-bordered table-form-custom']//tbody/tr[1]/td[2]");
    private final By serviceCreditSaleCode = By.xpath("(//*[@id='urunler'])[1]//td[3]");
    private final By serviceCreditSalePrice = By.xpath("(//*[@id='urunler'])[1]//td[5]");
    private final By servicesTable = By.id("tblBodyXidmetler");

}
