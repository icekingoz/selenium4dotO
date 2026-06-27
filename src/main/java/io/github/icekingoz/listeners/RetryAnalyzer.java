package io.github.icekingoz.listeners;

import io.github.icekingoz.config.ConfigReader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retries a failed test up to retry.count times. Retries are a triage tool,
 * not a cover-up: genuinely flaky tests should still be investigated.
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private int attempts = 0;
    private final int maxRetries = ConfigReader.getInt("retry.count");

    @Override
    public boolean retry(ITestResult result) {
        if (attempts < maxRetries) {
            attempts++;
            return true;
        }
        return false;
    }
}
