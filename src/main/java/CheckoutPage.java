import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends PageBase {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "topcartlink")
    WebElement shoppingCartButton;

    @FindBy(className = "qty-input")
    WebElement quantityInput;

    @FindBy(id = "updatecart")
    WebElement updateShoppingCart;
    @FindBy(id = "termsofservice")
    WebElement termsOfServiceCheckBox;
    @FindBy(id = "checkout")
    WebElement checkoutButton;
    @FindBy(id = "BillingNewAddress_CountryId")
    WebElement billingNewAddressCountryDropDown;
    @FindBy(id = "BillingNewAddress_City")
    WebElement billingNewAddressCity;
    @FindBy(id = "BillingNewAddress_Address1")
    WebElement billingNewAddress1;
    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    WebElement billingNewAddressZipPostalCode;
    @FindBy(id = "BillingNewAddress_PhoneNumber")
    WebElement billingNewAddressPhoneNumber;
    @FindBy(name = "save")
    WebElement saveButton;

    @FindBy(className = "shipping-method-next-step-button")
    WebElement shippingMethodContinueButton;
    @FindBy(className = "payment-method-next-step-button")
    WebElement paymentMethodContinueButton;
    @FindBy(className = "payment-info-next-step-button")
    WebElement paymentInfoContinueButton;
    @FindBy(className = "confirm-order-next-step-button")
    WebElement orderConfirmButton;


    public void openShoppingCartPage() {
        shoppingCartButton.click();
    }

    public void updateShoppingCart(String quantityValue) {
        quantityInput.clear();
        quantityInput.sendKeys(quantityValue);
        updateShoppingCart.click();
    }

    public void checkout(String country, String city, String address1, String postalCode, String phoneNumber) throws InterruptedException {
        termsOfServiceCheckBox.click();
        checkoutButton.click();
        Select billingNewAddressCountryDD = new Select(billingNewAddressCountryDropDown);
        billingNewAddressCountryDD.selectByVisibleText(country);
        billingNewAddressCity.sendKeys(city);
        billingNewAddress1.sendKeys(address1);
        billingNewAddressZipPostalCode.sendKeys(postalCode);
        billingNewAddressPhoneNumber.sendKeys(phoneNumber);
        saveButton.click();
        Thread.sleep(2000);
        shippingMethodContinueButton.click();
        Thread.sleep(2000);
        paymentMethodContinueButton.click();
        Thread.sleep(1000);
        paymentInfoContinueButton.click();
        Thread.sleep(1000);
        orderConfirmButton.click();
    }
}
