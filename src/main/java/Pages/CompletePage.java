package Pages;

import com.shaft.driver.SHAFT;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CompletePage {
    //Shaft driver declaration
    SHAFT.GUI.WebDriver driver;
    //building class construction receive the session from the overview phase
    public CompletePage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }
    private final By thankYou_TextView = AppiumBy.xpath("//android.widget.TextView[@text='THANK YOU FOR YOU ORDER']");
    //method purpose: verify that thank you for you order is appeared successfully in complete page
    public CompletePage assertONThankYouForYourOrderTextAppearance()
    {
        driver.assertThat().element(thankYou_TextView).text().isEqualTo("THANK YOU FOR YOU ORDER").perform();
        return this;
    }
}
