package iframesAndAlerts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;
import utilities.HelperMethods;

public class PracticeIframes {
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
    public void test1() throws InterruptedException {
        driver.navigate().to("https://demoqa.com/frames");
        //before checking if text1 is displayed we need to switch to the iframe that contains if

        //1st option => Switch to iframe by WebElement
        WebElement iframe1 = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(iframe1);
        WebElement text1 = driver.findElement(By.id("sampleHeading"));

        HelperMethods.highlightElement(text1);

        Assert.assertTrue(text1.isDisplayed());

        driver.switchTo().defaultContent();
        //Switch to Iframe by name or ID
        driver.switchTo().frame("frame2");

        WebElement text2 = driver.findElement(By.id("sampleHeading"));

        HelperMethods.highlightElement(text2);

        Assert.assertTrue(text2.isDisplayed());
    }

    @Test
    public void test2() throws InterruptedException {
        driver.navigate().to("https://demoqa.com/frames");

        //1st option => Switch to iframe by WebElement
        WebElement iframe1 = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(iframe1);
        WebElement text1 = driver.findElement(By.id("sampleHeading"));

        Assert.assertTrue(text1.isDisplayed());

        driver.switchTo().defaultContent();
        //3rd Option - switch to iframe by an index
        driver.switchTo().frame(1);

        WebElement text2 = driver.findElement(By.id("sampleHeading"));

        HelperMethods.highlightElement(text2);

        Assert.assertTrue(text2.isDisplayed());
    }

    @Test
    public void leetCodeTest() throws InterruptedException {

        driver.navigate().to("https://leetcode.com/");
        Actions actions = new Actions(driver);

        actions.moveToElement(driver.findElement(By.xpath("//div[@class='playground-iframe']"))).perform();

        WebElement iframe = driver.findElement(By.xpath("//iframe[@height='400']"));

        //HelperMethods.highlightElement(iframe);

        driver.switchTo().frame(iframe);

        WebElement runButton = driver.findElement(By.xpath("//button[@class='btn btn-success run-code-btn']"));
        runButton.click();

//        WebElement finished = driver.findElement(By.xpath("//span[contains(text(),'Finished in')]"));
//        Assert.assertTrue(finished. );
        WebElement runResultMessage = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='success result']")));

        String text = runResultMessage.getText();
        for (String word : text.split(" ")){
            if(Character.isDigit(word.charAt(0))){
                int ms = Integer.parseInt(word);
                Assert.assertTrue(ms < 5);
            }
        }
    }




}
