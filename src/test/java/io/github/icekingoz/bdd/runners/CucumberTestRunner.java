package io.github.icekingoz.bdd.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Cucumber-on-TestNG runner. This is the "BDD as an alternative layer" entry point:
 * the same page objects power both plain TestNG tests and Gherkin scenarios.
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "io.github.icekingoz.bdd.stepdefinitions",
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
