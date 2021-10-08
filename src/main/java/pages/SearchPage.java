package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
    private final WebDriver webDriver;
    private final WebDriverWait wait;
    @FindBy(name = "q")
    private WebElement searchInput;
    @FindBy(xpath = "//a[contains(@aria-label,'Google')]")
    private WebElement googleAppsButton;



    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 5);
    }

    public ResultPage search(String searchText) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        searchInput.sendKeys(searchText);
        searchInput.sendKeys(Keys.ENTER);
        return new ResultPage(webDriver);
    }

    public GoogleAppsPage googleAppsPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@aria-label,'Google')]")));
        googleAppsButton.click();
        return new GoogleAppsPage(webDriver);
    }
}
