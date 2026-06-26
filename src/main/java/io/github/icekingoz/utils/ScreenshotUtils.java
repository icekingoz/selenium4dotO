package io.github.icekingoz.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/** Captures PNG bytes for attaching to Allure/Extent on failure. */
public final class ScreenshotUtils {

    private ScreenshotUtils() { }

    public static byte[] asBytes(WebDriver driver) {
        if (driver == null) return new byte[0];
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
