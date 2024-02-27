package POM;

import lombok.Data;
import org.openqa.selenium.By;

import static POM.ElementsMap.elementsMap;

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
    private final By productNameField = By.id("stokAdi");
    private final By productBrandNameField = By.id("stokMarka-selectized");
    private final By productSearchBtn = By.cssSelector("button[class='btn btn-primary']");
    private final By successIcon = By.className("swal2-success-ring");
    private final By errorMessage = By.className("noty_type__error");
    private final By productEmptyErrorMessage = By.id("recipeitemAlert");

    static {
        elementsMap.put("productSearchBtn", By.cssSelector("button[class='btn btn-primary']"));
        elementsMap.put("customerCode", By.id("search_customervcode"));
        elementsMap.put("confirmBtn", By.className("swal2-confirm"));
        elementsMap.put("productAreaExpandBtn", By.xpath("//*[@data-target='#mehsulaxtar-card']"));
    }



    public By getProductPrice(int index){
        return By.xpath("//*[@id='tableSearchProducts']//tr["+index+"]/td[4]");
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

    public By selectFieldValue(String value){
        return By.xpath("//*[@data-value='"+value+"']");
    }
}
