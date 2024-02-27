import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@CucumberOptions(
        features = {"classpath:features"},
        plugin = {"pretty", "json:target/cucumber.json"},
        tags = "@InvalidSmsCode"
)


public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterTest
    public void afterTest(){
        File reportOutputDirectory = new File("target");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber.json");
        Configuration configuration = new Configuration(reportOutputDirectory, "KontaktERP");
        configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
