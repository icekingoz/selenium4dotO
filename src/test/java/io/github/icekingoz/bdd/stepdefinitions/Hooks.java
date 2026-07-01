package io.github.icekingoz.bdd.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.icekingoz.config.ConfigReader;
import io.github.icekingoz.driver.DriverFactory;
import io.github.icekingoz.driver.DriverManager;
import io.github.icekingoz.utils.ScreenshotUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

/** Driver lifecycle for BDD scenarios + screenshot on failure. */
public class Hooks {

    @Before
    public void start() {
        WebDriver driver = DriverFactory.create(
                ConfigReader.get("browser"),
                ConfigReader.getBoolean("headless"));
        driver.manage().window().maximize();
        DriverManager.set(driver);
    }

    @After
    public void finish(Scenario scenario) {
        if (scenario.isFailed()) {
            Allure.addAttachment("Failure screenshot",
                    new ByteArrayInputStream(ScreenshotUtils.asBytes(DriverManager.get())));
        }
        DriverManager.quit();
    }
}
