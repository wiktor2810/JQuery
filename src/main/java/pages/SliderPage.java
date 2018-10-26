package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class SliderPage extends BasePage{

    List<WebElement> elements;

    public SliderPage(WebDriver driver){
        super(driver);
        switchToFrame();
        PageFactory.initElements(driver, this);
        waitToSetupElements();
    }


    @FindBy(css = "div[id='custom-handle']")
    private WebElement slider;

    public void switchToFrame(){
        driver.switchTo().frame(0);
    }

    public SliderPage clickSlider(){
        slider.click();
        return this;
    }

    public void slidingForManyParameters(int a, int b, int c, int d){

        Actions actions = new Actions(driver);
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

    public void waitToSetupElements(){
        elements = new ArrayList<WebElement>();
        elements.add(slider);
        waitForElements(elements);
    }

}
