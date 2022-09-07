package dropdowns;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import utilities.ConfigReader;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PracticeSelectClass {

    WebDriver driver;

    @Before
    public void setUp(){

        if(ConfigReader.getProperty("browser").equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else
            if(ConfigReader.getProperty("browser").equalsIgnoreCase("firefox")){
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    @Test
    public void practiceExpediaDropdown(){
        driver.get("https://www.expedia.com/");
        driver.findElement(By.xpath("//a[@href=\"?pwaLob=wizard-cruise-pwa\"]")).click();
        //I want to see all the options available
        //I want to see the option selected by default

        //Step1
        //Locate your select webelement and store it in webelement object
        WebElement selectElement = driver.findElement(By.cssSelector("#cruise-destination"));
        //Step2
        //Create object of Select class;
        Select dropdown = new Select(selectElement);

        //To see all of available options
        List<WebElement> availableOptions = dropdown.getOptions();
        System.out.println(availableOptions.size());
        Assert.assertEquals(24, availableOptions.size());

        for (WebElement option : availableOptions){
            System.out.println(option.getText());
        }

        //Make sure "Select destination" is selected by default
        //1. Get all selected options

        List<WebElement> selectedOptions = dropdown.getAllSelectedOptions();
        Assert.assertTrue(selectedOptions.size() == 1 && selectedOptions.get(0).getText().equals("Select destination"));

        //Lets select "Bahamas"
        //To select by visible text
        //dropdown.selectByVisibleText("Bahamas");
        //to select "Bahamas" by index
        //dropdown.selectByIndex(2);
        //select "Bahamas" by value
        dropdown.selectByValue("bahamas");

        selectedOptions = dropdown.getAllSelectedOptions();
        Assert.assertTrue(selectedOptions.size() == 1
                && selectedOptions.get(0).getText().equals("Bahamas"));

    }

    @Test
    public void practiceMultiSelect(){
        driver.get("https://www.jquery-az.com/boots/demo.php?ex=63.0_2");
        //what options are selected
        WebElement selectElement = driver.findElement(By.id("option-droup-demo"));

        Select dropdown = new Select(selectElement);
        //Let's verify that it's a multi select
        Assert.assertTrue(dropdown.isMultiple());

        //what options are selected
        List<WebElement> selectedOptions = dropdown.getAllSelectedOptions();

        System.out.println("Currently selected options are: " );
        for (WebElement selected : selectedOptions){
            System.out.println(selected.getText());
        }

        //I want to select Java, Python, C#

        //1. I deselect HTML and CSS
        dropdown.deselectAll();
        //2. Select Java by index
        dropdown.selectByIndex(5);
        //select C#  by value
        dropdown.selectByValue("csharp");
        //3.Select Python by visible text
        dropdown.selectByVisibleText("Python");

        selectedOptions = dropdown.getAllSelectedOptions();
        System.out.println("**********************************\n New options selected");
        for (WebElement selected : selectedOptions){
            System.out.println(selected.getText());
        }
        Assert.assertEquals(3, selectedOptions.size());

    }

    @Test
    public void cruisesTest(){
        driver.get("https://www.expedia.com/");
        driver.findElement(By.xpath("//a[@href=\"?pwaLob=wizard-cruise-pwa\"]")).click();

        //Step1
        //Locate your select webelement and store it in webelement object
        WebElement selectElement = driver.findElement(By.cssSelector("#cruise-destination"));
        //Step2
        //Create object of Select class;
        Select dropdown = new Select(selectElement);

        dropdown.selectByValue("alaska");
        //verify Alaska is selected
        Assert.assertTrue(dropdown.getAllSelectedOptions().get(0).getText().equals("Alaska"));
        //dropdown.getFirstSelectedOption() - will return a first option that is selected

        dropdown.selectByVisibleText("Africa");
        //verify Africa is selected
        Assert.assertTrue(dropdown.getFirstSelectedOption().getText().equals("Africa"));

        dropdown.selectByIndex(3);
        //verify Mexico is selected
        Assert.assertTrue(dropdown.getFirstSelectedOption().getText().equals("Mexico"));

        //what options are selected
        System.out.println("**********************************\n All possible cruises destinations: ");
        for (WebElement option : dropdown.getOptions()){
            if(!option.getText().equals("Select destination"))
                System.out.println(option.getText());
        }

    }

    @Test
    public void expediaJSTest(){
        driver.get("https://www.expedia.com/");
        WebElement cruisesTab =
        driver.findElement(By.xpath("//a[@href=\"?pwaLob=wizard-cruise-pwa\"]"));

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", cruisesTab);

        //Step1
        //Locate your select webelement and store it in webelement object
        WebElement selectElement = driver.findElement(By.cssSelector("#cruise-destination"));
        //Step2
        //Create object of Select class;
        Select dropdown = new Select(selectElement);

        dropdown.selectByValue("alaska");
        //verify Alaska is selected
        Assert.assertTrue(dropdown.getAllSelectedOptions().get(0).getText().equals("Alaska"));
        //dropdown.getFirstSelectedOption() - will return a first option that is selected

        dropdown.selectByVisibleText("Africa");
        //verify Africa is selected
        Assert.assertTrue(dropdown.getFirstSelectedOption().getText().equals("Africa"));

        dropdown.selectByIndex(3);
        //verify Mexico is selected
        Assert.assertTrue(dropdown.getFirstSelectedOption().getText().equals("Mexico"));

        //what options are selected
        System.out.println("**********************************\n All possible cruises destinations: ");
        for (WebElement option : dropdown.getOptions()){
            if(!option.getText().equals("Select destination"))
                System.out.println(option.getText());
        }

    }



}
