package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utilies.Driver;

import java.util.List;

public class MainPage {
    //in this class we will store all the elements from the main page of Meetup

    //don't add all the elements right away
    //instead, create your scenarios first and then add web elements, when you need them

    //In our page class - we always put constructor first, then we put all our "WebElement"s(like loginButton etc.,)
    //And the basic methods are at the bottom
    public MainPage(){
        //this line will initialize all the web elements on this page
        PageFactory.initElements(Driver.getDriver(),this);
    }

    //here we will add all the elements that belong to the main page
    //before every single web element we need to tell how to find it
    //in order to do that we use - @FindBy annotation (xpath = "your xpath here")


    @FindBy(css = "#login-link")
    public WebElement loginButton;

    @FindBy(css = "#register-link")
    public WebElement signUpButton;

    @FindBy(xpath = "//a[contains(text(),'Join Meetup')]")
    public WebElement joinMeetupButton;

    @FindBy(id = "search-keyword-input")
    public WebElement searchInputField;

    //Here you can add more elements that you are using in your tests
    @FindBy(xpath = "//input[@type='submit']")
    public WebElement searchButton;

    @FindBy(xpath = "//input[@aria-label='Search for location by city or zip code]'")
    public WebElement locationInputField;

    @FindBys({//will return the element that matches all the locators listed below
            @FindBy(xpath = "//a[@aria-label='Online']"),
            @FindBy(xpath = "//a[@href='/find/?source=EVENTS&eventType=online']"),
            @FindBy(xpath = "//a[@data-event-label='Online filter']"),
            @FindBy(xpath = "//h2[contains(text(),'See what’s happening')]/ ../div/div[2]/a[1]")
    })
    public WebElement onlineLink;

    @FindAll({//@FindAll will return the first one it find from the list
            @FindBy(xpath = "//a[@aria-label='Online']"),
            @FindBy(xpath = "//a[@href='/find/?source=EVENTS&eventType=online']")
    })
    public List<WebElement> someLink;

    //In the page class you can add basic methods that you perform on the elements of this page

    public void clickJoinMeetup(){
        //We are adding this method, because we will need explicit waits before performing an action very often
        joinMeetupButton.click();
    }

    public void search(String searchCriteria, String location){
        searchInputField.sendKeys(searchCriteria);
        if(location  == null){
            //locationInputField.clear();
            //locationInputField.sendKeys(Keys.CLEAR);
            //locationInputField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
            locationInputField.sendKeys(location);
        }

        searchButton.click();
    }


}
