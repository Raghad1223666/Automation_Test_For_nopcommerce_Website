import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class FilterProductTest extends TestBase {
    FilterProductsPage filterProductPage;

    @BeforeTest
    public void beforeTest() {
        filterProductPage = new FilterProductsPage(driver);
        filterProductPage.openFilterPage();
    }

    @Test(priority = 1)
    public void filterProductsTestCase() throws InterruptedException {
        filterProductPage.filterProducts();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.className("page-title")).getText(), "Desktops");
    }

    @DataProvider(name = "Sort data")
    public static Object[][] sortData() {
        return new Object[][]{{"Name: A to Z"}, {"Name: Z to A"}};
    }

    @Test(priority = 2, dataProvider = "Sort data")
    public void selectSortTypeTestCase(String sortType) {
        filterProductPage.filterProducts();
        filterProductPage.selectSort(sortType);

        Select select = new Select(driver.findElement(By.id("products-orderby")));
        String selectedOption = select.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedOption, sortType);
    }


    @Test(priority = 3, dataProvider = "Sort data")
    public void sortProductsTestCase(String sortType) throws InterruptedException {
        filterProductPage.filterProducts();
        filterProductPage.selectSort(sortType);
        Thread.sleep(1000);
        List<WebElement> products = driver.findElements(By.className("product-item"));
        Map<String, List<String>> result = filterProductPage.sortProducts(products, sortType);
        Thread.sleep(1000);
        Assert.assertEquals(result.get("actualResult"), result.get("expectedResult"));
    }

    @AfterTest
    public void afterTest() {
        filterProductPage.backToHome();
    }
}
