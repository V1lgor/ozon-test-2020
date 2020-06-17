package ru.vilgor.ozontest.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.vilgor.ozontest.util.ConfirmCodeFilled;
import ru.vilgor.ozontest.util.InputLengthExpectedCondition;

import static org.testng.Assert.assertEquals;

public class MainPage {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    public MainPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
    }

    // XPath-пути к элементам главной страницы
    // Пути к элементам, касающимся логина на сайте
    private static final String LOGIN_BUTTON_XPATH = "//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[1]/div[2]/div[2]/div/button";
    private static final String PHONE_NUMBER_INPUT_XPATH = "/html/body/div[3]/div/div/div/div/div/div/div/div/div[2]/label/div/input";
    private static final String GET_CONFIRM_CODE_BUTTON_XPATH = "/html/body/div[3]/div/div/div/div/div/div/div/div/div[3]/button";
    private static final String CONFIRM_CODE_INPUT_CONTAINER_XPATH = "/html/body/div[3]/div/div/div/div/div/div/div/div/div[1]/div/div[3]/div[1]";
    private static final String CABINET_LINK_XPATH = "//*[@id=\"__ozon\"]/div/div[1]/header/div[1]/div[4]/div[1]/a";

    private static final String CHANGE_CITY_BUTTON_XPATH = "//*[@id=\"__ozon\"]/div/div[1]/div[1]/div/button";
    private static final String CITY_INPUT_XPATH = "/html/body/div[1]/div/div[2]/div/div/div/div/div/label/div/input";
    private static final String FIRST_CITY_IN_LIST_XPATH = "/html/body/div[1]/div/div[2]/div/div/div/div/ul/li[1]";

    private static final String CITY_SHORT_NAME = "Саратов";
    private static final String CITY_FULL_NAME = "Саратов, Саратовская область";

    private static final String CATALOG_BUTTON_XPATH = "/html/body/div[1]/div/div[1]/header/div[1]/div[2]/div/div[1]/button";
    private static final String HOME_TECH_LINK_XPATH = "/html/body/div[1]/div/div[1]/header/div[1]/div[2]/div/div[2]/div/div[1]/div/a[11]";
    private static final String JUICER_LINK_XPATH = "/html/body/div[1]/div/div[1]/header/div[1]/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[1]/div[2]/div/a[6]";

    @Step("Нажать кнопку \"Вход или регистрация\"")
    public void clickLoginButton() {
        System.out.println("Шаг 1. Нажатие кнопки \"Вход или регистрация\"");
        // Создаем элемент, ассоциированный с кнопкой "Вход или регистрация" на главной странице
        WebElement loginButton = webDriver.findElement(By.xpath(LOGIN_BUTTON_XPATH));
        assertEquals(loginButton.getText(), "Вход или регистрация");
        // Кликаем по нему
        loginButton.click();
    }

    @Step("Ввод номера телефона")
    public void inputPhoneNumber() {
        System.out.println("Шаг 2. Ручной ввод номера телефона");

        // Создаем условие равенства длины содержимого атрибута value числу 16
        ExpectedCondition<Boolean> lengthCondition =
                new InputLengthExpectedCondition(PHONE_NUMBER_INPUT_XPATH, 16);

        // Ждем выполнения этого условия
        webDriverWait.until(lengthCondition);

        WebElement getConfirmCodeButton = webDriver.findElement(By.xpath(GET_CONFIRM_CODE_BUTTON_XPATH));
        assertEquals(getConfirmCodeButton.getText(), "Получить код");

        getConfirmCodeButton.click();
    }

    @Step("Ввод кода подтверждения")
    public void inputConfirmationCode() {
        System.out.println("Шаг 3. Ручной ввод кода подтверждения");
        // Ждем до тех пор, пока не появится поле ввода кода подтверждения
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CONFIRM_CODE_INPUT_CONTAINER_XPATH)));

        // В силу специфики HTML-структуры формы с кодом подтверждения, найдем блок, в котором находятся 4 поля
        // ввода цифр кода подтверждения
        WebElement codeInputListContainer = webDriver.findElement(By.xpath(CONFIRM_CODE_INPUT_CONTAINER_XPATH));

        // Создаем условие заполненности кода подтверждения
        ExpectedCondition<Boolean> confirmCodeFilledCondition = new ConfirmCodeFilled(CONFIRM_CODE_INPUT_CONTAINER_XPATH);

        assertEquals(codeInputListContainer.findElements(By.xpath(".//div/input")).size(), 4);

        webDriverWait.until(confirmCodeFilledCondition);

    }

    @Step("Проверка наличия кнопки \"Кабинет\"")
    public void checkCabinetLink() {
        System.out.println("Шаг 4. Проверка наличия кнопки \"Кабинет\"");
        // Ждем до тех пор, пока не появится поле ввода кода подтверждения
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CABINET_LINK_XPATH)));

        assertEquals(webDriver.findElement(By.xpath(CABINET_LINK_XPATH)).getAttribute("href"), "https://www.ozon.ru/my/main");
    }

    @Step("Нажатие по кнопке смены города")
    public void clickChangeCityButton() {
        WebElement changeCityButton = webDriver.findElement(By.xpath(CHANGE_CITY_BUTTON_XPATH));
        changeCityButton.click();
    }

    @Step("Ввод города \"Саратов\" в поле поиска")
    public void inputCityName() {
        WebElement cityInput = webDriver.findElement(By.xpath(CITY_INPUT_XPATH));
        cityInput.click();
        cityInput.sendKeys(CITY_SHORT_NAME);
        webDriverWait.until(ExpectedConditions.textToBe(By.xpath(FIRST_CITY_IN_LIST_XPATH), CITY_FULL_NAME));
        webDriver.findElement(By.xpath(FIRST_CITY_IN_LIST_XPATH)).click();
    }

    @Step("Проверка совпадения названия выбранного города с необходимым")
    public void checkNewCityName() {
        webDriverWait.until(ExpectedConditions.textToBe(By.xpath(CHANGE_CITY_BUTTON_XPATH), CITY_SHORT_NAME));
        assertEquals(webDriver.findElement(By.xpath(CHANGE_CITY_BUTTON_XPATH)).getText(), CITY_SHORT_NAME);
    }

    @Step("Клик по кнопке \"Каталог\"")
    public void clickCatalogButton() {
        WebElement catalogButton = webDriver.findElement(By.xpath(CATALOG_BUTTON_XPATH));
        catalogButton.click();
    }

    @Step("Перемещение курсора к категории \"Бытовая техника\"")
    public void moveCursorToHomeTechLink() {
        WebElement homeTechLink = webDriver.findElement(By.xpath(HOME_TECH_LINK_XPATH));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(homeTechLink).build().perform();
    }

    @Step("Клик по категории \"Соковыжималки\"")
    public void clickJuicerLink() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(JUICER_LINK_XPATH)));
        WebElement juicerLink = webDriver.findElement(By.xpath(JUICER_LINK_XPATH));
        juicerLink.click();
    }


}
