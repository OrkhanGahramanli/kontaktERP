package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pom.ProductPOM;

import java.util.ArrayList;
import java.util.List;

import static pom.ElementsMap.elementsMap;

public class ProductSteps extends BaseMethods{

    ProductPOM productPOM = ProductPOM.getInstance();
private String productGroupCode;

    @And("Delete parameter associated to the product category")
    public void delete_parameter_associated_to_the_product_category() {
        if (!driver.findElement(productPOM.getParameterSection()).getText().isEmpty()) {
            driver.findElement(productPOM.getDeleteParameter()).click();
            driver.findElement(productPOM.getSubmitDeleteParameter()).click();
        }
    }

    @And("User takes parameter group code")
    public void userTakesParameterGroupCode() {
        productGroupCode = driver.findElement(productPOM.getProductGroupCode()).getText();
    }

    @And("Delete all parameter options")
    public void deleteAllParameterOptions() throws InterruptedException {
        try {
            waitVisibilityElement(productPOM.getParameterGroupCodeSearch(), 5);
            driver.findElement(productPOM.getParameterGroupCodeSearch()).sendKeys(productGroupCode);
            Thread.sleep(4000);
            driver.findElement(productPOM.getSelectGroupCodeCheckBox()).click();
            driver.findElement(productPOM.getFilterAcceptBtn()).click();
            Thread.sleep(1000);

            getJsExecutor().executeScript("arguments[0].click();",  driver.findElement(elementsMap.get("expandParameterOptionSection")));

            WebElement noDataMessage = driver.findElement(productPOM.getNoDataGrid());
            while (!noDataMessage.isDisplayed()) {
                driver.findElement(productPOM.getDeleteParameterOptionsBtn()).click();
                driver.findElement(elementsMap.get("confirmBtn")).click();
                Thread.sleep(1000);
                driver.findElement(elementsMap.get("confirmBtn")).click();
                Thread.sleep(3000);
            }
            }catch(NoSuchElementException ignored){
                driver.findElement(productPOM.getFilterCancelBtn()).click();
        }
    }

    @And("User selects {string} product")
    public void userSelectsProduct(String text) {
        moveToElement(driver.findElement(productPOM.getSelectProductBtn()));
        driver.findElement(productPOM.getSelectProductBtn()).click();
    }

    @Then("{string} option should be selected in {string} and {string} in {string}")
    public void optionShouldBeSelectedInAndIn(String text1, String element1, String text2, String element2) {
        Assert.assertEquals(text1, getSelectedOption(driver.findElement(elementsMap.get(element1))).getText());
        Assert.assertEquals(text2, getSelectedOption(driver.findElement(elementsMap.get(element2))).getText());
    }
}

