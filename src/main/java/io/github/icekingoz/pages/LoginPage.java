package io.github.icekingoz.pages;

import io.github.icekingoz.base.BasePage;
import io.github.icekingoz.config.ConfigReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Page object for https://www.saucedemo.com/ login. */
public class LoginPage extends BasePage {

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open the login page")
    public LoginPage open() {
        open(ConfigReader.get("ui.base.url"));
        return this;
    }

    @Step("Log in as {user}")
    public void loginAs(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(loginButton);
    }

    @Step("Log in with the configured standard user")
    public InventoryPage loginAsStandardUser() {
        loginAs(ConfigReader.get("sauce.username"), ConfigReader.get("sauce.password"));
        return new InventoryPage(driver);
    }

    public String errorText() {
        return textOf(errorMessage);
    }
}
