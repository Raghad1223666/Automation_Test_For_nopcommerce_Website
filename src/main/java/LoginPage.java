import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "ico-login")
    WebElement loginLink;

    @FindBy(id = "Email")
    WebElement emailInputField;
    @FindBy(id = "Password")
    WebElement passwordInputField;
    @FindBy(id = "RememberMe")
    WebElement rememberMeCheckBox;

    @FindBy(className = "login-button")
    WebElement loginButton;

    @FindBy(className = "ico-logout")
    WebElement logoutLink;

    public void openLoginPage() {
        loginLink.click();
    }

    public void login(String email, String password) {
        emailInputField.sendKeys(email);
        passwordInputField.sendKeys(password);
        rememberMeCheckBox.click();
        loginButton.click();
    }

    public void logout() {
        logoutLink.click();
    }
}
