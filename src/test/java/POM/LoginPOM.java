package POM;

import lombok.Data;
import org.openqa.selenium.By;

@Data
public class LoginPOM {

    private LoginPOM(){

    }

    private static LoginPOM INSTANCE;

    public static LoginPOM getInstance(){
        if (INSTANCE == null){
            INSTANCE = new LoginPOM();
            return INSTANCE;
        }
        return INSTANCE;
    }

    private By username = By.id("Username");
    private By password = By.id("Password");
    private By loginBtn = By.xpath("//*[@type='submit']");
    private By loggedInUserName = By.className("ml-4");
    private By loginFailedErrorMessage = By.className("validation-summary-errors");
    private By languageSelect = By.name("lang");
}