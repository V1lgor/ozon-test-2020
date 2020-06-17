package ru.vilgor.ozontest.util;

import ru.vilgor.ozontest.allure.WebDriverEventListener;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class TestSupport {

    public static EventFiringWebDriver webDriver;
    public static WebDriverWait webDriverWait;
    public static final String OZON_URL = "https://www.ozon.ru";


    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        webDriver = new EventFiringWebDriver(new ChromeDriver());
        webDriver.manage().window().maximize();

        WebDriverEventListener eventListener = new WebDriverEventListener();
        webDriver.register(eventListener);

        webDriverWait = new WebDriverWait(webDriver, 30);

        webDriver.get(OZON_URL);

    }

    @AfterMethod
    public void close() {
        webDriver.quit();
    }
}