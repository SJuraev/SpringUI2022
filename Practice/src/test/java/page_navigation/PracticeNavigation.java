package page_navigation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.concurrent.TimeUnit;

public class PracticeNavigation {

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
    public void practicePageNavigation(){
        //same as driver.get();
        driver.navigate().to("https://www.amazon.com/");

        WebElement bestSellerLink = driver.findElement(By.xpath("//a[@href=\"/gp/bestsellers/?ref_=nav_cs_bestsellers\"]"));
        bestSellerLink.click();
        //here we would expect StaleElementReferenceException
//        WebElement amazonBestSellers = driver.findElement(By.cssSelector("#zg_banner_text"));
//        Assert.assertTrue(amazonBestSellers.isDisplayed());
        bestSellerLink = driver.findElement(By.xpath("//a[@href=\"/gp/bestsellers/?ref_=nav_cs_bestsellers\"]"));
        Assert.assertEquals("nav-a  ", bestSellerLink.getAttribute("class"));

        //in order to navigate backwards
        driver.navigate().back();

        //verify x is selected by default
        WebElement backToSchool = driver.findElement(By.xpath("//a[@href=\"/backtoschool?ref_=nav_cs_bts\"]"));

        Assert.assertTrue(backToSchool.getAttribute("class").equals("nav-a  "));

        //let's navigate forward
        driver.navigate().forward();

        WebElement amazonBestSellers = driver.findElement(By.cssSelector("#zg_banner_text"));
        Assert.assertTrue(amazonBestSellers.isDisplayed());

        WebElement nameInput = driver.findElement(By.xpath("//input[@name=\"field-keywords\"]"));
        nameInput.sendKeys("DevX");

        driver.navigate().refresh();

        nameInput = driver.findElement(By.xpath("//input[@name=\"field-keywords\"]"));
        nameInput.sendKeys("School");

    }



}
