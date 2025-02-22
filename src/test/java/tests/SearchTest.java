package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest extends BaseTest {

    @Test
    public void testGoogleSearch() {
        logInfo("Starting Google Search Test");

        // Navigate to Google
        navigateTo("https://www.google.com");
        logInfo("Navigated to Google");

        // Perform a search
        logDebug("Entering search query: 'Selenium WebDriver'");
        driver.findElement(By.name("q")).sendKeys("Selenium WebDriver");
        driver.findElement(By.name("q")).submit();
        logInfo("Search query submitted");

        // Validate the page title
        String title = driver.getTitle();
        logInfo("Page title after search: " + title);
        assertTrue(title.contains("Selenium WebDriver"), "Page title should contain 'Selenium WebDriver'");

        logInfo("Google Search Test passed successfully");
    }
}