import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReviewPage extends PageBase {
    public ReviewPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Add your review")
    WebElement addReviewButton;
    @FindBy(id = "AddProductReview_Title")
    WebElement reviewTitleInputField;
    @FindBy(id = "AddProductReview_ReviewText")
    WebElement reviewTextInputField;
    @FindBy(id = "addproductrating_3")
    WebElement ratingCheckBox;
    @FindBy(name = "add-review")
    WebElement submitReview;

    public void openAddReviewPage() {
        addReviewButton.click();
    }

    public void addReview(String reviewTitle, String reviewText) {
        reviewTitleInputField.clear();
        reviewTitleInputField.sendKeys(reviewTitle);

        reviewTextInputField.clear();
        reviewTextInputField.sendKeys(reviewText);

        ratingCheckBox.click();
        submitReview.click();
    }
}
