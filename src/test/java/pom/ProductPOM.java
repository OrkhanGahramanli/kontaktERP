package pom;

import lombok.Data;
import org.openqa.selenium.By;

import static pom.ElementsMap.elementsMap;

@Data
public class ProductPOM {

    private ProductPOM() {
    }

    private static ProductPOM INSTANCE;

    public static ProductPOM getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductPOM();
            return INSTANCE;
        }
        return INSTANCE;
    }

    static {
        elementsMap.put("productModuleLink", By.id("product_menu"));
        elementsMap.put("groupParameterPageLink", By.id("webstock_link"));
        elementsMap.put("productCategoryList", By.id("anagroup_code"));
        elementsMap.put("parameterNameAze", By.id("headerNameAz"));
        elementsMap.put("parameterNameRu", By.id("headerNameRu"));
        elementsMap.put("webOrder", By.id("wshead_proiret"));
        elementsMap.put("viewOnWeb", By.id("wshead_visible"));
        elementsMap.put("makeActive", By.id("ws_status"));
        elementsMap.put("addParameterBtn", By.cssSelector(".btn.btn-primary"));
        elementsMap.put("parameterOptionsPageLink", By.id("product_parameters"));
        elementsMap.put("filterByGroupCodeBtn", By.xpath("//*[text()='Qrup kodu']//*[@class='dx-header-filter dx-header-filter-empty']"));
        elementsMap.put("expandParameterOptionSection", By.className("dx-datagrid-group-closed"));
        elementsMap.put("addParameterOptionBtn", By.xpath("//*[@aria-label='Əlavə et']"));
        elementsMap.put("productGroupList", By.id("groupList"));
        elementsMap.put("parameterNameList", By.id("headerList"));
        elementsMap.put("parameterOptionPlusBtn", By.name("addNew"));
        elementsMap.put("parameterOptionAze", By.name("ValueAz"));
        elementsMap.put("parameterOptionRu", By.name("ValueRu"));
        elementsMap.put("saveParameterOptionsBtn", By.xpath("//*[@onclick='save()']"));
        elementsMap.put("productParameterPageLink", By.id("webstockizle_link"));
        elementsMap.put("productCategories", By.id("stokKateqoriya"));
        elementsMap.put("optionAze", By.xpath("(//*[@id='webProdStock']//*[@class='form-control form-control-sm'])[1]"));
        elementsMap.put("optionRu", By.xpath("(//*[@id='webProdStock']//*[@class='form-control form-control-sm'])[2]"));
        elementsMap.put("saveProductParameterOptionsBtn", By.xpath("//*[@onclick='webStokModelGonder()']"));
    }

    private final By parameterSection = By.id("webStokcHeaderBody");
    private final By deleteParameter = By.id("headerDelete");
    private final By submitDeleteParameter = By.id("submitDelete");
    private final By productGroupCode = By.xpath("//*[@id='webStokcHeaderBody']//td[2]");
    private final By parameterGroupCodeSearch = By.xpath("//*[@aria-label='Search']");
    private final By selectGroupCodeCheckBox = By.className("dx-checkbox-icon");
    private final By filterAcceptBtn = By.xpath("//*[@aria-label='OK']");
    private final By deleteParameterOptionsBtn = By.xpath("//*[@class='dx-datagrid-content dx-datagrid-content-fixed']//*[@class='dx-link dx-icon-trash dx-link-icon']");
    private final By noDataGrid = By.className("dx-datagrid-nodata");
    private final By filterCancelBtn = By.xpath("//*[@aria-label='Cancel']");
    private final By selectProductBtn = By.xpath("//*[@onclick=\"addStockWeb('001','STOK')\"]");
}
