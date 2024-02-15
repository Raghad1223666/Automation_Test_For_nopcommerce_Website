import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {
    RegistrationPage registrationPage;

    @DataProvider(name = "Registration data")
    public static Object[][] registrationData() {
        return new Object[][]{{"Raghad", "Moeed", "1", "January", "2000", "", "Palestine", "123@Rag", "123@Rag", "Your registration completed", "happyScenario"},//Happy scenario (valid user)
                {"Raghad", "Moeed", "1", "January", "2000", "Raghad503@gmail.com", "Palestine", "123@Rag", "123@Rag", "The specified email already exists", "negativeScenarioAlreadyUsedEmail"},//Negative scenario (Already used email)
                {"Raghad", "Moeed", "1", "January", "2000", "", "Palestine", "", "", "Password is required.", "negativeScenarioWithMissingData"},//Negative scenario (With missing data)
                {"Raghad", "Moeed", "1", "January", "2000", "raghad.com", "Palestine", "123@Rag", "123@Rag", "Wrong email", "negativeScenarioWithWrongFormat"},//Negative scenario (With wrong format)
        };
    }

    @Test(dataProvider = "Registration data")
    public void registrationTestCase(String firstName, String lastName, String dateOfBirthDay, String dateOfBirthMonth, String dateOfBirthYear, String email, String company, String password, String confirmPassword, String resultMessage, String scenarioType) {
        registrationPage = new RegistrationPage(driver);

        //Generate New Email
        if (scenarioType.equals("happyScenario") || scenarioType.equals("negativeScenarioWithMissingData")) {
            email = registrationPage.generateEmail();
            System.out.println(email);
        }

        //Open Registration Page
        registrationPage.openRegistrationPage();

        //Register
        registrationPage.register(firstName, lastName, dateOfBirthDay, dateOfBirthMonth, dateOfBirthYear, email, company, password, confirmPassword);

        //Assertion
        switch (scenarioType) {
            case "happyScenario":
                Assert.assertEquals(driver.findElement(By.className("result")).getText(), resultMessage);
                Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/registerresult/1?returnUrl=/");
                break;
            case "negativeScenarioAlreadyUsedEmail":
                Assert.assertEquals(driver.findElement(By.className("validation-summary-errors")).getText(), resultMessage);
                break;
            case "negativeScenarioWithMissingData":
                Assert.assertEquals(driver.findElement(By.id("Password-error")).getText(), resultMessage);
                Assert.assertEquals(driver.findElement(By.id("ConfirmPassword-error")).getText(), resultMessage);
                break;
            case "negativeScenarioWithWrongFormat":
                Assert.assertEquals(driver.findElement(By.id("Email-error")).getText(), resultMessage);
                break;
        }
    }

    @AfterTest
    public void afterTest() {
        registrationPage.backToHome();
    }
}
