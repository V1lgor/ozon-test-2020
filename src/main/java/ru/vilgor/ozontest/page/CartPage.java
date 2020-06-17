package ru.vilgor.ozontest.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class CartPage {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    public CartPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
    }

    private static final String TOTAL_PRODUCT_COST_XPATH = "/html/body/div[1]/div/div[1]/div/div/div[3]/div[4]/div[1]/div[1]/div/div[2]/div[3]/div[3]/div[1]/div/span";
    private static final String TOTAL_ORDER_COST_XPATH = "/html/body/div[1]/div/div[1]/div/div/div[3]/div[4]/div[1]/div[1]/div/div[2]/div[3]/div[3]/div[1]/div/span";

    private String removeNonDigitalChars(String str) {
        return str.replaceAll("[^\\d]*", "");
    }

    @Step("Проверка совпадения цены товара в списке товаров с общей стоимостью заказа")
    public void checkJuicerAndTotalCost() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TOTAL_PRODUCT_COST_XPATH)));
        WebElement totalProductCost = webDriver.findElement(By.xpath(TOTAL_PRODUCT_COST_XPATH));
        WebElement totalOrderCost = webDriver.findElement(By.xpath(TOTAL_ORDER_COST_XPATH));

        assertEquals(removeNonDigitalChars(totalProductCost.getText()), removeNonDigitalChars(totalOrderCost.getText()));
    }
}
