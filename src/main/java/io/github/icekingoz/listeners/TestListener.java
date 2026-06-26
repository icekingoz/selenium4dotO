package io.github.icekingoz.listeners;

import io.github.icekingoz.driver.DriverManager;
import io.github.icekingoz.utils.ScreenshotUtils;
import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

/** Logs lifecycle events and attaches a screenshot to Allure on any UI failure. */
public class TestListener implements ITestListener {

    private static final Logger log = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("STARTED: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("PASSED: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("FAILED: {}", result.getName(), result.getThrowable());
        try {
            byte[] png = ScreenshotUtils.asBytes(DriverManager.get());
            if (png.length > 0) {
                Allure.addAttachment("Failure screenshot", new ByteArrayInputStream(png));
            }
        } catch (Exception ignored) {
            // API tests have no driver - nothing to capture
        }
    }
}
