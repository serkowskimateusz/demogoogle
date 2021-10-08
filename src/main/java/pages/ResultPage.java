package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage {
    private final WebDriver webDriver;
    private final WebDriverWait wait;
    @FindBy(id = "result-stats")
    private WebElement resultStats;

    public ResultPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 5);
    }

    public boolean isResultStatsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result-stats")));
        return resultStats.isDisplayed();
    }
}
