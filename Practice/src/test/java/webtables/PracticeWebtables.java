package webtables;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Driver;

import java.util.List;

public class PracticeWebtables {
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
    public void staticTableTest() throws InterruptedException {
        //1- we will treat it as a static table
        //and we can hardcode the row where Dan Brown is displayed
        driver.navigate().to("https://chandanachaitanya.github.io/selenium-practice-site/?language=Java&enterText=");
        //bring the table into the view
        Actions actions = new Actions(driver);
        WebElement buttonToScroll = driver.findElement(By.id("win1"));
        actions.moveToElement(buttonToScroll).perform();
        Thread.sleep(3000);

        WebElement table = driver.findElement(By.xpath("//table[@id='BooksAuthorsTable']"));
        //how to get the numbers of rows
        List<WebElement> rows = table.findElements(By.xpath("//.//tr"));
        int numberOfRows = rows.size();
        System.out.println("The number of rows in the table: " + numberOfRows);

        //how to get the number of columns
        List<WebElement>columns = table.findElements(By.xpath(".//tr/th"));
        int numberOfColumns = columns.size();
        System.out.println("The number of columns in the table: " + numberOfColumns);

        //print out all the books written by Dan Brown
        List<WebElement> danBrownRow = table.findElements(By.xpath(".//tr[4]/td"));

        for(int i = 1; i < danBrownRow.size(); i++){
            System.out.println(danBrownRow.get(i).getText());
        }

    }

    @Test
    public void dynamicTableTest() throws InterruptedException {
        driver.navigate().to("https://chandanachaitanya.github.io/selenium-practice-site/?language=Java&enterText=");
        //bring the table into the view
        Actions actions = new Actions(driver);

        WebElement buttonToScroll = driver.findElement(By.id("win1"));
        actions.moveToElement(buttonToScroll).perform();

        Thread.sleep(3000);

        //We will be working with dynamic table
        //1. We need to find out which column contains authors
        //2. We need to locate a row that stores Dan Brown books
        //3. We will(we will rock you!!!) print out books

        WebElement table = driver.findElement(By.xpath("//table[@id='BooksAuthorsTable']"));

        List<WebElement>columnNames = table.findElements(By.xpath(".//th"));

        int indexOfAuthorNameColumn = 0;
        for (WebElement columnName : columnNames) {
            ++indexOfAuthorNameColumn;
            if(columnName.getText().contains("Author"))
                break;
        }

        List<WebElement> authors = table.findElements(By.xpath(".//tr/td["+indexOfAuthorNameColumn+"]"));

        int indexOfDanBrownRow = 1;
        for (WebElement author : authors){
            ++indexOfDanBrownRow;
            if (author.getText().trim().equals("Dan Brown"))
                break;
        }

        int numOfColumns = columnNames.size();
        String xpathForTheBooks = ".//tr["+indexOfDanBrownRow+"]/td";

        for (int i = 1; i <= numOfColumns; i++){
            if(i == indexOfAuthorNameColumn)continue;

            WebElement book = table.findElement(By.xpath(xpathForTheBooks + "["+i+"]"));
            System.out.println(book.getText());
        }

    }

    @Test
    public void getAllAuthorsTest() throws InterruptedException {
        driver.navigate().to("https://chandanachaitanya.github.io/selenium-practice-site/?language=Java&enterText=");
        Actions actions = new Actions(driver);

        WebElement buttonToScroll = driver.findElement(By.id("win1"));
        actions.moveToElement(buttonToScroll).perform();

        Thread.sleep(3000);

        WebElement table = driver.findElement(By.xpath("//table[@id='BooksAuthorsTable']"));

        List<WebElement> columns = table.findElements(By.xpath(".//th"));

        int authorsIndex = 0;

        for (WebElement column : columns){
            authorsIndex++;
            if (column.getText().trim().equals("Author"))
                break;
        }

        List<WebElement> authors = table.findElements(By.xpath(".//tr/td["+authorsIndex+"]"));
        //tr[2]//td[1]
      //  List<WebElement> authors = table.findElements(By.xpath(".//tr["+i+"//td[1]"));
        for (WebElement author: authors){
            System.out.println(author.getText());
        }
    }

    @Test
    public void wThreeSchoolsTest(){
        driver.navigate().to("https://www.w3schools.com/html/html_tables.asp");

        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='customers']//tr"));

        int mexicoRow = 1;//pointing to headings row

        for (int i = 2; i <= rows.size(); i++){
            mexicoRow++;
            WebElement countryCell = driver.findElement(By.xpath("//table[@id='customers']//tr["+i+"]//td[3]"));
            if(countryCell.getText().trim().equals("Mexico"))
                break;
        }

        int companyColumn = 1;

        WebElement companyInMexico = driver.findElement(By.xpath("//table[@id='customers']//tr["+mexicoRow+"]//td["+companyColumn+"]"));

        System.out.println(companyInMexico.getText());
    }

    

}
