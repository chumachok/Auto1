package Romko.gmail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class FirstTest
{
    private static WebDriver driver;
    private Assertion hardAssert = new Assertion();
    private SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public static void setup()
    {
//        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver","C:\\GeckoDriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette",true);

//        WebDriver driver = new ChromeDriver();
        driver = new FirefoxDriver(capabilities);

//        driver.get("https://www.online.ua/");
//        driver.get("https://board-games.space/");
        driver.get("https://www.autohero.com/de/search/");

//        WebElement loginButton = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/ul/li[1]/div/button"));
//        loginButton.isDisplayed();
//        loginButton.click();

    }

    @Test
    public void login() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement yearFilterMenu = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[1]/div/div/div/div[3]/div[1]/span"));
        yearFilterMenu.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement yearFilterSelector = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[1]/div/div/div/div[3]/div[2]/div/select"));
        yearFilterSelector.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement yearFilter2015 = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[1]/div/div/div/div[3]/div[2]/div/select/option[5]"));
        yearFilter2015.click();
        System.out.println("ee");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement priceFilterAZ = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[2]/div[3]/div/div/select/option[2]"));
        priceFilterAZ.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement carOnPage72 = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[2]/div[4]/div/div/select/option[3]"));
        carOnPage72.click();

        WebElement carsFound = driver.findElement(By.cssSelector("div[class='resultsAmount___3OrV7']"));
        Thread.sleep(2000);
        String carsTotal = carsFound.getText();
        carsTotal = carsTotal.substring(0,carsTotal.length()-8);
        int pagesRounded = (int) Math.ceil((Integer.parseInt(carsTotal) + .0) / (72 + .0));

        for (int i = 3; i <= pagesRounded+2; i++)
        {
            WebElement currentPage = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[3]/div/div[2]/div[2]/div/ul/li[" + i + "]/a"));
            currentPage.click();

            for (int j = 1; j <= 72; j++)
            {
                WebElement year1stCar = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[3]/div/div[1]/div[" + j + "]/div/a/ul/li[1]"));
                String test1 = year1stCar.getText();
                test1 = test1.substring(2);
                System.out.println(test1);
                if (Integer.parseInt(test1) < 2015)
                {
                    System.out.println("filter works incorrectly!!! Car with registration year " + test1 + " is present!");

                }
            }



        }






        System.out.println("");
        }

    @AfterClass
    public static void closeWebBrowser ()
    {
     driver.close();
    }


}
