package stepDefinitions;

import pom.GeneralPOM;
import pom.OrderPOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @And("User change work status of creditor")
    public void userChangeWorkStatusOfCreditor() {
        creditorWorkStatus[0] = driver.findElement(orderPOM.getCreditorWorkStatus()).getText();
        driver.findElement(orderPOM.getCreditorWorkStatusBtn()).click();
        waitTextUpdate(orderPOM.getCreditorWorkStatus(), creditorWorkStatus[0], 10);
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

    @And("Collect product names for expected result")
    public void collectProductNamesForExpectedResult() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> productsBeforeCreateOrder = driver.findElements(orderPOM.getProductsCodeBeforeCreate());
        expectedProducts = new ArrayList<>();
        for (WebElement element : productsBeforeCreateOrder){
            expectedProducts.add(element.getText());
        }
    }
}
