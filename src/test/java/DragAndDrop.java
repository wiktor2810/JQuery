import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDrop extends Base {


    @Test
    public void dragAndDrop(){

        driver.get("https://jqueryui.com/droppable/");
        Actions action = new Actions(driver);

        driver.switchTo().frame(0);

        WebElement source = driver.findElement(By.cssSelector("#draggable"));
        WebElement target = driver.findElement(By.cssSelector("#droppable"));


        action.dragAndDrop(source, target).perform();


        String result = target.getText();
        String expectedValue = "Dropped!";
        Assert.assertEquals(result,expectedValue);
    }
}
