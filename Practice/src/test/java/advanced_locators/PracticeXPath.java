package advanced_locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class PracticeXPath {
    /*
    Task#3
    1.Go to https://demoqa.com/radio-button
    2. Print the question that is displayed on the screen
    3. Select answer Yes
    4. Find all answers, and if they are selected, print them out in the format:
    "Answer: ... is/is not selected"
    5. Verify the message on the screen that says "You have selected... " shows the correct value
     */
    WebDriver driver;

    @Before
    public void setUp() {
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
    public void tearDown() {
        driver.close();
    }

    @Test
    public void test1() throws InterruptedException {
        //Try out findElements() method
        //after driver.get() we don't need Thread.sleep();
        //.get() method automatically waits for the web page to fully load all the web elements
        driver.get("https://demoqa.com/radio-button");
//        Print the question that is displayed on the screen
        System.out.println(driver.findElement(By.xpath("//div[@class='mb-3']")).getText());
//        Select answer Yes
        driver.findElement(By.xpath("//label[@class='custom-control-label'][@for='yesRadio']")).click();
        Thread.sleep(3000);
        //Find all answers, and if they are selected, print them out in the format:
        //    "Answer: ... is/is not selected"

        String answer = driver.findElement(By.xpath("//span[@class='text-success']")).getText();

//        if ( answer.equals("Yes")) {
//            System.out.println("Answer: " + answer + " is selected");
//        }else
//        System.out.println("Answer: " + answer + " is not selected");

        //Verify the message on the screen that says "You have selected... " shows the correct value
        Assert.assertEquals("Yes", answer.trim());
    }

    /*
    1.Navigate to the https://demoqa.com/checkbox
    2. Click on the arrow to show nested folders
    3. Select Desktop folder
    4. Verify the result text contains "desktop"

    Look up how to check, if radio button is selected in Selenium. Add to radio buttons
     an assertion that would verify yes radio button is selected
     */

    @Test
    public void test2() throws InterruptedException{
        //Navigate to the https://demoqa.com/checkbox
        driver.get("https://demoqa.com/checkbox");
//        Click on the arrow to show nested folders
        driver.findElement(By.xpath("//button[@aria-label='Toggle']")).click();
        Thread.sleep(3000);
        //Select Desktop folder
        driver.findElement(By.xpath("//span[contains(text(),'Desktop')]")).click();
        Thread.sleep(3000);
        String result = driver.findElement(By.xpath("//span[contains(text(),'desktop')]")).getText();
        Assert.assertEquals("You should have selected desktop ", result, "desktop");
    }

}
