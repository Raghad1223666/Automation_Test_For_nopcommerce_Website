import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class RegistrationPage extends PageBase {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Register")
    WebElement registerLink;

    @FindBy(id = "gender-female")
    WebElement genderFemaleCheckBox;
    @FindBy(id = "FirstName")
    WebElement firstNameInputField;
    @FindBy(id = "LastName")
    WebElement lastNameInputField;
    @FindBy(name = "DateOfBirthDay")
    WebElement dateOfBirthDayDropDown;
    @FindBy(name = "DateOfBirthMonth")
    WebElement dateOfBirthMonthDropDown;
    @FindBy(name = "DateOfBirthYear")
    WebElement dateOfBirthYearDropDown;
    @FindBy(id = "Email")
    WebElement emailInputField;

    @FindBy(id = "Company")
    WebElement companyInputField;

    @FindBy(id = "Password")
    WebElement passwordInputField;
    @FindBy(id = "ConfirmPassword")
    WebElement confirmPasswordInputField;


    @FindBy(id = "register-button")
    WebElement registerButton;

    public String generateEmail() {
        Random random = new Random();
        int number = random.nextInt(1000);
        return "Raghad" + String.format("%03d", number) + "@gmail.com";
    }

    public void openRegistrationPage() {
        registerLink.click();
    }

    public void register(String firstName, String lastName, String dateOfBirthDay, String dateOfBirthMonth, String dateOfBirthYear, String email, String company, String password, String confirmPassword) {
        genderFemaleCheckBox.click();
        firstNameInputField.sendKeys(firstName);
        lastNameInputField.sendKeys(lastName);
        Select dateOfBirthDayDD = new Select(dateOfBirthDayDropDown);
        dateOfBirthDayDD.selectByVisibleText(dateOfBirthDay);
        Select dateOfBirthMonthDD = new Select(dateOfBirthMonthDropDown);
        dateOfBirthMonthDD.selectByVisibleText(dateOfBirthMonth);
        Select dateOfBirthYearDD = new Select(dateOfBirthYearDropDown);
        dateOfBirthYearDD.selectByVisibleText(dateOfBirthYear);

        emailInputField.sendKeys(email);
        companyInputField.sendKeys(company);

        passwordInputField.sendKeys(password);
        confirmPasswordInputField.sendKeys(confirmPassword);

        registerButton.click();
    }
}
