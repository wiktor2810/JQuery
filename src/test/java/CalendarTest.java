import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CalendarPage;

import java.util.concurrent.TimeUnit;

public class CalendarTest extends Base{

    CalendarPage calendarPage;

    @Test
    @Parameters({"Date1", "Date2", "Date3", "Date4", "Date5"})
    public void calendar(String Date1, String Date2, String Date3, String Date4, String Date5) throws java.text.ParseException {

        String[] parameters = {Date1, Date2, Date3, Date4, Date5};

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        calendarPage = new CalendarPage(driver);

        calendarPage.getCalendar()
                .switchToFrame()
                .getCurrentDate()
                .validateDate(parameters);
    }
}