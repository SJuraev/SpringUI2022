package step_defs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilies.Driver;
import utilies.EnvironmentManager;

public class Hooks { //Hooks class must be in step_defs(=glue) package


    @Before//make sure you are importing @Before hook from cucumber.api.java(not from junit)
    public void setUp() throws Exception {
        //you don't need to set up your driver
        //here you can set up your environment variables
        //we will have environment manager that will store all new env variables
        //the variables correctly depending on the env we are running our tests against
        EnvironmentManager.setUpEnvironment();

    }

    @After//cucumber.api.java
    public void tearDown(Scenario scenario){
        //I can attach a screenshot to a failing test

        try {
            if(scenario.isFailed()){
                //we will take a screenshot
                final byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
                //we need to add screenshot to the html report
                scenario.embed(screenshot,"image/png");
            }
        }catch (Exception e){
            System.out.println("The error happened while taking screenshot");
            e.getMessage();
        }

        Driver.closeDriver();

    }


}
