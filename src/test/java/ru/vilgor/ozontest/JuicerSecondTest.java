package ru.vilgor.ozontest;

import org.testng.annotations.Test;
import ru.vilgor.ozontest.page.CartPage;
import ru.vilgor.ozontest.page.JuicersPage;
import ru.vilgor.ozontest.page.MainPage;
import ru.vilgor.ozontest.util.TestSupport;

public class JuicerSecondTest extends TestSupport {
    @Test
    public void juicerSecondTest() {
        MainPage mainPage = new MainPage(webDriver, webDriverWait);

        mainPage.clickCatalogButton();
        mainPage.moveCursorToHomeTechLink();
        mainPage.clickJuicerLink();

        JuicersPage juicersPage = new JuicersPage(webDriver, webDriverWait);

        juicersPage.inputMinPrice();
        juicersPage.inputMaxPrice();

        juicersPage.inputMinPower();

        juicersPage.checkPriceRange();

        juicersPage.sortProductListByPriceAsc();

        juicersPage.addFirstJuicerToCart();

        juicersPage.goToCart();

        CartPage cartPage = new CartPage(webDriver, webDriverWait);

        cartPage.checkJuicerAndTotalCost();
    }
}
