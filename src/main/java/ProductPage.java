import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends PageBase {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "small-searchterms")
    WebElement searchInputField;
    @FindBy(className = "search-box-button")
    WebElement searchButton;

    @FindBy(className = "product-item")
    WebElement productCard;

    @FindBy(className = "qty-input")
    WebElement productQuantityInput;
    @FindBy(css = ".add-to-wishlist-button:nth-child(1)")
    WebElement wishListButton;
    @FindBy(css = ".add-to-compare-list-button:nth-child(1)")
    WebElement compareListButton;
    @FindBy(className = "email-a-friend-button")
    WebElement emailFriendButton;
    @FindBy(className = "add-to-cart-button")
    WebElement addToCartButton;
    @FindBy(className = "header-logo")
    WebElement headerLogo;

    @FindBy(id = "FriendEmail")
    WebElement friendEmailInput;
    @FindBy(id = "YourEmailAddress")
    WebElement yourEmailAddressInput;
    @FindBy(id = "PersonalMessage")
    WebElement personalMessageInput;
    @FindBy(name = "send-email")
    WebElement sendEmailButton;

    public void backHome() {
        headerLogo.click();
    }

    public void searchAboutProduct(String productName) {
        searchInputField.sendKeys(productName);
        searchButton.click();
    }

    public void openProductDetails() {
        productCard.click();
    }

    public void enterQuantity(int productQuantity) {
        productQuantityInput.clear();
        productQuantityInput.sendKeys("" + productQuantity);
    }

    public void addToWishList() {
        wishListButton.click();
    }

    public void addToCompareList() {
        compareListButton.click();
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public void clickOnEmailAFriendButton() {
        emailFriendButton.click();
    }

    public int calculateActualValueQuantity(WebElement previousValue, int productQuantity) {
        return Integer.parseInt(previousValue.getText().replace("(", "").replace(")", "")) + productQuantity;
    }

    public void fillEmailAFriendForm(String friendEmail, String personalMessage) {
        friendEmailInput.sendKeys(friendEmail);
        personalMessageInput.sendKeys(personalMessage);
        sendEmailButton.click();
    }
}
