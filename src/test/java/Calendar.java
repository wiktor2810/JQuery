import org.joda.time.DateTime;
import org.joda.time.Months;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Calendar {

    WebDriver driver;

    @Test
    @Parameters({"Date1", "Date2", "Date3", "Date4", "Date5"})
    public void calendar(String Date1, String Date2, String Date3, String Date4, String Date5) throws java.text.ParseException {

        String[] parameters = {Date1, Date2, Date3, Date4, Date5};


        for (int j = 0; j < parameters.length; j++) {

        System.setProperty("webdriver.chrome.driver", "src\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-extensions");
        driver = new ChromeDriver(options);

        driver.get("https://jqueryui.com/datepicker/");
        driver.switchTo().frame(0);

        WebElement calendar = driver.findElement(By.cssSelector(".hasDatepicker"));
        calendar.click();
        WebElement currentMonthWebElement = driver.findElement(By.cssSelector(".ui-datepicker-month"));
        WebElement currenYearWebElement = driver.findElement(By.cssSelector(".ui-datepicker-year"));
        WebElement currrentDayWebElement = driver.findElement(By.cssSelector("a[class*='ui-state-highlight']"));

        Map months = new HashMap();
        months.put("January", "1");
        months.put("February", "2");
        months.put("March", "3");
        months.put("April", "4");
        months.put("May", "5");
        months.put("June", "6");
        months.put("July", "7");
        months.put("August", "8");
        months.put("September", "9");
        months.put("October", "10");
        months.put("November", "11");
        months.put("December", "12");

        String year = currenYearWebElement.getText();
        String monthBeforeMap = currentMonthWebElement.getText();
        String month = (String) months.get(monthBeforeMap);
        String day = currrentDayWebElement.getText();
        String currentDateString = day + "." + month + "." + year;
        Date currentDate = new SimpleDateFormat("dd.MM.yyyy").parse(currentDateString);
        System.out.println(currentDate);

            String sourceDateString = parameters[j];

            Date sourceDate = new SimpleDateFormat("dd.MM.yyyy").parse(sourceDateString);

            int monthDifference = Months.monthsBetween(new DateTime(currentDate).withDayOfMonth(1), new DateTime(sourceDate).withDayOfMonth(1)).getMonths();

            boolean isFuture = true;

            if (monthDifference < 0) {
                isFuture = false;
                monthDifference = -1 * monthDifference;
            }

            for (int i = 0; i < monthDifference; i++) {

                if (isFuture) {
                    WebElement nextWebElement = driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
                    nextWebElement.click();
                } else {
                    WebElement prevWebElement = driver.findElement(By.xpath("//span[contains(text(),'Prev')]"));
                    prevWebElement.click();
                }

            }

            int dayOfSourceDateBeforeParse = Integer.parseInt(new SimpleDateFormat("dd").format(sourceDate));
            String dayForWebElement = String.valueOf(dayOfSourceDateBeforeParse);

            WebElement dayOfSourceDate = driver.findElement(By.linkText(dayForWebElement));
            dayOfSourceDate.click();

            String dateFromPageBeforeParse = calendar.getAttribute("value"); // mmDDyyyy  05/02/2019  || a sourcedatestring 02.05.2019
            System.out.println(dateFromPageBeforeParse);

            String dateFromPageString = dateFromPageBeforeParse.substring(3, 5) + "." + dateFromPageBeforeParse.substring(0, 2) + "." + dateFromPageBeforeParse.substring(6);
            System.out.println(dateFromPageString);
            Date dateFromPage = new SimpleDateFormat("dd.MM.yyyy").parse(dateFromPageString);

            Assert.assertEquals(dateFromPage, sourceDate);

            driver.quit();
        }
    }
}