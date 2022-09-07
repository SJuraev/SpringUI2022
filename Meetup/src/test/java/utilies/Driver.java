package utilies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Driver {
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
        //before creating a driver, we need to make sure that there is no driver running
        //first, we have to check that our driver is null
        if(driver == null){
            //here we will create a new fresh driver based on the browser property
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
            }
        }

        //if it is not null we will simply return existing driver

        return driver;
    }
    //2 -for closing your driver

    public static void closeDriver(){
        try {
            if(driver != null){
                driver.close();
                driver.quit();
                driver = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
