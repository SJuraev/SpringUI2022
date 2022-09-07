package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class HelperMethods {

    //In case you will be demoing your framework
    //ex => String style = "border: 4px solid purple";
    public static void highlightElement(WebElement element) throws InterruptedException {
        String style = "border: 4px solid purple";
        JavascriptExecutor js = (JavascriptExecutor)Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                element, style);
        Thread.sleep(2000);
    }

    public static void highlightElement(String id) throws InterruptedException {
        String style = "border: 4px dashed purple";
        JavascriptExecutor js = (JavascriptExecutor)Driver.getDriver();
        js.executeScript("document.getElementById('"+id+"').setAttribute('style', arguments[0]);", style);
        Thread.sleep(2000);
    }








}
