package io.github.icekingoz.base;

import io.github.icekingoz.config.ConfigReader;
import io.github.icekingoz.driver.DriverFactory;
import io.github.icekingoz.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * UI test lifecycle. Each test method gets a fresh, thread-local driver and
 * a guaranteed quit - the foundation of safe parallel runs.
 */
public abstract class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriver driver = DriverFactory.create(
                ConfigReader.get("browser"),
                ConfigReader.getBoolean("headless"));
        driver.manage().window().maximize();
        DriverManager.set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quit();
    }

    protected WebDriver driver() {
        return DriverManager.get();
    }
}
