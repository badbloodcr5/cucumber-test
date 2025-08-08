package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import page.LoginPage;
import org.junit.Assert.*;
import utility.DriverFactory;

public class LoginSteps {
    LoginPage loginPage = new LoginPage(DriverFactory.getCurrentDriver());

    @Given("user is on login page")
    public void user_on_login_page() {
        loginPage.openLoginPage();
    }

    @When("user enters username {string} and password {string}")
    public void enter_credentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @Then("user should be redirected to home page")
    public void user_should_be_redirected_to_home_page() {
        // Write code here that turns the phrase above into concrete actions
    	Assert.assertTrue("Login is success",loginPage.verifyLoginSuccess());
    }



}
