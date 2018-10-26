package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubMenuPage extends BasePage{

    public SubMenuPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Dates in other months")
    private WebElement otherMonths;

    @FindBy(linkText = "Custom handle")
    private WebElement customHandle;

    public void clickOtherMonths(){
        otherMonths.click();
    }

    public void clickCustomHandle(){
        customHandle.click();
    }

}
