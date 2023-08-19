import Pages.*;
import com.shaft.driver.SHAFT;
import com.shaft.tools.io.JSONFileManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class Test_SauceLabsWithShaft
{
    SHAFT.GUI.WebDriver driver;
    JSONFileManager jsonFileManager;
    String productNameInHomePage;
    String productPriceInHomePage;
    @BeforeMethod(description = "Setup needed before each test method")
    public void beforeClass() throws MalformedURLException {
        new Setup(driver).setupDevice();
        driver = new SHAFT.GUI.WebDriver();
        jsonFileManager = new JSONFileManager("src/test/resources/TestDataFiles/simpleJSON.json");
    }
    @Test(description = "Validate on login successfully by valid username and password")
    public void testLoginSuccessfully()
    {
        new LoginPage(driver)
                .typeUserNameInUserNameField(jsonFileManager.getTestData("ValidUserName"))
                .typePasswordInPasswordField(jsonFileManager.getTestData("ValidPassword"))
                .tapOnLoginButton();
        new HomePage(driver)
                .assertThatHomePageProductsTextIsDisplayed(jsonFileManager.getTestData("ProductsText"));
    }
    @Test(description = "Validate on invalid login by Entering invalid username and password")
    public void testInvalidLogin()
    {
        new LoginPage(driver)
                .typeUserNameInUserNameField(jsonFileManager.getTestData("InvalidUserName"))
                .typePasswordInPasswordField(jsonFileManager.getTestData("InvalidPassword"))
                .tapOnLoginButton()
                .assertThatInvalidUserLoginPopUpMsgAppearance(jsonFileManager.getTestData("InvalidUserPopUpMsg"));
    }
    @Test(description = "Validate on after adding items to cart that if the name and price of item in homepage and cart page are identical")
    public void testAddingItemsTCartPageAndCheckthatTheNameAndPriceOfItemsInHomePageEqualsToNameAndPriceInCartPage()
    {
        new LoginPage(driver)
                .typeUserNameInUserNameField(jsonFileManager.getTestData("ValidUserName"))
                .typePasswordInPasswordField(jsonFileManager.getTestData("ValidPassword"))
                .tapOnLoginButton();
        new HomePage(driver)
                .clickOnAddToCartButtonForProduct(jsonFileManager.getTestData("ProductNameInHomePage"));
        productNameInHomePage = new HomePage(driver)
                .getProductName_InHomePage(jsonFileManager.getTestData("ProductNameInHomePage"));
        productPriceInHomePage = new HomePage(driver)
                .getProductPrice_InHomePage(jsonFileManager.getTestData("ProductNameInHomePage"));
        new HomePage(driver)
                .clickOnCartButton();
        new CartPage(driver)
                .assertThatProductNameAndPriceInHomePageAndCartPageAreSame(productNameInHomePage, productPriceInHomePage);
    }
    @Test(description = "Validate that cart is empty after removing items from cart")
    public void testRemovingItemsFromTheCartAndCheckThatCartIsEmpty()
    {
        new LoginPage(driver)
                .typeUserNameInUserNameField(jsonFileManager.getTestData("ValidUserName"))
                .typePasswordInPasswordField(jsonFileManager.getTestData("ValidPassword"))
                .tapOnLoginButton();
        new HomePage(driver)
                .clickOnAddToCartButtonForProduct(jsonFileManager.getTestData("ProductNameInHomePage"))
                .clickOnAddToCartButtonForProduct(jsonFileManager.getTestData("ProductName2HomePage"))
                .clickOnCartButton();
        new CartPage(driver)
                .clickOnRemoveProductButton(jsonFileManager.getTestData("ProductNameInCartPage"))
                .clickOnRemoveProductButton(jsonFileManager.getTestData("ProductName2CartPage"))
                .assertThatCartIsEmpty();
    }
    @Test(description = "Validate on invalid login by Entering invalid username and password")
    public void testE2ePurchasingScenarioAndValidateThePriceAndSuccessPurchase()
    {
        new LoginPage(driver)
                .typeUserNameInUserNameField(jsonFileManager.getTestData("ValidUserName"))
                .typePasswordInPasswordField(jsonFileManager.getTestData("ValidPassword"))
                .tapOnLoginButton();
        new HomePage(driver)
                .clickOnAddToCartButtonForProduct(jsonFileManager.getTestData("ProductNameInHomePage"));
        productNameInHomePage = new HomePage(driver)
                .getProductName_InHomePage(jsonFileManager.getTestData("ProductNameInHomePage"));
        productPriceInHomePage = new HomePage(driver)
                .getProductPrice_InHomePage(jsonFileManager.getTestData("ProductNameInHomePage"));
        new HomePage(driver)
                .clickOnCartButton();
        new CartPage(driver)
                .clickOnCheckOutButton()
                .typeInFirstNameField(jsonFileManager.getTestData("CheckOutFirstName"))
                .typeInLastNameField(jsonFileManager.getTestData("CheckOutLastName"))
                .typeInZipCodeNameField(jsonFileManager.getTestData("CheckOutZipCode"))
                .clickOnContinueButton();
        new OverViewPage(driver)
                .assertThatProductNameAndPriceInOverviewPageAndHomePageAreIdentical(productNameInHomePage, productPriceInHomePage)
                .swipeDownAndClickOnFinishButtonAndTapOnIt();
        new CompletePage(driver)
                .assertONThankYouForYourOrderTextAppearance();
    }
    @AfterMethod(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

}
