package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pom.GeneralPOM;
import pom.OnlineOrderPOM;

import java.util.List;

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
}
