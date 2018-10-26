package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage{

    List<WebElement> elements;

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get("https://jqueryui.com/");
        PageFactory.initElements(driver, this);
        waitToSetupElements();
    }


    @FindBy(linkText = "Droppable")
    private WebElement dragAndDrop;

    @FindBy(linkText = "Datepicker")
    private WebElement calendar;

    @FindBy(linkText = "Slider")
    private WebElement slider;

    public void clickDroppable(){
        dragAndDrop.click();
    }

    public void clickDatepicker(){
        calendar.click();
    }

    public void clickSlider(){
        slider.click();
    }

    public void getHomePage(){
        driver.get("https://jqueryui.com/");
    }

    public void waitToSetupElements(){
        elements = new ArrayList<WebElement>();
        elements.add(dragAndDrop);
        elements.add(calendar);
        elements.add(slider);
        waitForElements(elements);
    }
}
