package advanced_locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class PracticeXPath2 {
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

    /*Username : Admin | Password : admin123
    Go to https://opensource-demo.orangehrmlive.com/

     */
    @Test
    public void test3() throws InterruptedException{
        driver.get("https://opensource-demo.orangehrmlive.com/");

        driver.findElement(By.xpath("//input[@name='txtUsername']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys("admin123");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(3000);

        String welcomeText = driver.findElement(By.xpath("//a[starts-with(text(),'Welcome')]")).getText();

        Assert.assertTrue("Welcome text contains wrong name ",welcomeText.contains("Paul"));

    }
}
