package utilies;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
    //This class will store reusable methods

    //actions click - mouse click

    public static void mouseClick(WebElement element){
        //I would first check if my element is clickable and visible
        new WebDriverWait(Driver.getDriver(), 7).until(ExpectedConditions.elementToBeClickable(element));
        Actions actions = new Actions(Driver.getDriver());
        actions.click(element).perform();
    }

    public static void javascriptClick(WebElement element){
        new WebDriverWait(Driver.getDriver(), 7).until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor js = (JavascriptExecutor)Driver.getDriver();

        js.executeScript("arguments[0].click();", element);
    }

    //In case you will be demoing your framework
    //ex => String style = "border: 4px solid purple";
    public static void highlightElement(WebElement element) throws InterruptedException {
        String style = "border: 4px dashed purple";
        JavascriptExecutor js = (JavascriptExecutor)Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                element, style);
        Thread.sleep(2000);
    }

    public static void highlightElement(String id) throws InterruptedException {
        String style = "border: 4px solid purple";
        JavascriptExecutor js = (JavascriptExecutor)Driver.getDriver();
        js.executeScript("document.getElementById('"+id+"').setAttribute('style', arguments[0]);", style);
        Thread.sleep(2000);
    }

    public static void waitForElementToBeDisplayed(WebElement element){
        new WebDriverWait(Driver.getDriver(),10).until(ExpectedConditions.visibilityOf(element));

    }

    public static void waitForElementToBeClickable(WebElement element){
        new WebDriverWait(Driver.getDriver(),10).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void weClick(WebElement element){
        waitForElementToBeDisplayed(element);
        waitForElementToBeClickable(element);
        element.click();
    }
}
