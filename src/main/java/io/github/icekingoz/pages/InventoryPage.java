package io.github.icekingoz.pages;

import io.github.icekingoz.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Page object for the SauceDemo products / inventory page. */
public class InventoryPage extends BasePage {

    private final By title = By.className("title");
    private final By inventoryItems = By.className("inventory_item");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By addBackpack = By.id("add-to-cart-sauce-labs-backpack");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return wait.urlContains("inventory.html") && "Products".equals(textOf(title));
    }

    public int productCount() {
        return driver.findElements(inventoryItems).size();
    }

    @Step("Add the backpack to the cart")
    public InventoryPage addBackpackToCart() {
        click(addBackpack);
        return this;
    }

    public int cartCount() {
        return isVisible(cartBadge) ? Integer.parseInt(textOf(cartBadge)) : 0;
    }
}
