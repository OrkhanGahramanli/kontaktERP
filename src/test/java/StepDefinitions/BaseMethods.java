package StepDefinitions;

import DriverSession.CucumberHook;
import DriverSession.CucumberHook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseMethods {

    public WebDriver driver = CucumberHook.driver.get();

    public BaseMethods(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    protected void waitVisibilityLocator(By locator, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitVisibilityElement(WebElement element, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitClickableLocator(By locator, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitTextMessage(WebElement element, String message, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.textToBePresentInElement(element, message));
    }

    protected void waitTextUpdate(By locator, String value, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, value)));
    }

    protected void selectVisibleText(WebElement element, String text){
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    protected void clickWithAction(WebElement element){
        Actions actions = new Actions(driver);
        actions.click(element).perform();
    }

    protected JavascriptExecutor getJsExecutor(){
        return (JavascriptExecutor) driver;
    }

}
