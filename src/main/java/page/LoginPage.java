package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.DriverFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import java.time.Duration;

public class LoginPage {
    //WebDriver driver = DriverFactory.getDriver();
	private WebDriver driver;
	
	//Constructor
	public LoginPage(WebDriver passedDriver)
	{
		this.driver = passedDriver;
	}
	
	// Declare elements
    By username = By.id("user-name");
    By password = By.id("password");
    By loginBtn = By.id("login-button");

    public void openLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    public void enterUsername(String uname) {

        driver.findElement(username).sendKeys(uname);
    }

    public void enterPassword(String pwd) {
        driver.findElement(password).sendKeys(pwd);
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    public boolean verifyLoginSuccess()
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("app_logo")));
            return logo.isDisplayed();
        }
        catch (Exception e)
        {
            System.out.println("Error is: " + e);
            return false;
        }
    }
}
