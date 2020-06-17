package ru.vilgor.ozontest.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.testng.Assert.assertEquals;

public class JuicersPage {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    public JuicersPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
    }

    private static final String MIN_PRICE_INPUT_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[1]/div/aside/div[2]/div[2]/div[2]/div[1]/input";
    private static final String MAX_PRICE_INPUT_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[1]/div/aside/div[2]/div[2]/div[2]/div[2]/input";

    private static final String PRICE_RANGE_SPAN_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[2]/div[2]/div[2]/div/div/button/div/span";

    private static final String EXPECTED_PRICE_RANGE_BLOCK_TEXT = "Цена: от 3 000 до 4 000";

    private static final String SORTING_OPTIONS_SELECT_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[2]/div[2]/div[1]/div/div/div[1]/div/div/input";


    private static final String FIRST_JUICER_ADD_TO_CART_BUTTON_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[2]/div[3]/div[1]/div[1]/div/div[2]/div/div/div[3]/div[3]/div/div/button";

    private static final String CART_LINK_XPATH = "/html/body/div[1]/div/div[1]/header/div[1]/div[4]/a[2]";

    private static final String MIN_POWER_INPUT_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[1]/div/aside/div[8]/div[2]/div[2]/div[1]/input";
    private static final String MAX_POWER_INPUT_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[1]/div/aside/div[8]/div[2]/div[2]/div[2]/input";

    @Step("Ввод минимальной цены")
    public void inputMinPrice() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MIN_PRICE_INPUT_XPATH)));
        WebElement minPriceInput = webDriver.findElement(By.xpath(MIN_PRICE_INPUT_XPATH));
        minPriceInput.click();
        minPriceInput.sendKeys(Keys.CONTROL + "a");
        minPriceInput.sendKeys("3000");
    }

    @Step("Ввод максимальной цены")
    public void inputMaxPrice() {
        WebElement maxPriceInput = webDriver.findElement(By.xpath(MAX_PRICE_INPUT_XPATH));
        maxPriceInput.click();
        maxPriceInput.sendKeys(Keys.CONTROL + "a");
        maxPriceInput.sendKeys("4000");
        maxPriceInput.sendKeys(Keys.ENTER);
    }

    @Step("Проверка соответствия результатов поиска введенным параметрам")
    public void checkPriceRange() {
        webDriverWait.until(ExpectedConditions.textToBe(By.xpath(PRICE_RANGE_SPAN_XPATH), EXPECTED_PRICE_RANGE_BLOCK_TEXT));
        WebElement priceRangeBlock = webDriver.findElement(By.xpath(PRICE_RANGE_SPAN_XPATH));

        assertEquals(priceRangeBlock.getText(), EXPECTED_PRICE_RANGE_BLOCK_TEXT);
    }

    @Step("Сортировка списка товаров по возрастанию цены")
    public void sortProductListByPriceAsc() {
        WebElement sortingOptionList = webDriver.findElement(By.xpath(SORTING_OPTIONS_SELECT_XPATH));

        sortingOptionList.click();

        sortingOptionList.sendKeys(Keys.DOWN);
        sortingOptionList.sendKeys(Keys.DOWN);
        sortingOptionList.sendKeys(Keys.ENTER);
    }

    @Step("Добавление первой соковыжималки в корзину")
    public void addFirstJuicerToCart() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(FIRST_JUICER_ADD_TO_CART_BUTTON_XPATH)));

        WebElement addToCartButton = webDriver.findElement(By.xpath(FIRST_JUICER_ADD_TO_CART_BUTTON_XPATH));
        addToCartButton.click();
    }

    @Step("Переход в корзину")
    public void goToCart() {
        WebElement cartLink = webDriver.findElement(By.xpath(CART_LINK_XPATH));

        cartLink.click();
    }

    @Step("Выбрать мощность от 1000 Ватт")
    public void inputMinPower() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MIN_POWER_INPUT_XPATH)));
        WebElement minPowerInput = webDriver.findElement(By.xpath(MIN_POWER_INPUT_XPATH));
        minPowerInput.click();
        minPowerInput.sendKeys(Keys.CONTROL + "a");
        minPowerInput.sendKeys("1000");
        minPowerInput.sendKeys(Keys.ENTER);
    }

}
