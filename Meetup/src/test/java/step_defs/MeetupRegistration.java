package step_defs;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
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

public class MeetupRegistration {
    WebDriver driver= Driver.getDriver();
    MainPage mainPage = new MainPage();

    @And("^user should click on \"([^\"]*)\" button$")
    public void user_should_click_on_button(String button){
        mainPage.clickJoinMeetup();
    }

    @Then("^user should be able to sign up with \"([^\"]*)\"$")
    public void userShouldBeAbleToSignUpWith(String register){
        WebElement signUpButton = driver.findElement(By.id(register));
        Assert.assertTrue("User is not able to sign up with " + register,
                signUpButton.isDisplayed());
    }
}
