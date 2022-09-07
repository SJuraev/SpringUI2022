package step_defs;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import utilies.Driver;
import utilies.EnvironmentManager;

public class BasicValidationSteps {

    WebDriver driver = Driver.getDriver();

    @Given("^the user navigates to Meetup homepage$")
    public void theUserNavigatesToMeetupHomepage() {
        driver.get(EnvironmentManager.baseUrl);
    }

    @Then("^verifies the title contains \"([^\"]*)\"$")
    public void verifiesTheTitleContains(String expectedTitle){
        // Write code here that turns the phrase above into concrete actions
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle));

    }

    @Then("^verify the url contains \"([^\"]*)\"$")
    public void verifyTheUrlContains(String expectedURL){
        // Write code here that turns the phrase above into concrete actions
      String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(expectedURL, actualURL);
    }



}
