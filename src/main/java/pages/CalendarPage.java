package pages;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarPage extends BasePage{

    Date currentDate;

    public CalendarPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hasDatepicker")
    private WebElement calendar;

    @FindBy(css = ".ui-datepicker-month")
    private WebElement currentMonthWebElement;

    @FindBy(css = ".ui-datepicker-year")
    private WebElement currenYearWebElement;

    @FindBy(css = "a[class*='ui-state-highlight']")
    private WebElement currrentDayWebElement;


    public CalendarPage getCalendar(){
        driver.get("https://jqueryui.com/datepicker/#other-months");
        return this;
    }


    public CalendarPage switchToFrame(){
        driver.switchTo().frame(0);
        return this;
    }

    public CalendarPage getCurrentDate () throws java.text.ParseException{
        calendar.click();

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
        currentDate = new SimpleDateFormat("dd.MM.yyyy").parse(currentDateString);
        System.out.println(currentDate);
        return this;
    }

    public void validateDate(String[] parameters) throws java.text.ParseException{
        for (int j = 0; j < parameters.length; j++) {

            calendar.clear();
            WebElement resetClick = driver.findElement(By.cssSelector("body > p"));
            resetClick.click();
            calendar.click();

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
            int monthOfSourceDateBeforeParse = Integer.parseInt(new SimpleDateFormat("MM").format(sourceDate));
            String dayForWebElement = String.valueOf(dayOfSourceDateBeforeParse);

            List<WebElement> daysElements = driver.findElements(By.cssSelector("tbody tr td"));
            for (WebElement element: daysElements) {
                if (Integer.valueOf(element.getAttribute("data-month")).equals(monthOfSourceDateBeforeParse - 1)){
                    WebElement element1 = element.findElement(By.cssSelector("a"));
                    String dayOfMonth = element1.getText();
                    if (dayOfMonth.equalsIgnoreCase(dayForWebElement)){
                        element.click();
                    }
                }
            }
            String dateFromPageBeforeParse = calendar.getAttribute("value"); // mmDDyyyy  05/02/2019  || a sourcedatestring 02.05.2019
            System.out.println(dateFromPageBeforeParse);

            String dateFromPageString = dateFromPageBeforeParse.substring(3, 5) + "." + dateFromPageBeforeParse.substring(0, 2) + "." + dateFromPageBeforeParse.substring(6);
            System.out.println(dateFromPageString);
            Date dateFromPage = new SimpleDateFormat("dd.MM.yyyy").parse(dateFromPageString);

            Assert.assertEquals(dateFromPage, sourceDate);
        }
    }
}
