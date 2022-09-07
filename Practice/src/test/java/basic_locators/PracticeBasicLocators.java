package basic_locators;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PracticeBasicLocators {

    WebDriver driver;

    @Test
    public void testClassLocator(){
        //Navigate to the google.com, Find Ling Gmail and print out the text of the link
        // and verify link text contains "future leaders"

        //the new way we set up a driver- independent of jar file
        //the new way does same as - System.setProperty();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://www.google.com/");

        WebElement link = driver.findElement(By.className("gb_d"));
        String linkText = link.getText();
        System.out.println(linkText);

        Assert.assertTrue("Link text verification does not contain - \"mail\" ",linkText.contains("mail"));
    }

    //create a new test where you will navigate to http://automationpractice.com/index.php
    //ust tag name locator and find <Automation Practice Website> heading and verify it contains - "Practice"
    @Test
    public void testTagNameLocator(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("http://automationpractice.com/index.php");

        WebElement heading = driver.findElement(By.tagName("h1"));
        String headingText = heading.getText();
        System.out.println(headingText);

        Assert.assertTrue("Heading text verification does not contain - \"Practice\" ",headingText.contains("Practice"));
    }

    @Test
    public void testLinkLocator() throws InterruptedException{
        //Navigate to the google.com, Find Ling Gmail and print out the text of the link
        // and verify link text contains "future leaders"

        //the new way we set up a driver- independent of jar file
        //the new way does same as - System.setProperty();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        //make sure you are passing full text of the link
        WebElement linkGmail = driver.findElement(By.linkText("Gmail"));
        linkGmail.click();
        String actualLink = driver.getCurrentUrl();

        Thread.sleep(3000);
        Assert.assertEquals("The Gmail link should be " + driver.getCurrentUrl(), "https://www.google.com/gmail/about/", actualLink);
        System.out.println("The url is: " + driver.getCurrentUrl());
    }

    //this method cleans up after your test
    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void testPartialLink() throws  InterruptedException{
        //Navigate to the google.com, Find Ling Gmail and print out the text of the link
        // and verify link text contains "future leaders"

        //the new way we set up a driver- independent of jar file
        //the new way does same as - System.setProperty();
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.get("https://www.google.com/");

        driver.manage().window().maximize();

        //make sure you are passing full text of the link
        WebElement linkHowSearch = driver.findElement(By.partialLinkText("How Search"));
        linkHowSearch.click();
        Thread.sleep(2000);

        String expected = "Discover How Google Search Works";

        Assert.assertTrue("Title verification failed, expected title should contain " + expected + "Actual is: " + driver.getTitle(),
                driver.getTitle().contains(expected));
        System.out.println("Actual title is: " + driver.getTitle());
    }

    /*
    Set up the driver, maximize the window, add timeouts before every single test
    Task #1 - partial link text
    Go to http://automationpractice.com/index.php
    Click on x
    Verify input field for the name is displayed
    Task #2 - tag
     */

}
