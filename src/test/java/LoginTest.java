import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    LoginPage loginPage;
    RegistrationPage registrationPage;
     public static String userEmail;

    @BeforeTest
    public void beforeTest() {
        registrationPage = new RegistrationPage(driver);
        registrationPage.openRegistrationPage();
        userEmail = registrationPage.generateEmail();
        registrationPage.register("N", "G", "1", "January", "2000", userEmail, "", "123456", "123456");
    }

    @DataProvider(name = "login Data")
    public static Object[][] loginData() {
        return new Object[][]{{userEmail, "123456", "happyScenario", ""},//Happy scenario (valid user)
                {"raghadMoeed@gmail.com", "123456", "negativeScenarioInvalidEmail", "No customer account found"},//Negative scenario (Invalid Email)
                {userEmail, "123@@@", "negativeScenarioInvalidPassword", "The credentials provided are incorrect"},//Negative scenario (Invalid Password)
                {"", "", "negativeScenarioEmptyFields", "Please enter your email"}//Negative scenario (Empty Fields)
        };
    }

    @Test(dataProvider = "login Data")
    public void loginTestCase(String email, String password, String scenarioType, String errorMessage) {
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.login(email, password);

        if (scenarioType.equals("happyScenario")) {
            Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/");
            Assert.assertEquals(driver.findElement(By.className("ico-account")).getText(), "My account");
            Assert.assertEquals(driver.findElement(By.className("ico-logout")).getText(), "Log out");
            loginPage.logout();
        } else if (scenarioType.equals("negativeScenarioInvalidEmail")) {
            Assert.assertTrue(driver.findElement(By.className("validation-summary-errors")).getText().contains(errorMessage));
        } else if (scenarioType.equals("negativeScenarioInvalidPassword")) {
            Assert.assertTrue(driver.findElement(By.className("validation-summary-errors")).getText().contains(errorMessage));
        } else if (scenarioType.equals("negativeScenarioEmptyFields")) {
            Assert.assertEquals(driver.findElement(By.id("Email-error")).getText(), errorMessage);
        }
    }
}
