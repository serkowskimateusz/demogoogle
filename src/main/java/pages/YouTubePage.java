package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YouTubePage {
    private final WebDriver webDriver;

    public YouTubePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String currentUrl() {
        var wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.titleContains("YouTube"));
        return webDriver.getCurrentUrl();
    }
}
