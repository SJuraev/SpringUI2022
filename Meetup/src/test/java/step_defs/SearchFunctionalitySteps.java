package step_defs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.MainPage;
import utilies.Driver;

public class SearchFunctionalitySteps {
    WebDriver driver = Driver.getDriver();


    MainPage mainPage = new MainPage();
    //Grayslake Bicycle Slow Roll 🚲
    @When("^the user searches for \"([^\"]*)\" in \"([^\"]*)\"$")
    public void the_user_searches_for_in(String event, String location){
        mainPage.search(event, location);
    }

    @Then("^verifies the result set contains search criteria in the title$")
    public void verifies_the_result_set_contains_search_criteria_in_the_title(){
        WebElement grayslakeLink = driver.findElement(By.xpath("//h2[contains(text(),'Grayslake Bicycle')]"));
        grayslakeLink.isDisplayed();
    }

}
