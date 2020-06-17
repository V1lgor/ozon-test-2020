package ru.vilgor.ozontest.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

// Класс, описывающий ожидаемое условие - длина содержимого атрибута value элемента должна совпадать с заданной
public class InputLengthExpectedCondition implements ExpectedCondition<Boolean> {
    private String elementXPath;    // XPath-путь к элементу
    private int requiredLength;     // Необходимая длина

    public InputLengthExpectedCondition(String elementXPath, int requiredLength) {
        this.elementXPath = elementXPath;
        this.requiredLength = requiredLength;
    }

    @Override
    public Boolean apply(WebDriver webDriver) {
        return webDriver.findElement(By.xpath(elementXPath)).getAttribute("value").length() == requiredLength;
    }
}
