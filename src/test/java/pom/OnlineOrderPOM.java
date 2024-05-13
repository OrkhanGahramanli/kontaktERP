package pom;

import lombok.Data;
import org.openqa.selenium.By;

import static pom.ElementsMap.elementsMap;

@Data
public class OnlineOrderPOM {

    private OnlineOrderPOM() {
    }

    private static OnlineOrderPOM INSTANCE;

    public static OnlineOrderPOM getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OnlineOrderPOM();
            return INSTANCE;
        }
        return INSTANCE;
    }

    static {
        elementsMap.put("onlineOrderPageLink", By.id("websifaris_link"));
        elementsMap.put("newOrderBtn", By.cssSelector(".btn.btn-sm.btn-primary"));
        elementsMap.put("customerName", By.name("sifaris_musteri_adi"));
        elementsMap.put("customerSurname", By.name("sifaris_musteri_soyadi"));
        elementsMap.put("customerMobile", By.name("sifaris_mobil_tel_no"));
        elementsMap.put("customerFinCode", By.name("sifaris_fin_kodu"));
        elementsMap.put("customerAddress", By.name("sifaris_unvan"));
        elementsMap.put("customerCity", By.name("sifaris_seher"));
        elementsMap.put("productSelectBtn", By.id("btnStok"));
        elementsMap.put("productSearchByName", By.id("axtarisAdiNew"));
        elementsMap.put("completeOnlineOrderBtn", By.xpath("//*[@onclick='webSifarisEkle()']"));
        elementsMap.put("onlineOrderInfoBtn", By.xpath("(//a[@title='Bax'])[2]"));
        elementsMap.put("orderNumField", By.id("sifaris_no"));
        elementsMap.put("productNameInOrder", By.xpath("//*[@id='sifaris_mehsullar_table']//td[3]/input"));
        elementsMap.put("onlineOrderStatusOptionsField", By.id("web_kassir_status"));
        elementsMap.put("updateOnlineOrderBtn", By.xpath("//*[@onclick='webSifarisStatusYenile()']"));
        elementsMap.put("productSearchByNameInOrderEdit", By.id("axtarisAdi"));
        elementsMap.put("customerNameEditOrder", By.id("sifaris_musteri_adi"));
        elementsMap.put("customerSurnameEditOrder", By.id("sifaris_musteri_soyadi"));
        elementsMap.put("customerMobileEditOrder", By.id("sifaris_mobil_tel_no"));
        elementsMap.put("customerFinCodeEditOrder", By.id("sifaris_fin_kodu"));
        elementsMap.put("customerAddressEditOrder", By.id("sifaris_unvan"));
        elementsMap.put("customerCityEditOrder", By.id("sifaris_seher"));
        elementsMap.put("selectProductInOnlineOrder", By.xpath("//*[@onclick='openMehsulModal()']"));
        elementsMap.put("addProductInOnlineOrder", By.xpath("//*[@id='tableProductsChange']//*[text()='Əlavə et']"));
        elementsMap.put("onlineOrderStatusColumn", By.xpath("//*[@class='dx-row dx-data-row dx-row-lines dx-column-lines']//td[9]"));
        elementsMap.put("creditSaleBtn", By.xpath("//*[@onclick='kreditSatis()']"));
        elementsMap.put("cashSaleBtn", By.xpath("//*[@onclick='nagdSatisAc()']"));
        elementsMap.put("customerAnalysisBtn", By.xpath("//*[@onclick='musteriAnalizAc()']"));
        elementsMap.put("newCustomerBtn", By.xpath("//*[@onclick='yeniMusterAc()']"));
        elementsMap.put("addToBasketBtn", By.xpath("(//a[@title='Səbətə at'])[2]"));
        elementsMap.put("basketBtn", By.id("sebetBtn"));
        elementsMap.put("reassignOrderBtn", By.xpath("(//a[@title='Əməkdaş dəyiş'])[2]"));
        elementsMap.put("employeeSelectField", By.id("userList"));
        elementsMap.put("completeAssignBtn", By.xpath("//*[@onclick='changeViewer()']"));
        elementsMap.put("onlineOrderViewerColumn", By.xpath("//*[@class='dx-row dx-data-row dx-row-lines dx-column-lines']//td[5]"));
    }

    private final By cashRadioBtn = By.id("nagdCheckBox");
    private final By creditRadioBtn = By.id("kreditCheckBox");
    private final By onlineOrderSearchField = By.xpath("//*[@aria-label='Search in the data grid']");
    private final By onlineOrderNumColumn = By.xpath("(//*[@class='dx-datagrid-search-text'])[2]");
    private final By onlineOrderNumInBasket = By.className("sorting_1");
    private final By reassignOrderCompleteMessage = By.id("swal2-content");
}
