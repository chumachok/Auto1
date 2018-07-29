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
        driver.get(neededMessage.webSiteAddress);
    }

    @Test
    public void login() throws InterruptedException
    {
        Xpaths neededXpath = new Xpaths();
        ActionElementOnPage actionWithWebSite = new ActionElementOnPage();

        actionWithWebSite.ActionOnPage(neededXpath.yearFilterMenu, driver); // open first registration year submenu
        actionWithWebSite.ActionOnPage(neededXpath.yearFilterSelector, driver); // open year dropdown
        actionWithWebSite.ActionOnPage(neededXpath.yearFilter2015, driver); // select 2015 year
        actionWithWebSite.ActionOnPage(neededXpath.priceFilterAZ, driver); // apply sorting A-Z (price) for result list
        actionWithWebSite.sortingVerification(neededXpath.first1PagePrice, neededXpath.last1PagePrice, driver); // verify sorting works correctly

        actionWithWebSite.filterVerification(neededXpath.carsFound, neededXpath.currentPageStartPart1,
                                                neededXpath.currentPageStartPart2, neededXpath.currentPage8,
                                                neededXpath.currentPageLastPart1, neededXpath.currentPageLastPart2,
                                                neededXpath.carYearPart1, neededXpath.carYearPart2, driver); // Filter by year verification
        }

    @AfterClass
    public static void closeWebBrowser ()
    {
        driver.close();
    }
}
