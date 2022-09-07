package advanced_locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PracticeCSSSelector {
    /*
    Task#2- practice CSS selector
    1. Go to https://www.amazon.com/
    2. Type "iphone" in a search field
    3. Hit the search
    4. Once on the result page, print out every single brand name
    (Brand section on the left with the list of brands as checkboxes)
     */
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
    public void test1() throws InterruptedException{
        //Try out findElements() method
        //after driver.get() we don't need Thread.sleep();
        //.get() method automatically waits for the web page to fully load all the web elements
        driver.get("https://www.amazon.com/");

        //Find Search field using CSS selector
        driver.findElement(By.cssSelector("input[id$='tbox']")).
                sendKeys("iphone"+Keys.ENTER);//Type "iphone" in a search field; Hit the search
        Thread.sleep(3000);

       // driver.findElement(By.cssSelector("a[aria-label='See more, Brand']")).click();

        //Find & print out Brands
        List<WebElement> brands = driver.findElements(By.cssSelector("li[id^='p_89/']"));

        for (WebElement eachBrand : brands){
            System.out.println(eachBrand.getText());
        }
        //Once on the result page, print out every single brand name
        //(Brand section on the left with the list of brands as checkboxes)


    }
}
