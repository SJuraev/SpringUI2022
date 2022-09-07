package browser_synchronization;

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

import java.util.concurrent.TimeUnit;

public class PracticeWaits {

    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //Implicit wait
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void amazonTest(){
        driver.get("https://www.amazon.com/");
    }

    @Test
    public void testEtsy(){
        driver.get("https://www.etsy.com/");//The only case when you don't need to wait
        //click Sign In button
        driver.findElement(By.xpath("//button[contains(@class,'select-signin')]")).click();

        //locate register button
        //here we will add explicit wait to wait for our register button to be displayed on the page
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement registerButton =
          wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@class,'select-register')]")));

        //WebElement registerButton = driver.findElement(By.xpath("//button[contains(@class,'select-register')]"));
        registerButton.click();

       // WebElement registrationIsEasyText =
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[starts-with(text(),'Registration')]")));
       WebElement registrationIsEasyText = driver.findElement(By.xpath("//p[starts-with(text(),'Registration')]"));

        Assert.assertTrue("\"Registration is easy.\" text is not displayed.", registrationIsEasyText.isDisplayed());
    }
    //https://demoqa.com/dynamic-properties

    @Test
    public void visibleAfterFiveSecondsTest(){

        driver.get("https://demoqa.com/dynamic-properties");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement visibleAfter =
          wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='visibleAfter']")));

        Assert.assertTrue("Visible after 5 seconds button is not visible yet", visibleAfter.isDisplayed());
    }

    @Test
    public void haveColorChangedTest(){
        driver.get("https://demoqa.com/dynamic-properties");
        WebDriverWait wait = new WebDriverWait(driver, 5);

         //wait.until(ExpectedConditions.attributeContains(By.cssSelector("#colorChange"), "class", "text-danger"));
        wait.until(ExpectedConditions.attributeContains(By.id("colorChange"), "class", "text-danger"));
         WebElement colorChangeButton = driver.findElement(By.id("colorChange"));

         Assert.assertTrue(colorChangeButton.getAttribute("class").contains("text-danger"));
    }

    @Test
    public void fileUploadTest() throws InterruptedException {
        driver.get("https://demoqa.com/upload-download");
        //I need to locate the input element that represents file upload
        //then I need to sendKeys() with the path to my file to the input field

        WebElement chooseFileInput = driver.findElement(By.xpath("//input[@id='uploadFile']"));
        chooseFileInput.sendKeys("C:\\Users\\Sanjar\\Desktop\\New-DevX-logo.png");
        //verify that my file path is displayed under the choose file button

        WebElement result = chooseFileInput.findElement(By.xpath("./ ../ ../following-sibling::p"));

        Assert.assertTrue(result.getText().contains("DevX-logo"));
    }

//    HomeWork:
//    Please read about Fluent Wait and learn syntax
//    Look up Robot Framework or AutoIt

    /*
    Task# 3 - file upload, google images
    1. Go to https://images.google.com/
    2. Click on small camera image "Search by image"
    3. Click on Upload an image
    4. Send a path to the image on your computer
    5. Visually verify that the search results have loaded and your image is displayed on top
    -Selenium can not handle visual validation, we can only check that image is there, but
    Selenium can't tell that the image is the actual image you want to see
     */

    @Test
    public void googleImageTest() {
        driver.get("https://images.google.com/");
        driver.findElement(By.xpath("//span[@class='tdPRye']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Upload')]")).click();
        //C:\Users\Sanjar\Desktop\cat.jpg
        WebElement uploadImage = driver.findElement(By.xpath("//input[@id='awyMjb']"));
        uploadImage.sendKeys("C:\\Users\\Sanjar\\Desktop\\cat.jpg");
    }


}
