package Romko.gmail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Auto1Test
{
    private static WebDriver driver;

    @BeforeClass
    public static void setup()
    {
        Messages neededMessage = new Messages();
        System.setProperty(neededMessage.driverPropertyPart1,neededMessage.driverPropertyPart2);
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette",true);

        driver = new FirefoxDriver(capabilities);
        driver.get(neededMessage.webSiteAddress);;
    }

    @Test
    public void login() throws InterruptedException
    {
        Xpaths neededXpath = new Xpaths();
        ActionElementOnPage actionWithWebSite = new ActionElementOnPage();

        actionWithWebSite.pressYearFilterMenuButton(neededXpath.yearFilterMenu, driver);

        actionWithWebSite.pressYearFilterSelector(neededXpath.yearFilterSelector, driver);

        actionWithWebSite.setFilterTo2015(neededXpath.yearFilter2015, driver);

        actionWithWebSite.orderPriceAZ(neededXpath.priceFilterAZ, driver);

        actionWithWebSite.sortingVerification(neededXpath.first1PagePrice, neededXpath.last1PagePrice, driver);

        actionWithWebSite.filterVerification(neededXpath.carsFound, neededXpath.currentPageStartPart1,
                                                neededXpath.currentPageStartPart2, neededXpath.currentPage8,
                                                neededXpath.currentPageLastPart1, neededXpath.currentPageLastPart2,
                                                neededXpath.carYearPart1, neededXpath.carYearPart2, driver);
        }

    @AfterClass
    public static void closeWebBrowser ()
    {
        driver.close();
    }
}
