import org.testng.annotations.Test;
import pages.DragAndDropPage;

public class DragAndDropTest extends Base {

    DragAndDropPage dragAndDropPage;

    @Test
    public void dragAndDrop(){

        homePage.clickDroppable();
        dragAndDropPage = new DragAndDropPage(driver);
        dragAndDropPage.dragAndDropAction()
                .verifyResult();

    }

}
