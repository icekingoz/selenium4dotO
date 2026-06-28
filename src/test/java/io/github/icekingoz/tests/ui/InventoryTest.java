package io.github.icekingoz.tests.ui;

import io.github.icekingoz.base.BaseTest;
import io.github.icekingoz.pages.InventoryPage;
import io.github.icekingoz.pages.LoginPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Shopping")
@Feature("Inventory")
public class InventoryTest extends BaseTest {

    @Test(groups = "regression")
    @Severity(SeverityLevel.NORMAL)
    @Description("All six demo products are listed after login")
    public void inventoryListsAllProducts() {
        InventoryPage inventory = new LoginPage(driver()).open().loginAsStandardUser();
        Assert.assertEquals(inventory.productCount(), 6, "SauceDemo should list 6 products");
    }

    @Test(groups = "smoke")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Adding an item updates the cart badge")
    public void addingItemUpdatesCart() {
        InventoryPage inventory = new LoginPage(driver()).open().loginAsStandardUser();
        inventory.addBackpackToCart();
        Assert.assertEquals(inventory.cartCount(), 1, "Cart badge should show 1 after adding an item");
    }
}
