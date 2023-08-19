package Pages;

import com.shaft.driver.SHAFT;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class OverViewPage {
    //Shaft driver declaration
    SHAFT.GUI.WebDriver driver;
    //building class construction receive the session from the Cart phase
    public OverViewPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }
    //initialization item name by specific xpath locator
    private final By itemName_Text = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView)[1]");
    //initialization item price by specific xpath locator
    private final By itemPrice_Text = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"test-Price\"]/android.widget.TextView)[1]");
    //initialization finish button by specific xpath locator
    private final By finish_Button = AppiumBy.accessibilityId("test-FINISH");
    //method input: item name and price in homepage
    //method purpose: verify that item name and price in homepagge and overview page are identical
    public OverViewPage assertThatProductNameAndPriceInOverviewPageAndHomePageAreIdentical(String name, String price)
    {
        driver.assertThat().element(itemName_Text).text().isEqualTo(name).perform();
        driver.assertThat().element(itemPrice_Text).text().isEqualTo(price).perform();
        return this;
    }
    //method purpose: swipe down anc click on finish button for navigating to complete page
    public OverViewPage swipeDownAndClickOnFinishButtonAndTapOnIt()
    {
        driver.element().touch().swipeElementIntoView("Finish").tap(finish_Button);
        return this;
    }
}
