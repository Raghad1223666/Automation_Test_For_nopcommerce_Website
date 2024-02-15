import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class FilterProductsPage extends PageBase {
    public FilterProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".header-menu ul > li:nth-child(1)")
    WebElement firstItemInHeaderMenu;

    @FindBy(css = ".listbox .sublist > li:nth-child(1) a")
    WebElement firstItemInSideMenu;

    @FindBy(id = "products-orderby")
    WebElement productsSortDropDown;

    @FindBy(id = "product-grid")
    WebElement products;

    public void openFilterPage() {
        firstItemInHeaderMenu.click();
    }

    public void filterProducts() {
        firstItemInSideMenu.click();

    }

    public void selectSort(String sortType) {
        Select select = new Select(productsSortDropDown);
        select.selectByVisibleText(sortType);
    }

    public Map<String, List<String>> sortProducts(List<WebElement> products, String sortType) {
        List<String> productsName = new ArrayList<>(products.size());
        for (int i = 0; i < products.size(); i++) {
            String productText = products.get(i).findElement(By.className("product-title")).getText();
            productsName.add(productText);
        }
        List<String> expectedResultProductsName = new ArrayList<String>(productsName);
        if (sortType.equals("Name: A to Z")) {
            Collections.sort(expectedResultProductsName);
        } else if (sortType.equals("Name: Z to A")) {
            Collections.sort(expectedResultProductsName);
            Collections.reverse(expectedResultProductsName);
        }

        Map<String, List<String>> result = new HashMap<>();
        result.put("actualResult", productsName);
        result.put("expectedResult", expectedResultProductsName);
        return result;
    }
}
