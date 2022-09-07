package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilies.Driver;

public class JoinMeetupPage {

    public JoinMeetupPage(){
        PageFactory.initElements(Driver.getDriver(), this);

    }

    @FindBy(id = "facebook-register")
    public WebElement continueWithFacebookButton;

    @FindBy(id = "google-register")
    public WebElement continueWithGoogleButton;

    @FindBy(id = "apple-register")
    public WebElement continueWithAppleButton;

    @FindBy(id = "email-register")
    public WebElement continueWithEmailButton;




}
