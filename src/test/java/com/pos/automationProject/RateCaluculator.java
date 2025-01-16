package com.pos.automationProject;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;

public class RateCaluculator {
    // Webdriver declaration
    WebDriver driver;

    //Storing the url in a string
    String URL = "https://pos.com.my/send/ratecalculator";

    //storing the intractable Objects in BY class variables
    By txt_fromCountryPostcode = By.xpath("//input[@formcontrolname='postcodeFrom']");
    By txt_ToCountry= By.id("mat-input-0");
    By txt_weight  = By.xpath("//input[@type='number' and @formcontrolname ='itemWeight']");
    By click_caluculatorBtn =By.xpath("//div[@class='flex max-w-7xl mx-auto justify-end px-8 sm:px-0']/a");

    //@BeforeTest is executed before @Test. It is executing only one time.
    @BeforeTest
    public void launchDriver() {
        //Initialization of the Chrome Browser
        driver = new ChromeDriver();
        //Windows Maximization
        driver.manage().window().maximize();
        //getmethod is used to get the URL
        driver.get(URL);
    }

    //@Test annotation is used to perform the actual Execution
    @Test
    public void rateCalculator() throws InterruptedException {
      //Driver interacting with from country postcode object and click() method is used to perform the click action.
        driver.findElement(txt_fromCountryPostcode).click();
        //Sendkeys() method is used to  enter the text in the provided field
        driver.findElement(txt_fromCountryPostcode).sendKeys("35600");
       // Driver interacting with To country postcode object and click() method is used to perform the click action
        driver.findElement(txt_ToCountry).click();
        //List of WebElements in the TO country dropdown list
        List<WebElement> countryList = driver.findElements(By.xpath("//*[@class='country-list-item ng-star-inserted']"));
       //Printing To Country dropdown list countries COUNT
        System.out.println("country List: " + countryList.size());
        //Clear() method is used to clear the text alraedy available in the To country textbox
        driver.findElement(txt_ToCountry).clear();
        //sendkeys() method is used to  enter the text in the To country textbox field
		driver.findElement(txt_ToCountry).sendKeys("India");

        //Here Thread.sleep can only able to use rather than implicitly wait and explicitly wait.
        //When use implicitly wait, ToCountry dropdown list is utilize more time to load. So that execution will take much longer when i use it.
        //When use Explicitly wait, cannot provide the condition inside.
		Thread.sleep(5000);
        //Click on the India-In option available in the dropdown list.
        WebElement sel_India = driver.findElement(By.xpath("//*[@class='country-list-item ng-star-inserted']"));
        sel_India.click();
        //driver interacting with the Weight textbox field.
        //Click() method is used to perform click action and sendKeys() method is used enter values in the provided textbox field.
        driver.findElement(txt_weight).click();
        driver.findElement(txt_weight).sendKeys("1");
        //driver interacting with Calculation button and storing this object in the WebElement Variable btn
        WebElement btn= driver.findElement(click_caluculatorBtn);
       //Here Action class is performed to the page scroll down which helps to interact with calculator button.
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        actions.moveByOffset(500, 0).perform();
        btn.click();

        //Able to see multiple quotes and shipments options available.
        Thread.sleep(5000);
        List<WebElement> count = driver.findElements(By.xpath("//*[contains(text(),' Book Now ')]"));
        System.out.println("searchResultList: "+ count.size());
        if(count.size()>1) {
            System.out.println("Able to display more than 1 search results");
        }
        else{
            System.out.println("unable to display more than 1 search results");
        }

    }
        //@AfterTest is executed after @Test. It is executing only one time after all @Test execution..
       @AfterTest
               public void browserClose() {
                driver.quit();

      }
}
