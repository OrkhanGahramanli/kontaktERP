package driverSession;

import io.cucumber.java.*;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class CucumberHook {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static ThreadLocal<String>  pomName = new ThreadLocal<>();
    static ChromeOptions options;

    @BeforeAll
    public static void setup() {
        options = new ChromeOptions();
        options.addArguments("--headless=new");
    }

    @Before
    public static void beforeScenario(Scenario scenario){
        pomName.set(FilenameUtils.getBaseName(scenario.getUri().toString()));
        try {
            if (pomName.get().equals("Payment")){
                Class<?> clazz = Class.forName("pom." + pomName.get() + "POM");
                Class<?> saleClass = Class.forName("pom." + "SalePOM");
                Object o = clazz.getDeclaredConstructor().newInstance();
                Object saleObject = saleClass.getDeclaredConstructor().newInstance();
            }
            Class<?> clazz = Class.forName("pom." + pomName.get() + "POM");
            Object o = clazz.getDeclaredConstructor().newInstance();
        }catch (Exception ignored){

        }
        driver.set(new ChromeDriver(options));
        driver.get().get("https://test.abc-telecom.az:5067/");
        driver.get().manage().window().maximize();
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

//    @After
//    public static void afterScenario(Scenario scenario) throws InterruptedException {
//        if(scenario.isFailed()) {
//            Thread.sleep(300);
//            final byte[] screenshot = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshot, "image/png", scenario.getName());
//        }
//        driver.get().quit();
//    }
}
