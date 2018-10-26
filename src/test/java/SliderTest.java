import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.SliderPage;
import pages.SubMenuPage;

public class SliderTest extends Base{

    SubMenuPage subMenuPage;
    SliderPage sliderPage;

    @Test
    @Parameters({"number1", "number2", "number3", "number4"})
    public void slider(String number1, String number2, String number3, String number4) {

        homePage.clickSlider();
        subMenuPage = new SubMenuPage(driver);
        subMenuPage.clickCustomHandle();
        sliderPage = new SliderPage(driver);


        int number1AfterParse = Integer.parseInt(number1);
        int number2AfterParse = Integer.parseInt(number2);
        int number3AfterParse = Integer.parseInt(number3);
        int number4AfterParse = Integer.parseInt(number4);
        sliderPage.clickSlider()
                .slidingForManyParameters(number1AfterParse, number2AfterParse, number3AfterParse, number4AfterParse);
    }
}
