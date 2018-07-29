package Romko.gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class ActionElementOnPage
{
    public static void pressYearFilterMenuButton (String xpath, WebDriver driver)
    {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement yearFilterMenu = driver.findElement(By.xpath(xpath));
//        WebElement yearFilterMenu = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[1]/div/div/div/div[3]/div[1]/span"));
        yearFilterMenu.click();
    }

    public static void pressYearFilterSelector (String xpath, WebDriver driver)
    {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement yearFilterSelector = driver.findElement(By.xpath(xpath));
//        WebElement yearFilterSelector = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[1]/div/div/div/div[3]/div[2]/div/select"));
        yearFilterSelector.click();
    }

    public static void setFilterTo2015 (String xpath, WebDriver driver)
    {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement yearFilter2015 = driver.findElement(By.xpath(xpath));
//        WebElement yearFilter2015 = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[1]/div/div/div/div[3]/div[2]/div/select/option[5]"));
        yearFilter2015.click();
    }

    public static void orderPriceAZ (String xpath, WebDriver driver)
    {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement priceFilterAZ = driver.findElement(By.xpath(xpath));
//        WebElement priceFilterAZ = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[4]/div/div[2]/div/div[2]/div[3]/div/div/select/option[2]"));
        priceFilterAZ.click();
    }
}
