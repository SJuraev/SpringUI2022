package step_defs;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import utilies.Driver;
import utilies.EnvironmentManager;

public class LoginSteps {

    WebDriver driver = Driver.getDriver();
    MainPage mainPage = new MainPage();


    @Then("^user should be able to see \"([^\"]*)\" button$")
    public void userShouldBeAbleToSeeButton(String button){
        if(button.equalsIgnoreCase("Log in")){
            Assert.assertTrue("Button is not displayed", mainPage.loginButton.isDisplayed());
        }else
            if(button.equalsIgnoreCase("Sign up")){
                Assert.assertTrue("Button is not displayed", mainPage.signUpButton.isDisplayed());
            }else
                if(button.equalsIgnoreCase("Join Meetup")){
                    Assert.assertTrue("Button is not displayed", mainPage.joinMeetupButton.isDisplayed());
                }
    }



}
