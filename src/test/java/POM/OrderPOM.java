package POM;

import lombok.Data;
import org.openqa.selenium.By;

import static POM.ElementsMap.elementsMap;

@Data
public class OrderPOM {

    public OrderPOM(){
    }

    private static OrderPOM INSTANCE;

    public static OrderPOM getInstance(){
        if (INSTANCE == null){
            INSTANCE = new OrderPOM();
            return INSTANCE;
        }
        return INSTANCE;
    }

    private final By customerNameField = By.id("sif_musteri_adi");
    private final By orderSubmitBtn = By.cssSelector("button[class='btn btn-primary float-right']");
    private final By newOrderCreateMessage = By.id("swal2-title");
    private final By newCreatedOrderConfirmBtn = By.cssSelector("button[class='swal2-confirm swal2-styled']");
    private final By saleType = By.id("sif_satis_novu");
    private final By customerBirthDate = By.id("customerBirthDate");
    private final By onlineOrderMenu = By.id("websifaris_menu");
    private final By webOrdersLink = By.id("allweborders_link");
    private final By webOrderNumSearchField = By.xpath("(//input[@aria-label='Filter cell'])[1]");
    private final By webOrderNum = By.xpath("//tr[1]/td[1][@aria-selected='false'][@role='gridcell'][@aria-describedby='dx-col-67-fixed']");
    private final By errorMessage = By.xpath("//h6");
    private final By productEmptyErrorMessage = By.id("recipeitemAlert");
    private final By selectServiceBtn = By.xpath("//button[contains(text(),'Xidmət Seç')]");
    private final By selectBundleBtn = By.xpath("//button[contains(text(),'Bundle Seç')]");
    private final By serviceSearchField = By.xpath("//input[@aria-controls='tableHizmet']");
    private final By addServiceBtn = By.xpath("//*[@id='tableHizmet_wrapper']//*[@type='button']");
    private final By addBundleBtn = By.xpath("//*[@id='tableBundle_wrapper']//*[@type='button']");
    private final By addProductOtherStoreBtn = By.xpath("//*[@id='tableOrderOtherProducts']//*[@type='button']");
    private final By servicesWindowCloseBtn = By.cssSelector("div[id='hizmetModal'] div[class='modal-footer'] button[type='button']");
    private final By bundlesWindowCloseBtn = By.cssSelector("div[class='modal-dialog modal-xl'] div[class='modal-footer'] button[type='button']");
    private final By productsCodeBeforeCreate = By.xpath("//*[@id='tableProd']/tr/td[2]");
    private final By productsCodeAfterCreate = By.xpath("//*[@id='sifaris_mehsullar_table']/tbody/tr/td[2]/input");
    private final By createdOrderDetailsBtn = By.xpath("(//a[@title='Bax'])[2]");
    private final By addedProductsPrices = By.xpath("//*[@id='tableProd']/tr/td[4]/input");
    private final By addedProductsDiscounts = By.xpath("//*[@id='tableProd']/tr/td[6]/input");
    private final By productsAmount = By.id("toplamMebleg");
    private final By productsDiscount = By.id("endirim");
    private final By productsTotalAmount = By.id("yekunMebleg");
    private final By productInBundle = By.xpath("//*[@id='faqs-row-0']/td[2]");
    private final By bundleDetailsBtn = By.cssSelector(".btn.btn-info.btn-sm.float-right.mr-2");
    private final By otherStoreProductsWindowCloseBtn = By.cssSelector("div[class='row mt-3 mb-3'] div[class='modal-footer'] button[type='button']");
    private final By productDeliveryType = By.id("teslimnovu_0");
    private final By createdOrderType = By.xpath("//tr[1]/td[3][@aria-selected='false'][@role='gridcell'][@aria-describedby='dx-col-69']");
    private final By creditorWorkStatusBtn = By.xpath("//tr[1]/td/button[@class='btn btn-success']");
    private final By creditorWorkStatus = By.xpath("//tbody/tr[1]/td[3]");
    public By productsInfoBtn(String value){
        return By.xpath("(//button[@type='button'][contains(text(),'" + value + "')])");
    }
    private final By productInfoResult = By.id("exampleModalLabel");


    static {
        elementsMap.put("orderModule", By.id("sifaris_menu"));
        elementsMap.put("newOrder", By.id("order_link"));
        elementsMap.put("productsInfo", By.id("stockinfo_link"));
        elementsMap.put("creditors", By.id("creditorView_link"));
    }





}
