package Romko.gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class ActionElementOnPage {
    public static void pressYearFilterMenuButton(String xpath, WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement yearFilterMenu = driver.findElement(By.xpath(xpath));
        yearFilterMenu.click();
    }

    public static void pressYearFilterSelector(String xpath, WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement yearFilterSelector = driver.findElement(By.xpath(xpath));
        yearFilterSelector.click();
    }

    public static void setFilterTo2015(String xpath, WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement yearFilter2015 = driver.findElement(By.xpath(xpath));
        yearFilter2015.click();
    }

    public static void orderPriceAZ(String xpath, WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement priceFilterAZ = driver.findElement(By.xpath(xpath));
        priceFilterAZ.click();
    }

    public static void sortingVerification(String xpathFirstPrice, String xpathLastPrice, WebDriver driver) throws InterruptedException {
        Messages neededMessage = new Messages();

        WebElement first1PagePrice = driver.findElement(By.xpath(xpathFirstPrice));
        WebElement last1PagePrice = driver.findElement(By.xpath(xpathLastPrice));
        Thread.sleep(2000);

        String firstPriceString = first1PagePrice.getText();
        String lastPriceString = last1PagePrice.getText();
        firstPriceString = firstPriceString.replaceAll("\\D", "");
        lastPriceString = lastPriceString.replaceAll("\\D", "");

        if (Integer.parseInt(firstPriceString) > Integer.parseInt(lastPriceString))
            System.out.println(neededMessage.sortingPriceIncorrect);
        else System.out.println(neededMessage.sortingPriceCorrect);
    }

    public static void filterVerification(String totalCars, String currentPageStartPart1, String currentPageStartPart2,
                                           String currentPage8, String currentPageLastPart1, String currentPageLastPart2,
                                           String carYearPart1, String carYearPart2, WebDriver driver) throws InterruptedException
    {
        WebElement carsFound = driver.findElement(By.cssSelector(totalCars));
        Messages incorrectCarOutput = new Messages();
        Messages neededMessage = new Messages();

        Thread.sleep(2000);
        String carsTotal = carsFound.getText();
        carsTotal =carsTotal.substring(0,carsTotal.length()-8);
        int itemsOnRegularPage = 24;
        int pagesRounded = (int) Math.ceil((Integer.parseInt(carsTotal) + .0) / (24 + .0));
        int itemsOnLastPage = Integer.parseInt(carsTotal) % 24;

        int incorrectYear = 0;

        for(int i = 3; i <=pagesRounded+2; i++)
        {
            if (i >= 3 && i < 9)
            {
                WebElement currentPage = driver.findElement(By.xpath(currentPageStartPart1 + i + currentPageStartPart2));
                currentPage.click();
            }
            else if (i >= 9 && i <= pagesRounded + 1)
            {
                WebElement currentPage = driver.findElement(By.xpath(currentPage8));
                currentPage.click();
            }
            else if (i == pagesRounded + 2)
            {
                WebElement currentPage = driver.findElement(By.xpath(currentPageLastPart1 + 9 + currentPageLastPart2));
                currentPage.click();
            }

            Thread.sleep(2000);

            int counterItems;

            if (i == pagesRounded + 2)
            {
                counterItems = itemsOnLastPage;
            }
            else
            {
               counterItems = itemsOnRegularPage;
            }

            for (int j = 1; j <= counterItems; j++)
            {
                WebElement carYear = driver.findElement(By.xpath(carYearPart1 + j + carYearPart2));
                String test1 = carYear.getText();
//                test1 = test1.substring(2);
                test1 = test1.replaceAll("\\D", "");
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
        if(incorrectYear >=1)System.out.println(neededMessage.filterByYearIncorrect);
        else if(incorrectYear ==0)System.out.println(neededMessage.filterByYearCorrect);
    }
}
