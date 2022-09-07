package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import saucelabs.SaucelabsDriver;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Driver {

    //Initialize a logger for every class separately and add the class you are working in
    //so that logger uses this class name when logging stuff from this class.
    private static final Logger LOGGER = LogManager.getLogger(Driver.class);
    //This will be the class that will totally control our browser and driver
    //It will make sure that there is only one driver instance running at a time

    //In order to prevent anyone from creating a driver instance from anywhere
    //we can create private constructor

    //!!!We created this class using Singleton Design Pattern


    private Driver(){
        // as we have private constructor called Driver, we can't make objects called Driver => driver = new Driver()
    }

    private static WebDriver driver;

    //this class will contain two methods:
    //1 - for creating a driver
    public static WebDriver getDriver(){
        LOGGER.debug("Initializing a web-driver for Selenium version - 3.141.59");

        //before creating a driver, we need to make sure that there is no driver running
        //first, we have to check that our driver is null
        if(driver == null){
            //here we will create a new fresh driver based on the browser property
            LOGGER.info("Loading " + ConfigReader.getProperty("browser").toLowerCase() + " Browser.");
            switch (ConfigReader.getProperty("browser").toLowerCase(Locale.ROOT)){

                default:
                    //here is set up for Chrome
                    driver = ChromeWebDriver.loadChromeDriver();
                    break;
                case "firefox" :
                    //here will be a code to set up firefox browser
                    driver = FirefoxWebDriver.loadFirefoxDriver();


                    break;
                case "safari" :
                    //here will be a code to set up firefox browser

                    driver = new SafariDriver();
                    driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
                    driver.manage().window().maximize();
                    break;
                case "saucelabs":
                    driver = SaucelabsDriver.loadSaucelabsDriver();
                    break;
            }
        }

        //if it is not null we will simply return existing driver

        return driver;
    }
    //2 -for closing your driver

    public static void closeDriver(){
        try {
            if(driver != null){
                LOGGER.info("Closing Driver");
                driver.close();
                driver.quit();
                driver = null;
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }

    }
}
