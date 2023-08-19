package Pages;

import com.shaft.driver.SHAFT;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CartPage
{
    //Shaft driver declaration
    SHAFT.GUI.WebDriver driver;
    //method input: chosen product name
    // method ouput: specific name xpath locator for the chosen product
    private By productName_InCartPage (String name)
    {
        return AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[@text='"+name+"']");
    }
    //method input: chosen product name
    // method ouput: specific price xpath locator for the chosen product
    private By productPrice_InCartPage (String name)
    {
        return AppiumBy.xpath("//android.widget.TextView[@text='"+name+"']/../..//android.view.ViewGroup[@content-desc=\"test-Price\"]/android.widget.TextView");
    }
    //method input: chosen product name
    // method ouput: specific remove button xpath locator for the chosen product
    private By removeProduct_Button (String name)
    {
        return AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[@text='"+name+"']/../..//android.view.ViewGroup[@content-desc=\"test-REMOVE\"]");
    }
    //initialization xpath locator for remove button for anu product
    private final By genericRemoveButton = AppiumBy.accessibilityId("test-REMOVE");
    //initialization checkout text by specific xpath locator
    private final By checkOut_Button = AppiumBy.accessibilityId("test-CHECKOUT");
    //initialization firstname field by specific xpath locator
    private final By checkOutFirstName_Field = AppiumBy.accessibilityId("test-First Name");
    //initialization lastname field by specific xpath locator
    private final By checkOutLastName_Field = AppiumBy.accessibilityId("test-Last Name");
    //initialization postal code field by specific xpath locator
    private final By checkOutZipCode_Field = AppiumBy.accessibilityId("test-Zip/Postal Code");
    //initialization continue button by specific xpath locator
    private final By checkOutContinue_Button = AppiumBy.accessibilityId("test-CONTINUE");
    //building class construction receive the session from the homepage phase
    public CartPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }
    //method input: product name value and price value in homepage
    //method purpose: verify that product price in homepage and cartpage are identical
    public CartPage assertThatProductNameAndPriceInHomePageAndCartPageAreSame(String name, String price)
    {
        driver.assertThat().element(productName_InCartPage(name)).text().isEqualTo(name).perform();
        driver.assertThat().element(productPrice_InCartPage(name)).text().isEqualTo(price).perform();
        return this;
    }
    //method input: chosen product name string
    //method purpose: click on  remove button for the chosen product
    public CartPage clickOnRemoveProductButton(String name)
    {
        driver.element().click(removeProduct_Button(name));
        return this;
    }
    //method purpose: verify that cart is empty by seeking if there is any remove button in the page
    public CartPage assertThatCartIsEmpty()
    {
        driver.element().assertThat(genericRemoveButton).doesNotExist().perform();
        return this;
    }
    //method purpose: click on checkout button for the chosen product
    public CartPage clickOnCheckOutButton()
    {
        driver.element().click(checkOut_Button);
        return this;
    }
    //method input: firstname string
    //method purpose: fill in firstname field in the purchasing request
    public CartPage typeInFirstNameField(String firstName)
    {
        driver.element().type(checkOutFirstName_Field, firstName);
        return this;
    }
    //method input: lastname string
    //method purpose: fill in lastname field in the purchasing request
    public CartPage typeInLastNameField(String lastName)
    {
        driver.element().type(checkOutLastName_Field, lastName);
        return this;
    }
    //method input: postal code string
    //method purpose: fill in postal code field in the purchasing request
    public CartPage typeInZipCodeNameField(String zipCode)
    {
        driver.element().type(checkOutZipCode_Field, zipCode);
        return this;
    }
    //method purpose: clicking on continue for navigating to overview page
    public CartPage clickOnContinueButton()
    {
        driver.element().click(checkOutContinue_Button);
        return this;
    }
}
