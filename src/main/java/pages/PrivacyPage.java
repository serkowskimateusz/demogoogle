package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PrivacyPage {
    private final WebDriver webDriver;
    private final WebDriverWait wait;
    @FindBy(id = "L2AGLb")
    private WebElement agreeBtn;

    public PrivacyPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver,5);
    }

    public SearchPage agreeBtn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("L2AGLb")));
        agreeBtn.click();
        return new SearchPage(webDriver);
    }
}
