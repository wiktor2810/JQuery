import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Slider extends Base{

    Actions actions;
    WebElement slider;

    @Test
    @Parameters({"number1", "number2", "number3", "number4"})
    public void slider(String number1, String number2, String number3, String number4) {
        driver.get("https://jqueryui.com/slider/#custom-handle");
        driver.switchTo().frame(0);

        slider = driver.findElement(By.cssSelector("div[id='custom-handle']"));
        actions = new Actions(driver);

        slider.click();

        int number1AfterParse = Integer.parseInt(number1);
        int number2AfterParse = Integer.parseInt(number2);
        int number3AfterParse = Integer.parseInt(number3);
        int number4AfterParse = Integer.parseInt(number4);

        slidingForManyParameters(number1AfterParse, number2AfterParse, number3AfterParse, number4AfterParse);
    }


    public void slidingForManyParameters(int a, int b, int c, int d){

        int[] parameters = {a, b, c, d};

        for(int i =0; i < parameters.length; i++) {
            int value =  parameters[i];
            System.out.println(value);

            if(Integer.parseInt(slider.getText()) < value) {
                while (Integer.valueOf(slider.getText()) != value) {
                    actions.sendKeys(slider, Keys.ARROW_RIGHT).perform();
                }
            }else if (Integer.parseInt(slider.getText()) > value) {
                while (Integer.parseInt(slider.getText()) != value) {
                    actions.sendKeys(slider, Keys.ARROW_LEFT).perform();
                }
            }else if (Integer.parseInt(slider.getText()) == value){
                break;
            }

            int readValue = Integer.valueOf(slider.getText());
            Assert.assertEquals(readValue, value);

        }
    }

}
