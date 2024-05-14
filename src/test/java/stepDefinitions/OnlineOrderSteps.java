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

    @Then("Forward date should be visible in {string}")
    public void forwardDateShouldBeVisibleIn(String element) {
        String[] forwardDate = driver.findElement(elementsMap.get(element)).getAttribute("innerText").split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Assert.assertEquals(forwardDate[0], LocalDate.now().format(formatter));
    }

    @Then("Forward by user name should be visible in {string}")
    public void forwardByUserNameShouldBeVisibleIn(String element) {
        Assert.assertTrue(driver.findElement(elementsMap.get(element)).getAttribute("innerText").equalsIgnoreCase(LoginSteps.getUserName()));
    }
}
