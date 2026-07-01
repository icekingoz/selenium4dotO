package io.github.icekingoz.bdd.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.icekingoz.driver.DriverManager;
import io.github.icekingoz.pages.InventoryPage;
import io.github.icekingoz.pages.LoginPage;
import org.testng.Assert;

/** Steps delegate to the SAME page objects the TestNG tests use - zero duplication. */
public class LoginSteps {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @Given("the login page is open")
    public void theLoginPageIsOpen() {
        loginPage = new LoginPage(DriverManager.get()).open();
    }

    @When("I log in as a standard user")
    public void iLogInAsAStandardUser() {
        inventoryPage = loginPage.loginAsStandardUser();
    }

    @When("I log in with username {string} and password {string}")
    public void iLogInWith(String username, String password) {
        loginPage.loginAs(username, password);
    }

    @Then("I should land on the products page")
    public void iShouldLandOnTheProductsPage() {
        Assert.assertTrue(inventoryPage.isLoaded(), "Products page should be loaded");
    }

    @Then("I should see the error {string}")
    public void iShouldSeeTheError(String message) {
        Assert.assertEquals(loginPage.errorText(), message);
    }
}
