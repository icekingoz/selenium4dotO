package io.github.icekingoz.base;

import io.github.icekingoz.config.ConfigReader;
import io.github.icekingoz.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Common page behaviour. Page objects extend this; tests never touch WebDriver
 * directly - they speak to page objects, which speak to these helpers.
 */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WaitUtils wait;
    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, ConfigReader.getInt("explicit.wait"));
    }

    protected void open(String url) {
        log.info("Navigating to {}", url);
        driver.get(url);
    }

    protected void click(By locator) {
        wait.clickable(locator).click();
    }

    protected void type(By locator, String text) {
        wait.visible(locator).sendKeys(text);
    }

    protected String textOf(By locator) {
        return wait.visible(locator).getText();
    }

    protected boolean isVisible(By locator) {
        try {
            return wait.visible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
