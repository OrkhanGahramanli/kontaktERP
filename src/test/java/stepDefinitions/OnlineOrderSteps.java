package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pom.GeneralPOM;
import pom.OnlineOrderPOM;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import static pom.ElementsMap.elementsMap;

public class OnlineOrderSteps extends BaseMethods{

    OnlineOrderPOM onlineOrderPOM = OnlineOrderPOM.getInstance();
    GeneralPOM generalPOM = GeneralPOM.getInstance();

    String onlineOrderNum;

    @And("User selects {string} radioButton")
    public void userSelectsRadioButton(String radioButton) {
        switch (radioButton) {
            case "Nağd": driver.findElement(onlineOrderPOM.getCashRadioBtn()).click();
            break;

            case "Kredit": driver.findElement(onlineOrderPOM.getCreditRadioBtn()).click();
            break;
        }
    }

    @Then("New online order should be create")
    public void newOnlineOrderShouldBeCreate() {
        WebElement orderCreated = driver.findElement(generalPOM.getSuccessIcon());
        Assert.assertTrue(orderCreated.isDisplayed());
    }

    @And("User takes online order number")
    public void userTakesOnlineOrderNumber() {
        String createdOrderMessage = driver.findElement(generalPOM.getCompleteNotificationText()).getText();
        String[] createdOrderNum = createdOrderMessage.split(" ");
        onlineOrderNum = createdOrderNum[(createdOrderNum.length) - 1];
    }

    @And("User fills online order number in search field")
    public void userFillsOnlineOrderNumberInSearchField() {
        driver.findElement(onlineOrderPOM.getOnlineOrderSearchField()).sendKeys(onlineOrderNum);
    }

    @Then("Created online order should be displayed")
    public void createdOnlineOrderShouldBeDisplayed() {
        Assert.assertEquals(driver.findElement(onlineOrderPOM.getOnlineOrderNumColumn()).getText(), onlineOrderNum);
    }

    @Then("Online order number should be visible in {string}")
    public void onlineOrderNumberShouldBeVisibleIn(String orderNum) {
        Assert.assertEquals(driver.findElement(elementsMap.get(orderNum)).getAttribute("value"), onlineOrderNum);
    }

    @Then("{string} and {string} should be displayed in {string}")
    public void andShouldBeDisplayedIn(String product1, String product2, String element) {
        List<WebElement> productElements = driver.findElements(elementsMap.get(element));
        for (WebElement productElement : productElements) {
            if (productElement.getAttribute("value").equals(product1)) {
                Assert.assertTrue(true);
            } else if (productElement.getAttribute("value").equals(product2)) {
                Assert.assertTrue(true);
            } else Assert.fail();
        }

    }

    @Then("{string} should be displayed in {string}")
    public void shouldBeDisplayedIn(String text, String element) {
        Assert.assertEquals(driver.findElement(elementsMap.get(element)).getText(), text);
    }

    @Then("User should be navigated to {string} page in new tab")
    public void userShouldBeNavigatedToInNewTab(String text) {
        Set<String> tabs = driver.getWindowHandles();
        driver.switchTo().window(tabs.toArray()[1].toString());
        Assert.assertEquals(driver.findElement(generalPOM.getPageTitle()).getText(), text);
    }

    @Then("{int} order should be displayed in {string}")
    public void orderShouldBeDisplayedInBasket(int num, String element) {
        Assert.assertEquals(driver.findElement(elementsMap.get(element)).getText(),"Səbət (" + num + ")");
    }

    @Then("Online order should be displayed in basket")
    public void onlineOrderShouldBeDisplayedInBasket() {
        waitVisibilityElement(onlineOrderPOM.getOnlineOrderNumInBasket(), 5);
        Assert.assertEquals(driver.findElement(onlineOrderPOM.getOnlineOrderNumInBasket()).getText(), onlineOrderNum);
    }

    @Then("OrderNum {string} message should be displayed")
    public void userShouldGetMessage(String text) {
        Assert.assertEquals(driver.findElement(onlineOrderPOM.getReassignOrderCompleteMessage()).getText()
                                        , onlineOrderNum + " " + text);
    }

    @Then("Online order datagrid should be empty")
    public void onlineOrderDatagridShouldBeEmpty() {
        Assert.assertTrue(driver.findElement(onlineOrderPOM.getEmptyDataGrid()).isDisplayed());
    }

    @Then("Current date should be displayed in {string}")
    public void forwardDateShouldBeVisibleIn(String element) {
        WebElement date = driver.findElement(elementsMap.get(element));
        if (!date.getAttribute("innerText").isEmpty()){
            String[] forwardDate = date.getAttribute("innerText").split(" ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            Assert.assertEquals(forwardDate[0], LocalDate.now().format(formatter));
        }
        else {
            String[] forwardDate = date.getAttribute("value").split(" ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            Assert.assertEquals(forwardDate[0], LocalDate.now().format(formatter));
        }
    }

    @Then("Logged in user name should be displayed in {string}")
    public void forwardByUserNameShouldBeVisibleIn(String element) {
        WebElement userName = driver.findElement(elementsMap.get(element));
        if (!userName.getAttribute("innerText").isEmpty()){
            Assert.assertTrue(userName.getAttribute("innerText").equalsIgnoreCase(LoginSteps.getUserName()));
        }
        else {
            Assert.assertTrue(userName.getAttribute("value").equalsIgnoreCase(LoginSteps.getUserName()));
        }
    }

    @And("User add {string} product in online order")
    public void userAddProductInOnlineOrder(String productName) {
        if (!productName.isEmpty()){
            driver.findElement(elementsMap.get("productSelectBtn")).click();
            driver.findElement(elementsMap.get("productSearchByName")).sendKeys(productName);
            enterAction();
            findElementByText("Əlavə et").click();
        }
    }

    @And("User double clicks {string} element")
    public void userDoubleClicksElement(String element) {
        doubleClickAction(driver.findElement(elementsMap.get(element)));
    }

    @Then("Success icon should be displayed")
    public void successIconShouldBeDisplayed() {
        Assert.assertTrue(driver.findElement(generalPOM.getSuccessIcon()).isDisplayed());
    }

    @And("User selects {string} status from {string}")
    public void userSelectsStatusFrom(String text, String element) {
        driver.findElement(elementsMap.get(element)).click();
        driver.findElement(elementsMap.get(element)).sendKeys(text);
        selectElementByText2(text).click();
    }

    @And("{string} status should be selected in {string}")
    public void statusShouldBeSelected(String text, String element) {
        driver.findElement(elementsMap.get(element)).click();
        WebElement status = selectElementByText2(text);
        WebElement statusParentNode = (WebElement) getJsExecutor().executeScript(
                "return arguments[0].parentNode;", status);
        Assert.assertTrue(statusParentNode.getAttribute("class").contains("selected"));
    }
}
