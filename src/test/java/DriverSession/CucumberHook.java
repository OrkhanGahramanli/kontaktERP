package DriverSession;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class CucumberHook {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static ThreadLocal<String>  pomName = new ThreadLocal<>();


    @Before
    public static void beforeScenario(Scenario scenario){
        pomName.set(FilenameUtils.getBaseName(scenario.getUri().toString()));
        try {
            Class<?> clazz = Class.forName("POM." + pomName.get() + "POM");
            Object o = clazz.getDeclaredConstructor().newInstance();
        }catch (Exception ignored){

        }
        driver.set(new ChromeDriver());
        driver.get().get("https://test.abc-telecom.az:5067/");
        driver.get().manage().window().maximize();
    }

    @After
    public static void afterScenario(Scenario scenario) throws InterruptedException {
        if(scenario.isFailed()) {
            Thread.sleep(300);
            final byte[] screenshot = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        driver.get().quit();
    }
}
