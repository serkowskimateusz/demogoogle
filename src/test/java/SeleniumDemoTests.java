import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.GoogleAppsPage;
import pages.PrivacyPage;
import pages.SearchPage;
import pages.YouTubePage;

import static org.assertj.core.api.BDDAssertions.then;

@Execution(ExecutionMode.CONCURRENT)
class SeleniumDemoTests {

    public static final int EXPECTED_MINIMUM_NUMBER_OF_GOOGLE_APPS = 30;
    private WebDriver driver;
    private SearchPage searchPage;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.google.com");
        searchPage = new PrivacyPage(driver).agreeBtn();
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void googleResultPageShouldDisplayResultsStatsGivenDucoTextInSearchInputOnMainPage() {
        var results = searchPage.search("duco");
        then(results.isResultStatsDisplayed()).isTrue();
    }

    @Test
    void shouldDisplayGoogleAppsWhenClickOnGoogleAppsOnTopRightCorner() {
        GoogleAppsPage googleAppsPage = searchPage.googleAppsPage();
        then(googleAppsPage.googleApps().size()).isGreaterThanOrEqualTo(EXPECTED_MINIMUM_NUMBER_OF_GOOGLE_APPS);
    }

    @Test
    void shouldOpenYoutubeApplicationWhenClickOnGoogleAppsAndYoutubeIcon() {
        GoogleAppsPage googleAppsPage = searchPage.googleAppsPage();
        YouTubePage youtubePage = googleAppsPage.youTube();
        then(youtubePage.currentUrl()).matches(".*www.youtube.com.*");
    }
}