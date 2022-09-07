package windows_i_frames;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MultipleWindows {

    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();//will close all opened tabs and quit the browser

    }

    @Test
    public void amazonTest(){
        driver.get("https://www.amazon.com/");
        //I want to get the unique window handle assigned to amazon page
        //.getWindowHandle() method - retrieves the unique id of currently open window
        String windowHandle = driver.getWindowHandle();

        System.out.println(windowHandle);
        //CDwindow-D2963FA7A288700D786F00F5D250E43B
        //CDwindow-F00AA0372B26A4B97A97049C67B79939

    }

    @Test
    public void doubleWindowsTest(){
        driver.get("https://plugins.jetbrains.com/");
        driver.findElement(By.xpath("//a[@target='_blank']")).click();

        //after I clicked on the link, the new tab opened up
        //in order to interact with web element on the new window, we need to tell my driver to switch to the new tab
        //1. Get windowHandle of main window
        //2. Get windowHandles of all open windows using a loop compare and switch to the new window

        //1
        String mainPage = driver.getWindowHandle();
        //2
        Set<String> allOpenWindows = driver.getWindowHandles();
        //==> this set will contain 2 values(because we have 2 tabs opened)
        //one of the values will be id of our main window
        //3
        for (String windowHandle : allOpenWindows) {
            if (!windowHandle.equals(mainPage)) {
                driver.switchTo().window(windowHandle);
            }
        }

        //click on the link at the new tab
        driver.findElement(By.xpath("//a[contains(text(),'Find your solution')]")).click();
        //here I may need to add some wait...

        WebElement jetBrainsHeading = driver.findElement(By.xpath("//h1[contains(text(),'JetBrains')]"));

        Assert.assertTrue("JetBrains is not displayed. Switch to the new window. ",jetBrainsHeading.isDisplayed());
        //I need to switch back to the main window
        driver.switchTo().window(mainPage);

        WebElement checkOutLink = driver.findElement(By.xpath("//h3[contains(text(),'Marketplace')]"));
        Assert.assertTrue("Failed to switch back to the home page.", checkOutLink.isDisplayed());

        }

        @Test
    public void multipleWindowsTest() throws InterruptedException {
        driver.get("https://demoqa.com/links");
        driver.findElement(By.id("simpleLink")).click();//after the click new tab has opened
            //in order to click on JoinNow button I first need to switch to the new window

            String mainPage = driver.getWindowHandle();
            //2
            Set<String> allOpenWindows = driver.getWindowHandles();//only two values

            for (String windowHandle : allOpenWindows) {
                if (!windowHandle.equals(mainPage)) {
                    driver.switchTo().window(windowHandle);
                }
            }
            //now we are on the new window, and we can interact with its elements

            driver.findElement(By.xpath("//img[@alt='Selenium Online Training']")).click();
            //now I have 3 windows open



            String secondPage = driver.getWindowHandle();

           allOpenWindows = driver.getWindowHandles();//now it will have 3 values

            for (String windowHandle : allOpenWindows){
                if(!windowHandle.equals(mainPage) && !windowHandle.equals(secondPage)){
                    driver.switchTo().window(windowHandle);
                }
            }

//            WebDriverWait wait = new WebDriverWait(driver, 10);
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'Tools QA - Selenium Training')]")));

            //now we are on the new window, and we can interact with its elements

            Assert.assertEquals("","Tools QA - Selenium Training", driver.getTitle() );
            Assert.assertTrue(driver.findElement
                    (By.xpath("//div[contains(text(),'Selenium Certification Training | Enroll Now')]")).isDisplayed());


        }


}
