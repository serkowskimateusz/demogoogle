package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleAppsPage {
    private final WebDriver webDriver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//iframe[contains(@src,'https://ogs.google.com')]")
    private WebElement webAppsFrame;

    public GoogleAppsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 5);
        this.webDriver.switchTo().frame(webAppsFrame);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li/a")));
    }

    public List<String> googleApps() {
        return googleAppsWebElements().stream()
                .map(x -> x.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public YouTubePage youTube() {
        googleAppsWebElements().stream()
                .filter(x -> x.getAttribute("href").contains("www.youtube.com"))
                .findFirst()
                .orElseThrow()
                .click();
        return new YouTubePage(webDriver);
    }

    private List<WebElement> googleAppsWebElements() {
        return this.webDriver.findElements(By.xpath("//ul/li/a"));
    }
}
