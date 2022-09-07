package advancedMouseInteractions;

import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;
import utilities.HelperMethods;

public class PracticeActionsClass {

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
    public void test1(){
        driver.navigate().to("https://demoqa.com/buttons");
        //we will practice doubleClick() and right click

        WebElement doubleClickButton = driver.findElement(By.id("doubleClickBtn"));

        WebElement rightClickButton = driver.findElement(By.id("rightClickBtn"));

        //Steps in order to the Actions class
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleClickButton).contextClick(rightClickButton).perform();
        WebElement doubleClickMessage = driver.findElement(By.id("doubleClickMessage"));
        Assert.assertTrue(doubleClickMessage.isDisplayed());

        //actions.contextClick(rightClickButton).perform();
        WebElement rightClickMessage = driver.findElement(By.id("rightClickMessage"));
        Assert.assertTrue(rightClickMessage.isDisplayed());

    }


    @Test
    public void test2() throws InterruptedException {
        driver.navigate().to("https://demoqa.com/tool-tips");

        WebElement hoverMeOverButton = driver.findElement(By.id("toolTipButton"));
     //in order to hover over the web element we use moveToElement()

     Actions actions = new Actions(driver);
     actions.moveToElement(hoverMeOverButton).perform();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.attributeToBe
                (hoverMeOverButton, "aria-describedby", "buttonToolTip"));


     Assert.assertTrue("Tool tip is not displayed",
             hoverMeOverButton.getAttribute("aria-describedby").equals("buttonToolTip"));
    }

    @Test
    public void test3(){
        driver.navigate().to("https://demoqa.com/slider");

        WebElement sliderCircle = driver.findElement(By.xpath("//input[@type='range']"));
        Actions actions = new Actions(driver);

        actions.clickAndHold(sliderCircle).moveByOffset(30,0).release().perform();
        Integer newSliderValue = Integer.parseInt(sliderCircle.getAttribute("value"));
        System.out.println(newSliderValue);
        Assert.assertTrue(newSliderValue > 25);
    }

    @Test
    public void test4(){
        driver.navigate().to("https://demoqa.com/droppable");

        //1) We will use built into selenium drag and drop method

        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.xpath("//div[@id='simpleDropContainer']/div[2]"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(source,target).perform();

        WebElement successfulDrop = target.findElement(By.xpath("./p"));

        Assert.assertTrue(successfulDrop.getText().equals("Dropped!"));

    }

    @Test
    public void test5(){
        driver.navigate().to("https://demoqa.com/droppable");


        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.xpath("//div[@id='simpleDropContainer']/div[2]"));

        Actions actions = new Actions(driver);
        actions.clickAndHold(source).moveToElement(target).release().perform();

        WebElement successfulDrop = target.findElement(By.xpath("./p"));

        Assert.assertTrue(successfulDrop.getText().equals("Dropped!"));

    }

    @Test
    public void test6() throws InterruptedException {
        driver.navigate().to("https://demoqa.com/droppable");


        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.xpath("//div[@id='simpleDropContainer']/div[2]"));

        Actions actions = new Actions(driver);
        actions.clickAndHold(source).moveByOffset(250, 25).release().perform();
        Thread.sleep(3000);

        WebElement successfulDrop = target.findElement(By.xpath("./p"));

        Assert.assertTrue(successfulDrop.getText().equals("Dropped!"));

    }

    @Test
    public void test7(){
        driver.navigate().to("https://demoqa.com/progress-bar");

        WebElement startButton = driver.findElement(By.id("startStopButton"));
        startButton.click();


        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("resetButton")));
        WebElement resetButton = driver.findElement(By.id("resetButton"));
                resetButton.click();

        WebElement startStopButtonButton = driver.findElement(By.id("startStopButton"));
        Assert.assertTrue(startStopButtonButton.isDisplayed());
    }

    @Test
    public void test8(){
        driver.navigate().to("https://demoqa.com/menu#");

        WebElement mainItemTwo = driver.findElement(By.xpath("//a[text()='Main Item 2']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(mainItemTwo).perform();

        WebElement subSubList = driver.findElement(By.xpath("//a[text()='SUB SUB LIST »']"));
        WebElement subSubItemOne = driver.findElement(By.xpath("//a[text()='Sub Sub Item 1']"));
        actions.moveToElement(subSubList).moveToElement(subSubItemOne).perform();

        subSubItemOne.click();
    }

    //key press events
    @Test
    public void test9() throws InterruptedException {
        driver.navigate().to("https://demoqa.com/automation-practice-form");
        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        WebElement lastNameInput = driver.findElement(By.id("lastName"));

        HelperMethods.highlightElement(firstNameInput);
        Faker faker = new Faker();
        firstNameInput.sendKeys(faker.funnyName().name());

        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();

        HelperMethods.highlightElement(lastNameInput);
        actions.sendKeys(Keys.TAB).perform();
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();

        Thread.sleep(3000);

        Assert.assertTrue(lastNameInput.getAttribute("value").equals(firstNameInput.getAttribute("value")));

    }
    /*
    Homework:
    1.Navigate to https://opensource-demo.orangehrmlive.com/
    2.Login using Admin and admin123 as a username and password respectively
    3.If landed not on a dashboard - click Dashboard
    4.Click Assign Leave
    5.Type Muhammad Ali and select us this employee for auto suggested names in employee name field
    6.Leave Type: Vacation
    7.Select tomorrow's date as start and end date
    8.Add a comment (any)
    9.Click Assign
    10.Verify error modal is displayed saying that she does not have enough vacation hours
     */
    //iframe[@src='https://leetcode.com/playground/UpwhGDg6/shared']
    @Test
    public void homeworkTest() throws InterruptedException {
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
        WebElement usernameInput = driver.findElement(By.xpath("//input[@name='username']"));
        WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));

        HelperMethods.highlightElement(usernameInput);
        usernameInput.sendKeys("Admin");

        HelperMethods.highlightElement(passwordInput);
        passwordInput.sendKeys("admin123");

        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        HelperMethods.highlightElement(loginButton);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",loginButton);
        Thread.sleep(2000);

        WebElement headingEmployeeInformation = driver.findElement(By.xpath("//h5"));
        HelperMethods.highlightElement(headingEmployeeInformation);
        Assert.assertTrue(headingEmployeeInformation.isDisplayed());
    }




}
