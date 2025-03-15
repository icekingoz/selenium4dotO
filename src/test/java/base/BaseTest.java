package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest extends BaseLogger {
    protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        logInfo("Setting up WebDriver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logInfo("WebDriver setup complete");
    }

    @AfterTest
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