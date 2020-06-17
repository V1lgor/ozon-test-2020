package ru.vilgor.ozontest;

import org.testng.annotations.Test;
import ru.vilgor.ozontest.page.MainPage;
import ru.vilgor.ozontest.util.TestSupport;


public class AuthTest extends TestSupport {
    @Test
    public void authTest() {
        MainPage mainPage = new MainPage(webDriver, webDriverWait);

        // Шаг 1. Нажатие по кнопке "Вход или регистрация"
        mainPage.clickLoginButton();
        // Шаг 2. РУЧНОЙ ввод номера телефона
        // Данный шаг продолжается до клика по кнопке "Получить код"
        mainPage.inputPhoneNumber();
        // Шаг 3. РУЧНОЙ ввод кода подтверждения, полученного из СМС
        // Данный шаг продолжается до тех пор, пока не исчезнет форма ввода кода подтверждения
        mainPage.inputConfirmationCode();
        mainPage.checkCabinetLink();
    }
}
