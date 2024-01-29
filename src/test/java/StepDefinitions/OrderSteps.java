package StepDefinitions;

import POM.OrderPOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

public class OrderSteps extends BaseMethods {

    OrderPOM orderPOM = OrderPOM.getInstance();

    String orderNum;
    String saleTypeValue;
    String[] creditorWorkStatus = new String[2];

    @When("User selects {string} store")
    public void userSelectsStore(String storeName) {
        WebElement store = driver.findElement(orderPOM.getStoreSelect());
        Select select = new Select(store);
        select.selectByVisibleText(storeName);
    }

    @And("User clicks order menu")
    public void UserClicksOrderMenu() {
        waitVisibilityLocator(orderPOM.getOrderMenu(),10);
        driver.findElement(orderPOM.getOrderMenu()).click();
    }

    @And("User clicks new order link")
    public void userClicksNewOrderLink() {
        driver.findElement(orderPOM.getNewOrderLink()).click();
    }

    @And("User add {string} code")
    public void userSelectsSeller(String sellerCode) {
        waitVisibilityLocator(orderPOM.getSellerSearchField(), 5);
        driver.findElement(orderPOM.getSellerSearchField()).sendKeys(sellerCode);
        if (!sellerCode.isEmpty()) driver.findElement(orderPOM.getSellerSearchBtn()).click();
    }

    @And("User add {string} product")
    public void userAddProduct(String product) {
        driver.findElement(orderPOM.getProductAreaExpand()).click();
        driver.findElement(orderPOM.getProductNameField()).sendKeys(product);
        if (!product.isEmpty()) {
            driver.findElement(orderPOM.getProductSearchBtn()).click();
            int productInStockIndex = 0;
            for (int i = 1; i < 11; i++) {
                String[] priceSplit = driver.findElement(orderPOM.getProductPrice(i)).getText().split("\\.");
                int price = Integer.parseInt(priceSplit[0]);
                if (price > 0
                        && Integer.parseInt(driver.findElement(orderPOM.getProductCount(i)).getText()) > 0) {
                    productInStockIndex = i;
                    break;
                }

            }
            driver.findElement(orderPOM.getAddProductBtn(productInStockIndex)).click();
        }
    }

    @And("User fills {string} field")
    public void userFillsCustomerNameField(String customer) {
        if (!customer.isEmpty()) driver.findElement(orderPOM.getCustomerNameField()).sendKeys(customer);
    }

    @And("User selects {string}")
    public void userSelectsSaleType(String saleType) {
        WebElement saleTypeElement = driver.findElement(orderPOM.getSaleType());
        Select select = new Select(saleTypeElement);
        select.selectByVisibleText(saleType);
       saleTypeValue = select.getFirstSelectedOption().getText();
    }

    @And("User clicks submit order button")
    public void userClicksSubmitOrderButton() {
        driver.findElement(orderPOM.getOrderSubmitBtn()).click();
    }

    @Then("New order should be create")
    public void newOrderShouldBeCreate() {
        String orderCreated = driver.findElement(orderPOM.getNewOrderCreateMessage()).getText();
        Assert.assertFalse(orderCreated.isEmpty());
        String[] createdOrderNum = orderCreated.split(" ");
        orderNum = createdOrderNum[0];
    }

    @Then("New created order should be in Web Orders list")
    public void newCreatedOrderShouldBeOrdersList() throws InterruptedException {
        waitVisibilityLocator(orderPOM.getNewCreatedOrderConfirmBtn(), 10);
        driver.findElement(orderPOM.getNewCreatedOrderConfirmBtn()).click();
        driver.findElement(orderPOM.getOnlineOrderMenu()).click();
        driver.findElement(orderPOM.getWebOrdersLink()).click();
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

    @And("User fills customer {string} field {string}")
    public void userFillsCustomerBirthDaterField(String customerBirthDate, String saleType) {
        if (saleTypeValue.equals(saleType)) driver.findElement(orderPOM.getCustomerBirthDate()).sendKeys(customerBirthDate);
    }

    @Then("User should get {string} message in new Order Page")
    public void userShouldGetMessageInNewOrderPage(String expectedError) {
        if (expectedError.equals("Məhsul seçilməyib.")){
            List<WebElement> list = driver.findElements(orderPOM.getProductEmptyErrorMessage());
            waitTextMessage(list.getLast(), expectedError, 5);
            String actualError = list.getLast().getText();
            Assert.assertEquals(actualError, expectedError);
        }else {
            List<WebElement> list = driver.findElements(orderPOM.getErrorMessage());
            waitTextMessage(list.getLast(), expectedError, 5);
            String actualError = list.getLast().getText();
            Assert.assertEquals(actualError, expectedError);
        }
    }

    @And("User add {string} service in order")
    public void userAddServiceInOrder(String service) {
        driver.findElement(orderPOM.getSelectServiceBtn()).click();
        driver.findElement(orderPOM.getServiceSearchField()).sendKeys(service);
        driver.findElement(orderPOM.getAddServiceBtn()).click();
        driver.findElement(orderPOM.getServicesWindowCloseBtn()).click();
    }

    @And("User add Bundle in order")
    public void userAddBundleInOrder() {
        driver.findElement(orderPOM.getSelectBundleBtn()).click();
        waitVisibilityLocator(orderPOM.getAddBundleBtn(), 10);
        driver.findElement(orderPOM.getAddBundleBtn()).click();
        Actions actions = new Actions(driver);
        actions.click(driver.findElement(orderPOM.getBundlesWindowCloseBtn()));
    }

    @Then("Products and services should be visible in new order")
    public void productsAndServicesShouldBeVisibleInNewOrder() throws InterruptedException {
        List<WebElement> productsBeforeCreateOrder = driver.findElements(orderPOM.getProductsCodeBeforeCreate());
        List<String> expectedProducts = new ArrayList<>();
        for (WebElement element : productsBeforeCreateOrder){
            expectedProducts.add(element.getText());
        }
        waitVisibilityLocator(orderPOM.getNewCreatedOrderConfirmBtn(), 10);
        driver.findElement(orderPOM.getNewCreatedOrderConfirmBtn()).click();
        driver.findElement(orderPOM.getOnlineOrderMenu()).click();
        driver.findElement(orderPOM.getWebOrdersLink()).click();
        driver.findElement(orderPOM.getWebOrderNumSearchField()).sendKeys(orderNum);
        Thread.sleep(2000);
        driver.findElement(orderPOM.getCreatedOrderDetailsBtn()).click();
        waitVisibilityLocator(orderPOM.getProductsCodeAfterCreate(),10);
        List<WebElement> productsAfterCreateOrder = driver.findElements(orderPOM.getProductsCodeAfterCreate());
        List<String> actualProducts = new ArrayList<>();
        for (WebElement element : productsAfterCreateOrder){
            actualProducts.add(element.getAttribute("value"));
        }
        Collections.sort(actualProducts);
        Collections.sort(expectedProducts);
        Assert.assertEquals(actualProducts, expectedProducts);
    }

    @Then("Total amount should be sum of all prices")
    public void totalAmountShouldBeSumOfAllPrices() {
    List<WebElement> productsPrices = driver.findElements(orderPOM.getAddedProductsPrices());
    List<WebElement> productsDiscounts = driver.findElements(orderPOM.getAddedProductsDiscounts());

    double productsPricesSum = 0.00d;
    for (WebElement element : productsPrices){
        productsPricesSum += Double.parseDouble(element.getAttribute("value"));
    }
    double productsDiscountsSum = 0.00d;
    for (WebElement element : productsDiscounts){
        if (!element.getAttribute("value").isEmpty()) productsDiscountsSum += Double.parseDouble(element.getAttribute("value"));
        }

    WebElement productsAmount = driver.findElement(orderPOM.getProductsAmount());
    WebElement productsDiscount = driver.findElement(orderPOM.getProductsDiscount());
    WebElement productsTotalAmount = driver.findElement(orderPOM.getProductsTotalAmount());

    Assert.assertEquals(Double.parseDouble(productsAmount.getText()), Math.round(productsPricesSum*100.0)/100.0);
    Assert.assertEquals(Double.parseDouble(productsDiscount.getText()), Math.round(productsDiscountsSum*100.0)/100.0);
    Assert.assertEquals(Double.parseDouble(productsTotalAmount.getText()), Math.round((productsPricesSum-productsDiscountsSum)*100.0)/100.0);
    }

    @And("User clicks bundle select button")
    public void userClicksBundleSelectButton() {
        driver.findElement(orderPOM.getSelectBundleBtn()).click();
    }

    @And("User clicks on button to see products in bundle")
    public void userClicksOnButtonToSeeProductsInBundle() {
        driver.findElement(orderPOM.getBundleDetailsBtn()).click();
    }

    @Then("Products should be displayed in bundle")
    public void productsShouldBeDisplayedInBundle() {
        waitVisibilityLocator(orderPOM.getProductInBundle(), 10);
        Assert.assertFalse(driver.findElement(orderPOM.getProductInBundle()).getText().isEmpty());
    }

    @And("User add {string} product from different store")
    public void userAddProductFromDifferentStore(String product) {
        driver.findElement(orderPOM.getProductAreaExpand()).click();
        driver.findElement(orderPOM.getProductNameField()).sendKeys(product);
        if (!product.isEmpty()) {
            driver.findElement(orderPOM.getProductSearchBtn()).click();
            int otherStoreIndex = 0;
            for (int i = 1; i < 11; i++) {
                String[] priceSplit = driver.findElement(orderPOM.getProductPrice(i)).getText().split("\\.");
                int price = Integer.parseInt(priceSplit[0]);
                if (price > 0
                        && Integer.parseInt(driver.findElement(orderPOM.getProductCountInOtherStore(i)).getText()) > 1) {
                    otherStoreIndex = i;
                    break;
                }

            }
            driver.findElement(orderPOM.getOtherStoresBtn(otherStoreIndex)).click();
            driver.findElement(orderPOM.getAddProductOtherStoreBtn()).click();
            driver.findElement(orderPOM.getOtherStoreProductsWindowCloseBtn()).click();
        }
    }

    @And("User selects {string} delivery type of the product")
    public void userSelectsDeliveryTypeOfTheProduct(String deliveryType) {
        waitVisibilityLocator(orderPOM.getProductDeliveryType(), 10);
        Select select = new Select(driver.findElement(orderPOM.getProductDeliveryType()));
        select.selectByVisibleText(deliveryType);
    }

    @And("User directs to Creditors page")
    public void userDirectsToCreditorsPage() {
        driver.findElement(orderPOM.getCreditorsMenuLink()).click();
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
}
