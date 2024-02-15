import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CheckoutTest extends TestBase {
    CheckoutPage checkoutPage;
    ProductPage productPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;

    @BeforeTest
    public void beforeTest() {
        registrationPage = new RegistrationPage(driver);
        registrationPage.openRegistrationPage();
        String email = registrationPage.generateEmail();
        registrationPage.register("N", "G", "1", "January", "2000", email, "", "123456", "123456");
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.login(email, "123456");
        productPage = new ProductPage(driver);
        productPage.searchAboutProduct("Apple MacBook Pro 13-inch");
        productPage.openProductDetails();
        productPage.enterQuantity(2);
        productPage.addToCart();
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.openShoppingCartPage();
    }

    @Test(priority = 1)
    public void updateShoppingCart() {
        checkoutPage.updateShoppingCart("3");
        Assert.assertEquals(driver.findElement(By.className("qty-input")).getAttribute("value"), "3");
    }

    @Test(priority = 2)
    public void checkoutTestCase() throws InterruptedException {
        checkoutPage.checkout("Palestine", "Jenin", "Nablus Street", "6542", "0572444788");
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.cssSelector(".page-title h1")).getText(), "Thank you");
        Assert.assertEquals(driver.findElement(By.cssSelector(".order-completed .title")).getText(), "Your order has been successfully processed!");
    }
}
