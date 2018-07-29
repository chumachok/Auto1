package Romko.gmail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.xml.xpath.XPath;
import java.util.concurrent.TimeUnit;


public class FirstTest
{
    private static WebDriver driver;

    @BeforeClass
    public static void setup()
    {
        Messages neededMessage = new Messages();
        System.setProperty(neededMessage.driverPropertyPart1,neededMessage.driverPropertyPart2);
//        System.setProperty("webdriver.gecko.driver","C:\\GeckoDriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette",true);

        driver = new FirefoxDriver(capabilities);
        driver.get(neededMessage.webSiteAddress);
//        driver.get("https://www.autohero.com/de/search/");
    }

    @Test
    public void login() throws InterruptedException
    {
        Xpaths neededXpath = new Xpaths();
        Messages neededMessage = new Messages();
        Messages incorrectCarOutput = new Messages();
        ActionElementOnPage actionWithWebSite = new ActionElementOnPage();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actionWithWebSite.pressYearFilterMenuButton(neededXpath.yearFilterMenu, driver);
//        WebElement yearFilterMenu = driver.findElement(By.xpath(neededXpath.yearFilterMenu));
//        WebElement yearFilterMenu = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[1]/div/div/div/div[3]/div[1]/span"));
//        yearFilterMenu.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actionWithWebSite.pressYearFilterSelector(neededXpath.yearFilterSelector, driver);
//        WebElement yearFilterSelector = driver.findElement(By.xpath(neededXpath.yearFilterSelector));
//        WebElement yearFilterSelector = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[1]/div/div/div/div[3]/div[2]/div/select"));
//        yearFilterSelector.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actionWithWebSite.setFilterTo2015(neededXpath.yearFilter2015, driver);
//        WebElement yearFilter2015 = driver.findElement(By.xpath(neededXpath.yearFilter2015));
//        WebElement yearFilter2015 = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[1]/div/div/div/div[3]/div[2]/div/select/option[5]"));
//        yearFilter2015.click();

//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actionWithWebSite.orderPriceAZ(neededXpath.priceFilterAZ, driver);
//        WebElement priceFilterAZ = driver.findElement(By.xpath(neededXpath.priceFilterAZ));
//        WebElement priceFilterAZ = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[2]/div[3]/div/div/select/option[2]"));
//            priceFilterAZ.click();

        WebElement first1PagePrice = driver.findElement(By.xpath(neededXpath.first1PagePrice));
//        WebElement first1PagePrice = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[3]/div/div[1]/div[1]/div/a/div[3]/div[1]"));
        WebElement last1PagePrice = driver.findElement(By.xpath(neededXpath.last1PagePrice));
//        WebElement last1PagePrice = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[3]/div/div[1]/div[24]/div/a/div[3]/div[1]"));
        Thread.sleep(2000);

        String firstPriceString = first1PagePrice.getText();
        String lastPriceString = last1PagePrice.getText();
        firstPriceString = firstPriceString.replaceAll("\\D", "");
        lastPriceString = lastPriceString.replaceAll("\\D", "");

        if (Integer.parseInt(firstPriceString) > Integer.parseInt(lastPriceString))
        {
            System.out.println(neededMessage.sortingPriceIncorrect);
        }
        else
        {
            System.out.println(neededMessage.sortingPriceCorrect);
        }

        WebElement carsFound = driver.findElement(By.cssSelector(neededXpath.carsFound));
//        WebElement carsFound = driver.findElement(By.cssSelector("div[class='resultsAmount___3OrV7']"));
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
                WebElement currentPage = driver.findElement(By.xpath(neededXpath.currentPageStartPart1 + i + neededXpath.currentPageStartPart2));
//                WebElement currentPage = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[3]/div/div[2]/div[2]/div/ul/li[" + i + "]/a"));
                currentPage.click();
            }
            else if (i >= 9 && i <= pagesRounded+1)
            {
                WebElement currentPage = driver.findElement(By.xpath(neededXpath.currentPage8));
                currentPage.click();
            }

            else if (i == pagesRounded+2)
            {
                 WebElement currentPage = driver.findElement(By.xpath(neededXpath.currentPageLastPart1 + 9 + neededXpath.currentPageLastPart2));
                 // WebElement currentPage = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[3]/div/div[2]/div[2]/div/ul/li[" + 9 + "]/a"));
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
                WebElement carYear = driver.findElement(By.xpath(neededXpath.carYearPart1 + j + neededXpath.carYearPart2));
//                WebElement carYear = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[3]/div/div[1]/div[" + j + "]/div/a/ul/li[1]"));
                String test1 = carYear.getText();
                test1 = test1.substring(2);
//                System.out.println(test1);
                if (Integer.parseInt(test1) < 2015)
                {
                    incorrectCarOutput.incorrectCarYearOutput(test1, i);
//                    System.out.println("Car with registration year " + test1 + " is present!");
//                    System.out.println("page No:" + (i-2));
//                    System.out.println(test1);
                    incorrectYear++;
                }
            }
        }

        if (incorrectYear >= 1 ) System.out.println(neededMessage.filterByYearIncorrect);
        else if (incorrectYear == 0 ) System.out.println(neededMessage.filterByYearCorrect);
        }

    @AfterClass
    public static void closeWebBrowser ()
    {
     driver.close();
    }
}
