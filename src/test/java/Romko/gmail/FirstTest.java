package Romko.gmail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;


public class FirstTest
{
    private static WebDriver driver;

    @BeforeClass
    public static void setup()
    {
        System.setProperty("webdriver.gecko.driver","C:\\GeckoDriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette",true);

        driver = new FirefoxDriver(capabilities);
        driver.get("https://www.autohero.com/de/search/");
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
//        WebElement yearFilter2015 = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[1]/div/div/div/div[3]/div[2]/div/select/option[6]"));
        yearFilter2015.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement priceFilterAZ = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[2]/div[3]/div/div/select/option[2]"));
        priceFilterAZ.click();

        WebElement first1PagePrice = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[3]/div/div[1]/div[1]/div/a/div[3]/div[1]"));
        WebElement last1PagePrice = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[3]/div/div[1]/div[24]/div/a/div[3]/div[1]"));
        Thread.sleep(2000);

        String firstPriceString = first1PagePrice.getText();
        String lastPriceString = last1PagePrice.getText();
        firstPriceString = firstPriceString.replaceAll("\\D", "");
        lastPriceString = lastPriceString.replaceAll("\\D", "");

        if (Integer.parseInt(firstPriceString) > Integer.parseInt(lastPriceString))
        {
            System.out.println("Sorting by price works INCORRECTLY!!!");
        }
        else
        {
            System.out.println("Sorting by price works correctly");
        }

        WebElement carsFound = driver.findElement(By.cssSelector("div[class='resultsAmount___3OrV7']"));
        Thread.sleep(2000);
        String carsTotal = carsFound.getText();
        carsTotal = carsTotal.substring(0,carsTotal.length()-8);
        int itemsOnRegularPage = 24;
        int pagesRounded = (int) Math.ceil((Integer.parseInt(carsTotal) + .0) / (24 + .0));
        int itemsOnLastPage = Integer.parseInt(carsTotal) % 24;

        int incorrectYear = 0;

        for (int i = 3; i <= pagesRounded+2; i++)
        {
            if (i >= 3 && i < 9)
            {
                WebElement currentPage = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[3]/div/div[2]/div[2]/div/ul/li[" + i + "]/a"));
                currentPage.click();
            }
            else if (i >= 9 && i <= pagesRounded+1)
            {
                WebElement currentPage = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[3]/div/div[2]/div[2]/div/ul/li[8]/a"));
                currentPage.click();
            }

            else if (i == pagesRounded+2)
            {
                WebElement currentPage = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[3]/div/div[2]/div[2]/div/ul/li[" + 9 + "]/a"));
                currentPage.click();
            }

            Thread.sleep(2000);
//            System.out.println("page No:" + (i-2));

            int counterItems;

            if (i == pagesRounded+2)
            {
                counterItems = itemsOnLastPage;
            }
            else
            {
                counterItems = itemsOnRegularPage;
            }

            for (int j = 1; j <= counterItems; j++)
            {
                WebElement year1stCar = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[3]/div/div[1]/div[" + j + "]/div/a/ul/li[1]"));
                String test1 = year1stCar.getText();
                test1 = test1.substring(2);
//                System.out.println(test1);
                if (Integer.parseInt(test1) < 2015)
                {
                    System.out.println("Car with registration year " + test1 + " is present!");
                    System.out.println("page No:" + (i-2));
                    System.out.println(test1);
                    incorrectYear++;
                }
            }
        }

        if (incorrectYear >=1) System.out.println("Filter by year works INCORRECTLY");
        else if (incorrectYear ==0) System.out.println("Filter by year works correctly");
        }

    @AfterClass
    public static void closeWebBrowser ()
    {
     driver.close();
    }


}
