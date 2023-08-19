package Pages;
import com.shaft.driver.SHAFT;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class LoginPage
{
    //Shaft driver declaration
    SHAFT.GUI.WebDriver driver;
    //initialization username field by unique xpath locator
    private By userName_TextField = AppiumBy.accessibilityId("test-Username");
    //initialization password field by unique xpath locator
    private By password_TextField = AppiumBy.accessibilityId("test-Password");
    //initialization login by unique xpath locator
    private By Login_Button = AppiumBy.accessibilityId("test-LOGIN");
    //initialization invalid signin message by unique xpath locator
    private By invalidUser_PopUpMsg = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");
    //building class construction receive the session from the setup phase
    public LoginPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }
//    method input: username string
//    method purpose: typing a username string
    public LoginPage typeUserNameInUserNameField(String username)
    {
        driver.element().type(userName_TextField, username);
        return this;
    }
    //    method input:  password string
    //    method purpose: typing a password string
    public LoginPage typePasswordInPasswordField(String password)
    {
        driver.element().type(password_TextField, password);
        return this;
    }
    //    method purpose: clicking on a login button
    public LoginPage tapOnLoginButton()
    {
        driver.element().touch().tap(Login_Button);
        return this;
    }
    //    method input: invalid password string
    //    method purpose: verify that invalid signin message appear when user enter invalid username or password
    public LoginPage assertThatInvalidUserLoginPopUpMsgAppearance(String invalidPopUpMsg)
    {
        driver.assertThat().element(invalidUser_PopUpMsg).text().equals(invalidPopUpMsg);
        return this;
    }
}
