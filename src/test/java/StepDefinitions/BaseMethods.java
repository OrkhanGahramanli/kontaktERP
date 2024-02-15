package StepDefinitions;

import DriverSession.CreateDriverSession;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseMethods {

//    static {
//        try {
//            Class<?> clazz = Class.forName("POM.GeneralPOM");
//            Object o = clazz.getDeclaredConstructor().newInstance();
//        }catch (Exception ignored){
//
//        }
//    }
    public WebDriver driver = CreateDriverSession.driver.get();

    public BaseMethods(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void waitVisibilityLocator(By locator, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitVisibilityElement(WebElement element, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitClickableLocator(By locator, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitTextMessage(WebElement element, String message, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.textToBePresentInElement(element, message));
    }

    public void waitTextUpdate(By locator, String value, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, value)));
    }

    public void selectVisibleText(WebElement element, String text){
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

}
