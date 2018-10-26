package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class DragAndDropPage extends BasePage{

    List<WebElement> elements;

    public DragAndDropPage(WebDriver driver){
        super(driver);
        switchToFrame();
        PageFactory.initElements(driver, this);
        waitToSetupElements();
    }

    @FindBy(css = "#draggable")
    private WebElement draggable;

    @FindBy(css = "#droppable")
    private WebElement droppable;

    public void switchToFrame(){
        driver.switchTo().frame(0);
    }

    public DragAndDropPage dragAndDropAction(){
        Actions action = new Actions(driver);
        action.dragAndDrop(draggable, droppable).perform();
        return this;
    }

    public void verifyResult(){
        String result = droppable.getText();
        String expectedValue = "Dropped!";
        Assert.assertEquals(result,expectedValue);
    }

    public void waitToSetupElements(){
        elements = new ArrayList<WebElement>();
        elements.add(draggable);
        elements.add(droppable);
        waitForElements(elements);
    }
}
