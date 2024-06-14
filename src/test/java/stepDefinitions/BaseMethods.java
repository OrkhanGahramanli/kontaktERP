package stepDefinitions;

import driverSession.CucumberHook;
import io.cucumber.java.en_old.Ac;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BaseMethods {

    public WebDriver driver = CucumberHook.driver.get();

    public BaseMethods(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    protected void waitVisibilityElement(By locator, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitVisibilityElement(WebElement element, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitVisibilityElements(By locator, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected void waitClickableElement(By locator, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitClickableElement(WebElement element, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitTextMessage(By locator, String message, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.textToBe(locator, message));
    }

    protected void waitPresenceElement(By locator, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waitPresenceElements(By locator, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
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

    protected void moveToElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    protected WebElement selectElementByText(String text) {
        return driver.findElement(By.xpath("//*[@class='selectize-dropdown-content']//*[text()='" + text + "']"));
    }

    protected WebElement selectElementByText2(String text) {
        return driver.findElement(By.xpath("//*[contains(@class, 'dx-list-item-content')][text()='" + text + "']"));
    }

    protected WebElement findElementByText(String text) {
        return driver.findElement(By.xpath("//*[text()='" + text + "']"));
    }
    protected List<WebElement> findElementsByText(String text) {
        return driver.findElements(By.xpath("//*[text()='" + text + "']"));
    }


    protected void clearFieldWithAction(By locator){
        driver.findElement(locator).click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.CONTROL + "a");
        actions.sendKeys(Keys.DELETE);
    }

    protected void clearFieldWithBackspace(WebElement element){
        element.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.BACK_SPACE).perform();
    }

    protected  void doubleClickAction(WebElement element){
        Actions actions = new Actions(driver);
        actions.doubleClick(element).build().perform();
    }

    protected WebElement getSelectedOption(WebElement element){
        Select select = new Select(element);
        return select.getFirstSelectedOption();
    }

    protected void enterAction(){
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    protected void fillWithAction(WebElement element, String text){
        Actions actions = new Actions(driver);
        actions.click(element).perform();
        actions.sendKeys(text).perform();
    }
}
