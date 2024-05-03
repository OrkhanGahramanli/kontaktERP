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
        elementsMap.put("productSearchByName", By.name("axtarisAdiNew"));
        elementsMap.put("completeOnlineOrderBtn", By.xpath("//*[@onclick='webSifarisEkle()']"));
    }

    private final By cashRadioBtn = By.id("nagdCheckBox");
    private final By creditRadioBtn = By.id("kreditCheckBox");
    private final By onlineOrderSearchField = By.xpath("//*[@aria-label='Search in the data grid']");
    private final By onlineOrderNumColumn = By.xpath("(//*[@class='dx-datagrid-search-text'])[2]");
}
