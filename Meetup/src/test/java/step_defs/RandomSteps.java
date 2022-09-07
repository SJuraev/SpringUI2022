package step_defs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utilies.Driver;

import java.util.Locale;

public class RandomSteps {

    WebDriver driver = Driver.getDriver();

    @When("^the user navigates to google$")
    public void the_user_navigates_to_google(){
        driver.navigate().to("https://www.google.com/");
    }

    @When("^the user inputs \"([^\"]*)\" in search window$")
    public void the_user_inputs_in_search_window(String input){
        driver.findElement(By.name("q")).sendKeys(input + Keys.ENTER);
    }

    @Then("^verifies \"([^\"]*)\" is in the title of the page$")
    public void verifies_is_in_the_title_of_the_page(String input) {
        Assert.assertTrue(driver.getTitle().toLowerCase(Locale.ROOT).contains(input));
    }
}
