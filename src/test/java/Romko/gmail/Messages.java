package Romko.gmail;

public class Messages
{
    String driverPropertyPart1 = "webdriver.gecko.driver";
    String driverPropertyPart2 = "C:\\GeckoDriver.exe";
    String webSiteAddress = "https://www.autohero.com/de/search/";

    String sortingPriceIncorrect = "Sorting by price works INCORRECTLY!!!";
    String sortingPriceCorrect  = "Sorting by price works correctly";
    String filterByYearIncorrect = "Filter by year works INCORRECTLY";
    String filterByYearCorrect = "Filter by year works correctly";

    public static void incorrectCarYearOutput (String test1, int i)
    {
        System.out.println("Car with registration year " + test1 + " is present!");
        System.out.println("page No:" + (i-2));
        System.out.println(test1);
    }

}
