package iframesAndAlerts;

import log4jdemo.Log4jDemo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;


public class PracticeAlerts {

    private static final Logger LOGGER = LogManager.getLogger(PracticeAlerts.class);

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
    public void testSimpleAlert() throws InterruptedException {
        LOGGER.info("Testing SimpleAlert");

        driver.navigate().to("https://demoqa.com/alerts");
        LOGGER.debug("Navigated to https://demoqa.com/alerts");
        //trigger the first alert
        LOGGER.debug("Triggered the first alert");
        LOGGER.debug("Triggered the first alert with id alertButton");
        driver.findElement(By.id("alertButton")).click();
        //now alert is present on the screen
        LOGGER.debug("Waiting for 2 sek the alert pop up");
        Thread.sleep(2000);

        LOGGER.debug("Switching to alert");
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        LOGGER.debug("Got the alert text " + alertText);

        Assert.assertEquals("You clicked a button", alertText);

        //with the simple alert - we can only click ok
        alert.accept();

        LOGGER.debug("Click OK");
        driver.findElement(By.id("timerAlertButton")).click();
        //this line will fail because the alert is not present on the screen right away
        //it takes 5 seconds to appear
        //that's why we need to wait
        LOGGER.debug("Waiting for 7 sek for the timerAlertButton to appear");
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();

        LOGGER.debug("Accepting Alert");
        alert.accept();
        LOGGER.info("SimpleAlert Test Successful");
    }

    @Test
    public void promptAlertTest(){
        driver.navigate().to("https://demoqa.com/alerts");
        driver.findElement(By.id("promtButton")).click();

        Alert alert = driver.switchTo().alert();

        //since it is a prompt - I have to type some info in it
        String name = "Sanjar";
        alert.sendKeys(name);
        alert.accept();

        WebElement nameResult = driver.findElement(By.id("promptResult"));
        Assert.assertTrue(nameResult.getText().contains(name));
    }

    @Test
    public void confirmationAlertTest() throws InterruptedException {
        driver.navigate().to("https://demoqa.com/alerts");
        driver.findElement(By.id("confirmButton")).click();
        Alert alert = driver.switchTo().alert();

        alert.dismiss();

        String cancel = "Cancel";
        WebElement cancelResult = driver.findElement(By.id("confirmResult"));

        Assert.assertTrue(cancelResult.getText().endsWith(cancel));
        Thread.sleep(2000);


    }


}
