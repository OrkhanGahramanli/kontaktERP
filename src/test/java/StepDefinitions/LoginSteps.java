package StepDefinitions;

import POM.LoginPOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class LoginSteps extends BaseMethods{

    LoginPOM loginPOM = LoginPOM.getInstance();


    @When("User fills input username field with {string}")
    public void userFillsInputUsernameFieldWith(String username) {
        driver.findElement(loginPOM.getUsername()).sendKeys(username);
    }

    @And("User fills input password field with {string}")
    public void userFillsInputPasswordFieldWith(String password) {
        driver.findElement(loginPOM.getPassword()).sendKeys(password);
    }

    @And("User selects {string} language")
    public void userSelects(String value) {
        WebElement language = driver.findElement(loginPOM.getLanguageSelect());
        Select select = new Select(language);
        select.selectByValue(value);
    }

    @And("User clicks on submit button")
    public void userClicksOnSubmitButton() {
        driver.findElement(loginPOM.getLoginBtn()).click();
    }

    @Then("User should navigate to Home Page")
    public void userShouldNavigateToHomePage() {
       WebElement loggedInUserName = driver.findElement(loginPOM.getLoggedInUserName());
        Assert.assertFalse(loggedInUserName.getText().isEmpty());
    }

    @Then("User should get {string} message in Login Page")
    public void userShouldGetMessage(String error) {
        WebElement errorMessage = driver.findElement(loginPOM.getLoginFailedErrorMessage());
        Assert.assertEquals(errorMessage.getText(), error);
    }

    @Then("Home Page language should be in {string}")
    public void homePageLanguageShouldBe(String value) {
        String homePageURL = driver.getCurrentUrl().substring(driver.getCurrentUrl().length()-2);
        Assert.assertEquals(homePageURL, value);
    }
}
