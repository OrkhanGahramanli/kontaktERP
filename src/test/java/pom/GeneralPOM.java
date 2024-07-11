package pom;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static pom.ElementsMap.elementsMap;

@Data
public class GeneralPOM {

    private GeneralPOM(){

    }

    private static GeneralPOM INSTANCE;

    public static GeneralPOM getInstance(){
        if (INSTANCE == null){
            INSTANCE = new GeneralPOM();
            return INSTANCE;
        }
        return INSTANCE;
    }

    private final By storeSelect = By.id("user_magaza_kodu");
    private final By sellerSearchField = By.id("sif_satici_kodu_axtar");
    private final By sellerSearchBtn = By.cssSelector(".btn.btn-primary.btn-sm.form-control.form-control-sm");
    private final By successIcon = By.className("swal2-success-ring");
    private final By popUpMessage = By.className("noty_bar");
    private final By emptyProductErrorAlert = By.id("recipeitemAlert");
    private final By popUpErrorAlert = By.className("noty_type__error");
    private final By serviceSearchField = By.xpath("//input[@aria-controls='tableHizmet']");
    private final By addServiceBtn = By.xpath("//*[@id='tableHizmet_wrapper']//*[@type='button']");
    private final By addedProductsPrices = By.xpath("//*[@id='tableProd']/tr/td[4]/input");
    private final By addedProductsDiscounts = By.xpath("//*[@id='tableProd']/tr/td[6]/input");
    private final By productsAmount = By.id("toplamMebleg");
    private final By productsDiscount = By.id("endirim");
    private final By productsTotalAmount = By.id("yekunMebleg");
    private final By productInBundle = By.xpath("//*[@id='faqs-row-0']/td[2]");
    private final By completeNotificationText = By.className("swal2-title");
    private final By invoiceNumber = By.id("sifaris_seriya");
    private final By emptyPaymentReasonErrorMessage = By.id("odenis_sebebi-error");
    private final By pageTitle = By.xpath("(//h5)[1]");

    static {
        elementsMap.put("productSearchBtn", By.cssSelector("button[class='btn btn-primary']"));
        elementsMap.put("customerCode", By.id("search_customervcode"));
        elementsMap.put("confirmBtn", By.className("swal2-confirm"));
        elementsMap.put("productAreaExpandBtn", By.xpath("//*[@data-target='#mehsulaxtar-card']"));
        elementsMap.put("bundleBtn", By.xpath("//button[contains(text(),'Bundle Seç')]"));
        elementsMap.put("serviceBtn", By.xpath("//button[contains(text(),'Xidmət Seç')]"));
        elementsMap.put("windowCloseBtn", By.xpath("//*[@aria-modal='true']//*[@class='btn btn-secondary']"));
        elementsMap.put("bundleDetailsBtn", By.cssSelector(".btn.btn-info.btn-sm.float-right.mr-2"));
        elementsMap.put("productBrand", By.id("stokMarka-selectized"));
        elementsMap.put("bundleAddBtn", By.xpath("//*[@id='tableBundle_wrapper']//tr[1]/td/button[@type='button']"));
        elementsMap.put("productName", By.id("stokAdi"));
        elementsMap.put("addProductBtn", By.xpath("//*[@id='tableSearchProducts']//tr[1]/td/button[text()='Əlavə Et']"));
        elementsMap.put("otherStoresBtn", By.xpath("//*[@data-target='#stokDigerModal']"));
        elementsMap.put("addProductOtherStoreBtn", By.xpath("//*[@id='tableOrderOtherProducts']//*[@type='button']"));
        elementsMap.put("productDeliveryType", By.id("teslimnovu_0"));
        elementsMap.put("acceptSuccessMessageBtn", By.className("swal2-confirm"));
        elementsMap.put("printEdvBtn", By.className("swal2-confirm"));
        elementsMap.put("customerSearchBtn", By.cssSelector(".btn.btn-primary.mt-3"));
        elementsMap.put("onlineOrder", By.id("websifaris_menu"));
        elementsMap.put("webOrdersLink", By.id("allweborders_link"));
        elementsMap.put("orderModule", By.id("sifaris_menu"));
        elementsMap.put("newOrder", By.id("order_link"));
        elementsMap.put("customerName", By.id("sif_musteri_adi"));
        elementsMap.put("saleType", By.id("sif_satis_novu"));
        elementsMap.put("customerBirthDate", By.id("customerBirthDate"));
        elementsMap.put("submitOrder", By.cssSelector("button[class='btn btn-primary float-right']"));
        elementsMap.put("addedProductsTable", By.id("faqs-row8"));
        elementsMap.put("removeProductBtn", By.cssSelector(".fa.fa-trash"));
        elementsMap.put("showCustomerDetailsBtn", By.id("showCustDetail"));
        elementsMap.put("customerCodeInCustomerAnalysis", By.id("customer_code"));
    }


    public By getProductCount(int index){
        return By.xpath("//*[@id='tableSearchProducts']//tr["+index+"]/td[5]");
    }

    public By getProductCountInOtherStore(int index){
        return By.xpath("//tr["+index+"]/td[8]");
    }

}
