package StepDefinitions;

import POM.GeneralPOM;
import POM.OrderPOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.InputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static POM.ElementsMap.elementsMap;

public class OrderSteps extends BaseMethods {

    OrderPOM orderPOM = OrderPOM.getInstance();
    GeneralPOM generalPOM = GeneralPOM.getInstance();

    String orderNum;
    String[] creditorWorkStatus = new String[2];
    List<String> actualProducts;
    List<String> expectedProducts;

    @And("User add {string} code")
    public void userSelectsSeller(String sellerCode) {
        waitVisibilityElement(generalPOM.getSellerSearchField(), 5);
        driver.findElement(generalPOM.getSellerSearchField()).sendKeys(sellerCode);
        if (!sellerCode.isEmpty()) driver.findElement(generalPOM.getSellerSearchBtn()).click();
    }

    @Then("New order should be create")
    public void newOrderShouldBeCreate() {
        WebElement orderCreated = driver.findElement(generalPOM.getSuccessIcon());
        Assert.assertTrue(orderCreated.isDisplayed());
        String createdOrderMessage = driver.findElement(generalPOM.getCompleteNotificationText()).getText();
        String[] createdOrderNum = createdOrderMessage.split(" ");
        orderNum = createdOrderNum[0];
    }

    @Then("New created order should be in Web Orders list")
    public void newCreatedOrderShouldBeOrdersList() throws InterruptedException {
        driver.findElement(orderPOM.getWebOrderNumSearchField()).sendKeys(orderNum);
        Thread.sleep(2000);
        WebElement webOrderNum = driver.findElement(orderPOM.getWebOrderNum());

        Assert.assertEquals(webOrderNum.getText(), orderNum);
    }

    @Then("Type of new created order should be as {string}")
    public void typeOfNewCreatedOrderShouldBeAs(String saleType) {
        saleType = saleType.split(" ")[0];
        Assert.assertEquals(driver.findElement(orderPOM.getCreatedOrderType()).getText(), saleType);
    }

    @Then("Products and services should be visible in new order")
    public void productsAndServicesShouldBeVisibleInNewOrder() throws InterruptedException {
        driver.findElement(orderPOM.getWebOrderNumSearchField()).sendKeys(orderNum);
        Thread.sleep(2000);
        driver.findElement(orderPOM.getCreatedOrderDetailsBtn()).click();
        waitVisibilityElement(orderPOM.getProductsCodeAfterCreate(),10);
        List<WebElement> productsAfterCreateOrder = driver.findElements(orderPOM.getProductsCodeAfterCreate());
        actualProducts = new ArrayList<>();
        for (WebElement element : productsAfterCreateOrder){
            actualProducts.add(element.getAttribute("value"));
        }
        Collections.sort(actualProducts);
        Collections.sort(expectedProducts);
        Assert.assertEquals(actualProducts, expectedProducts);
    }

    @And("User add {string} product from different store")
    public void userAddProductFromDifferentStore(String product) {
        if (!product.isEmpty()) {
            driver.findElement(generalPOM.getProductNameField()).sendKeys(product);
            driver.findElement(generalPOM.getProductSearchBtn()).click();
            int otherStoreIndex = 0;
            for (int i = 1; i < 11; i++) {
                String[] priceSplit = driver.findElement(generalPOM.getProductPrice(i)).getText().split("\\.");
                int price = Integer.parseInt(priceSplit[0]);
                if (price > 0
                        && Integer.parseInt(driver.findElement(generalPOM.getProductCountInOtherStore(i)).getText()) > 1) {
                    otherStoreIndex = i;
                    break;
                }

            }
            driver.findElement(generalPOM.getOtherStoresBtn(otherStoreIndex)).click();
            driver.findElement(orderPOM.getAddProductOtherStoreBtn()).click();
            driver.findElement(orderPOM.getOtherStoreProductsWindowCloseBtn()).click();
        }
    }

    @And("User change work status of creditor")
    public void userChangeWorkStatusOfCreditor() {
        creditorWorkStatus[0] = driver.findElement(orderPOM.getCreditorWorkStatus()).getText();
        driver.findElement(orderPOM.getCreditorWorkStatusBtn()).click();
        waitTextUpdate(orderPOM.getCreditorWorkStatus(), creditorWorkStatus[0], 5);
        creditorWorkStatus[1] = driver.findElement(orderPOM.getCreditorWorkStatus()).getText();
    }

    @Then("Work status should be changed")
    public void workStatusShouldBeChanged() {
        Assert.assertNotEquals(creditorWorkStatus[0], creditorWorkStatus[1]);
    }

    @And("User clicks {string} of a product")
    public void userClicksOfAProduct(String text) {
        driver.findElement(orderPOM.productsInfoBtn(text)).click();
    }

    @Then("Relative {string} should be displayed in new window")
    public void relativeInfoShouldBeDisplayedInNewWindow(String text) {
        List<WebElement> productInfo = driver.findElements(orderPOM.getProductInfoResult());
        for (WebElement element : productInfo){
            if (element.getText().equals(text)){
                Assert.assertEquals(element.getText(), text);
            }
        }
    }
}
