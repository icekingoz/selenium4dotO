package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import base.BaseLogger;

public abstract class BaseTest extends BaseLogger {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        logInfo("Setting up WebDriver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logInfo("WebDriver setup complete");
    }

    @AfterEach
    public void tearDown() {
        logInfo("Tearing down WebDriver");
        if (driver != null) {
            driver.quit();
        }
        logInfo("WebDriver closed");
    }

    protected void navigateTo(String url) {
        logInfo("Navigating to URL: " + url);
        driver.get(url);
    }
}