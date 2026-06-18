package io.github.icekingoz.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Builds a WebDriver for the requested browser.
 * Selenium 4's built-in Selenium Manager auto-resolves the driver binary,
 * so there is no WebDriverManager and no manual chromedriver to maintain.
 */
public final class DriverFactory {

    private DriverFactory() { }

    public static WebDriver create(String browser, boolean headless) {
        return switch (browser.toLowerCase()) {
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                if (headless) options.addArguments("-headless");
                yield new FirefoxDriver(options);
            }
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                if (headless) options.addArguments("--headless=new");
                options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");
                yield new ChromeDriver(options);
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }
}
