package advanced_locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PracticeAdvancedLocators {
    WebDriver driver;

    @Before
    public void setUp(){
        //this is a set-up method for every single test annotation
        //we initialize the driver
        //maximize  the window
        //set timeout
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void test1(){
        //Try out findElements() method
        //after driver.get() we don't need Thread.sleep();
        //.get() method automatically waits for the web page to fully load all the web elements
        driver.get("https://devxschool.com/");

        //print out the text of the first link on the page
       WebElement firstLink = driver.findElement(By.tagName("a"));
        System.out.println("First link is: " + firstLink.getAttribute("href"));

        //how to print out text of every single link on the page

        List<WebElement> links = driver.findElements(By.tagName("a"));

        for (WebElement link : links){
            System.out.println(link.getAttribute("href"));
        }



    }
}
