package POM;

import lombok.Data;
import org.openqa.selenium.By;
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
    private final By productAreaExpand = By.xpath("//*[@aria-expanded='false']");
    private final By productNameField = By.id("stokAdi");
    private final By productBrandNameField = By.id("stokMarka-selectized");
    private final By productSearchBtn = By.cssSelector("button[class='btn btn-primary']");



    public By getProductPrice(int index){
        return By.xpath("//tr["+index+"]/td[4]");
    }

    public By getProductCount(int index){
        return By.xpath("//tr["+index+"]/td[5]");
    }

    public By getAddProductBtn(int index){
        return By.xpath("//tr["+index+"]/td[10]");
    }

    public By getProductCountInOtherStore(int index){
        return By.xpath("//tr["+index+"]/td[8]");
    }

    public By getOtherStoresBtn(int index){
        return By.xpath("//tr["+index+"]/td[11]");
    }

    public By productBrandSelect(String value){
        return By.xpath("//*[@data-value='"+value+"']");
    }
}
