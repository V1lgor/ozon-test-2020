package ru.vilgor.ozontest.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

// Условие ожидания, суть которого - заполненность поля для кода потверждения
public class ConfirmCodeFilled implements ExpectedCondition<Boolean> {
    private String codeInputListContainerXPath; // XPath-путь к контейнеру с полями ввода

    public ConfirmCodeFilled(String codeInputListContainerXPath) {
        this.codeInputListContainerXPath = codeInputListContainerXPath;
    }

    @Override
    public Boolean apply(WebDriver webDriver) {

        // Проверяем отсутствие формы ввода кода
        return webDriver.findElements(By.xpath(codeInputListContainerXPath)).size() == 0;
        /*// Находим контейнер с полями ввода
        WebElement codeInputListContainer = webDriver.findElement(By.xpath(codeInputListContainerXPath));


        // Находим в этом контейнере список полей ввода
        List<WebElement> codeInputList = codeInputListContainer.findElements(By.xpath(".//div/input"));

        // Проходим по каждому полю и возвращаем false, если хоть одно из полей пустое
        for (WebElement codeInput : codeInputList) {
            if (codeInput.getAttribute("value").length() == 0) return false;
        }

        return true;*/
    }
}
