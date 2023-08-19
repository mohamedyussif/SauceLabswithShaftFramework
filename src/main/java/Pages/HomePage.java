package Pages;
import com.shaft.driver.SHAFT;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class HomePage
{
    //Shaft driver declaration
    SHAFT.GUI.WebDriver driver;
    //method input: chosen product name
    // method ouput: specific name xpath locator for the chosen product
    private By chosenItemName(String name)
    {
        return AppiumBy.xpath("//android.widget.TextView[(@text='"+name+"')]");
    }
    //method input: chosen product name
    // method ouput: specific price xpath locator for the chosen product
    private By chosenItemPrice(String name)
    {
        return AppiumBy.xpath("//android.widget.TextView[(@text='"+name+"')]/..//android.widget.TextView[@content-desc=\"test-Price\"]");
    }
    //method input: chosen product name
    // method ouput: specific add to cart button xpath locator for the chosen product
    private By chosenItemAddToCartButton(String name)
    {
        return AppiumBy.xpath("//android.widget.TextView[(@text='"+name+"')]//..//android.view.ViewGroup[@content-desc='test-ADD TO CART']");
    }
    //initialization PRODUCTS text by specific xpath locator
    private final By products_TextView = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Toggle\"]/../android.widget.TextView");
    //initialization cart icon by specific xpath locator
    private final By cart_button = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView");
    //building class construction receive the session from the login phase
    public HomePage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }
    //method input: PRODUCTS string in homepage
    //method purpose: verify that user signin successfully by appearing products text
    public HomePage assertThatHomePageProductsTextIsDisplayed(String productsText)
    {
        driver.assertThat().element(products_TextView).text().isEqualTo(productsText).perform();
        return this;
    }
    //method input: chosen product name string
    //method purpose: return the name value of the product
    public String getProductName_InHomePage(String name)
    {
         return driver.element().getText(chosenItemName(name));
    }
    //method input: chosen product name string
    //method purpose: return the price value of the product
    public String getProductPrice_InHomePage(String name)
    {
        return driver.element().getText(chosenItemPrice(name));
    }
    //method input: chosen product name string
    //method purpose: click on the add to cart button depends on the name of the product
    public HomePage clickOnAddToCartButtonForProduct(String name)
    {
         driver.element().touch().tap(chosenItemAddToCartButton(name));
         return this;
    }
    //method purpose: clicking on the cart icon for navigating to cart page
    public HomePage clickOnCartButton()
    {
        driver.element().click(cart_button);
        return this;
    }
}
