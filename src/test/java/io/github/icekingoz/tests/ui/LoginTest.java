package io.github.icekingoz.tests.ui;

import io.github.icekingoz.base.BaseTest;
import io.github.icekingoz.pages.InventoryPage;
import io.github.icekingoz.pages.LoginPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Authentication")
@Feature("Login")
public class LoginTest extends BaseTest {

    @Test(groups = "smoke")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Standard user can log in and reach the products page")
    public void standardUserCanLogIn() {
        InventoryPage inventory = new LoginPage(driver()).open().loginAsStandardUser();
        Assert.assertTrue(inventory.isLoaded(), "Products page should load after login");
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"standard_user", "wrong", "Epic sadface: Username and password do not match any user in this service."},
                {"", "secret_sauce", "Epic sadface: Username is required."}
        };
    }

    @Test(groups = "regression", dataProvider = "invalidCredentials")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Invalid credentials are rejected with the correct message")
    public void invalidLoginShowsError(String user, String pass, String expected) {
        LoginPage login = new LoginPage(driver()).open();
        login.loginAs(user, pass);
        Assert.assertEquals(login.errorText(), expected);
    }
}
