package pom;

import lombok.Data;
import org.openqa.selenium.By;

import static pom.ElementsMap.elementsMap;

@Data
public class OrderPOM {

    private OrderPOM(){
    }

    private static OrderPOM INSTANCE;

    public static OrderPOM getInstance(){
        if (INSTANCE == null){
            INSTANCE = new OrderPOM();
            return INSTANCE;
        }
        return INSTANCE;
    }

    private final By webOrderNumSearchField = By.xpath("(//input[@aria-label='Filter cell'])[1]");
    private final By webOrderNum = By.xpath("//tr[1]/td[1][@aria-selected='false'][@role='gridcell'][@aria-describedby='dx-col-67-fixed']");
    private final By productsCodeBeforeCreate = By.xpath("//*[@id='tableProd']/tr/td[2]");
    private final By productsCodeAfterCreate = By.xpath("//*[@id='sifaris_mehsullar_table']/tbody/tr/td[2]/input");
    private final By createdOrderDetailsBtn = By.xpath("(//a[@title='Bax'])[2]");
    private final By createdOrderType = By.xpath("//tr[1]/td[3][@aria-selected='false'][@role='gridcell'][@aria-describedby='dx-col-69']");
    private final By creditorWorkStatusBtn = By.xpath("//tr[1]/td/button[@class='btn btn-success']");
    private final By creditorWorkStatus = By.xpath("//tbody/tr[1]/td[3]");
    public By productsInfoBtn(String value){
        return By.xpath("(//button[@type='button'][contains(text(),'" + value + "')])");
    }
    private final By productInfoResult = By.id("exampleModalLabel");


    static {
        elementsMap.put("productsInfo", By.id("stockinfo_link"));
        elementsMap.put("creditors", By.id("creditorView_link"));
        elementsMap.put("bundleSearchField", By.xpath("//*[@id='tableBundle_filter']//*[@type='search']"));

    }





}
