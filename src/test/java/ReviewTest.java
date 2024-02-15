import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReviewTest extends TestBase {
    ReviewPage reviewPage;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    ProductPage productPage;

    @BeforeTest
    public void beforeTest() {
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        reviewPage = new ReviewPage(driver);

        registrationPage.openRegistrationPage();
        String email = registrationPage.generateEmail();
        registrationPage.register("N", "G", "1", "January", "2000", email, "", "123456", "123456");
        loginPage.openLoginPage();
        loginPage.login(email, "123456");
        productPage.searchAboutProduct("Apple MacBook Pro 13-inch");
        productPage.openProductDetails();
        reviewPage.openAddReviewPage();
    }

    //.
    @DataProvider(name = "Review data")
    public static Object[][] reviewData() {
        return new Object[][]{{"RevTitle", "RevText", "happyScenario", "Product review is successfully added."},//Happy scenario
                {"", "RevText", "negativeScenario", "Review title is required."},//Negative scenario
        };
    }

    @Test(dataProvider = "Review data")
    public void addReviewTestCase(String reviewTitle, String reviewText, String scenarioType, String message) throws InterruptedException {
        reviewPage.addReview(reviewTitle, reviewText);
        Thread.sleep(1000);
        if (scenarioType.equals("happyScenario")) {
            Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), message);
            Assert.assertEquals(driver.findElement(By.cssSelector(".title + .product-review-item .review-title")).getText(), reviewTitle);
            Assert.assertEquals(driver.findElement(By.cssSelector(".title + .product-review-item .review-text")).getText(), reviewText);
            driver.navigate().back();
        } else if (scenarioType.equals("negativeScenario")) {
            Assert.assertEquals(driver.findElement(By.className("field-validation-error")).getText(), message);
        }
    }
}
