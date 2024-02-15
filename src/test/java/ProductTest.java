import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;


public class ProductTest extends TestBase {
    ProductPage productPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;

    @DataProvider(name = "Search data")
    public static Object[][] SearchData() {
        return new Object[][]{{"Apple MacBook Pro 13-inch", "happyScenario", ""},//Happy scenario
                {"", "negativeScenarioEmptyField", "Please enter some search keyword"},//Negative scenario (Empty Field)
        };
    }

    @Test(priority = 1, dataProvider = "Search data")
    public void searchTestCase(String productName, String scenarioType, String message) {
        productPage = new ProductPage(driver);
        productPage.searchAboutProduct(productName);
        if (scenarioType.equals("happyScenario")) {
            Assert.assertEquals(driver.findElement(By.className("product-title")).getText(), productName);
            productPage.backToHome();
        } else if (scenarioType.equals("negativeScenarioEmptyField")) {
            Assert.assertEquals(driver.switchTo().alert().getText(), message);
            driver.switchTo().alert().accept();
        }
    }


    @BeforeGroups(groups = "productDetailsPage")
    public void beforeGroup() {
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);

        registrationPage.openRegistrationPage();
        String email = registrationPage.generateEmail();
        registrationPage.register("N", "G", "1", "January", "2000", email, "", "123456", "123456");
        loginPage.openLoginPage();
        loginPage.login(email, "123456");
        productPage.searchAboutProduct("Apple MacBook Pro 13-inch");
        productPage.openProductDetails();
    }

    @DataProvider(name = "Action data")
    public static Object[][] ActionData() {
        return new Object[][]{{2, "happyScenario", "The product has been added to your"},//Happy scenario
                {0, "negativeScenario", "Quantity should be positive"},//Happy scenario
        };
    }

    @Test(priority = 2, dataProvider = "Action data", groups = "productDetailsPage")
    public void addToWishListTestCase(int productQuantity, String scenarioType, String message) throws InterruptedException {
        productPage = new ProductPage(driver);
        Thread.sleep(2000);
        productPage.enterQuantity(productQuantity);
        int expectedQuantity = productPage.calculateActualValueQuantity(driver.findElement(By.className("wishlist-qty")), productQuantity);
        productPage.addToWishList();
        if (scenarioType.equals("happyScenario")) {
            Thread.sleep(1000);
            Assert.assertTrue(driver.findElement(By.className("bar-notification")).getText().contains(message));
            Assert.assertEquals(driver.findElement(By.className("wishlist-qty")).getText(), "(" + expectedQuantity + ")");
        } else if (scenarioType.equals("negativeScenario")) {
            Thread.sleep(1000);
            Assert.assertTrue(driver.findElement(By.className("bar-notification")).getText().contains(message));
        }
    }

    @Test(priority = 3, groups = "productDetailsPage")
    public void addToCompareListTestCase() throws InterruptedException {
        productPage = new ProductPage(driver);
        Thread.sleep(1000);
        productPage.addToCompareList();
        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(By.className("bar-notification")).getText().contains("The product has been added to your"));

    }

    @Test(priority = 4, dataProvider = "Action data", groups = "productDetailsPage")
    public void addToCartTestCase(int productQuantity, String scenarioType, String message) throws InterruptedException {
        productPage = new ProductPage(driver);
        Thread.sleep(2000);
        productPage.enterQuantity(productQuantity);
        int expectedQuantity = productPage.calculateActualValueQuantity(driver.findElement(By.className("cart-qty")), productQuantity);
        productPage.addToCart();
        if (scenarioType.equals("happyScenario")) {
            Thread.sleep(1000);
            Assert.assertTrue(driver.findElement(By.className("bar-notification")).getText().contains(message));
            Assert.assertEquals(driver.findElement(By.className("cart-qty")).getText(), "(" + expectedQuantity + ")");
        } else if (scenarioType.equals("negativeScenario")) {
            Thread.sleep(1000);
            Assert.assertTrue(driver.findElement(By.className("bar-notification")).getText().contains(message));
        }
    }

    @DataProvider(name = "Email friend data")
    public static Object[][] emailFriendData() {
        return new Object[][]{{"raghad@gmail.com", "Hi all", "happyScenario", "Your message has been sent."},//Happy scenario
                {"raghad.com", "Hi all", "negativeScenarioWrongEmail", "Wrong email"},//Negative scenario(Wrong Email)
                {"", "", "negativeScenarioEmptyFields", "Enter friend's email"},//Negative scenario(Empty Fields)
        };
    }

    @Test(priority = 5, dataProvider = "Email friend data", groups = "productDetailsPage")
    public void emailAFriendTestCase(String friendEmail, String personalMessage, String scenarioType, String message) throws InterruptedException {
        productPage = new ProductPage(driver);
        Thread.sleep(2000);
        productPage.clickOnEmailAFriendButton();
        productPage.fillEmailAFriendForm(friendEmail, personalMessage);
        if (scenarioType.equals("happyScenario")) {
            Assert.assertEquals(driver.findElement(By.className("result")).getText(), message);
            driver.navigate().back();
            driver.navigate().back();
        }
        if (scenarioType.equals("negativeScenarioWrongEmail") || scenarioType.equals("negativeScenarioEmptyFields")) {
            Assert.assertEquals(driver.findElement(By.id("FriendEmail-error")).getText(), message);
            driver.navigate().back();
        }
    }

    @AfterTest
    public void afterTest() {
        productPage.backToHome();
    }
}
