package javascript;

import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilies.Driver;
import utilies.Helper;

public class PracticeJavaScript {
    WebDriver driver;

    @Before
    public void setUp(){
        driver = Driver.getDriver();
    }

    @After
    public void tearDown(){
        Driver.closeDriver();
    }

    @Test
    public void etsyTest() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("window.location='https://www.etsy.com/'");

        WebElement singInButton = driver.findElement(By.cssSelector(".select-signin"));

        //js.executeScript("arguments[0].setAttribute('style', arguments[1]);",singInButton, "border: 2px solid blue");
        //Thread.sleep(2000);
        Helper.highlightElement(singInButton);

        js.executeScript("arguments[0].click();",singInButton);
        Thread.sleep(3000);

//        js.executeScript("document.getElementById('join_neu_email_field').setAttribute('style', arguments[0]);",
//                "border: 2px solid purple");
//        Thread.sleep(2000);

        Helper.highlightElement("join_neu_email_field");

        Faker faker = new Faker();
        String fakeEmail = faker.internet().emailAddress();
        String fakePassword = faker.internet().password();

        js.executeScript("document.getElementById('join_neu_email_field').value=arguments[0];", fakeEmail);

        //join_neu_password_field

//        js.executeScript("document.getElementById('join_neu_password_field').setAttribute('style', arguments[0]);",
//                "border: 2px solid yellow");
//        Thread.sleep(2000);
        Helper.highlightElement("join_neu_password_field");

        js.executeScript("document.getElementById('join_neu_password_field').value=arguments[0];", fakePassword);

        //button[@value='sign-in']

        WebElement submitButton = driver.findElement(By.xpath("//button[@value='sign-in']"));
        Helper.highlightElement(submitButton);
//        js.executeScript("arguments[0].setAttribute('style',arguments[1]);", submitButton, "border: 2px solid fuxia");
//        Thread.sleep(2000);
        js.executeScript("arguments[0].click();", submitButton);

        Thread.sleep(3000);
    }



}
