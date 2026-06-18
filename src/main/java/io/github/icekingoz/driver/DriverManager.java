package io.github.icekingoz.driver;

import org.openqa.selenium.WebDriver;

/**
 * Thread-safe driver holder. Each TestNG thread / Cucumber scenario gets its OWN
 * WebDriver via ThreadLocal, which is what makes parallel execution safe.
 */
public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() { }

    public static void set(WebDriver driver) { DRIVER.set(driver); }

    public static WebDriver get() {
        if (DRIVER.get() == null) {
            throw new IllegalStateException("Driver not initialised for this thread.");
        }
        return DRIVER.get();
    }

    public static void quit() {
        if (DRIVER.get() != null) {
            DRIVER.get().quit();
            DRIVER.remove();
        }
    }
}
