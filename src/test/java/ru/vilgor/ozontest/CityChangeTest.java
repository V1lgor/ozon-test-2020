package ru.vilgor.ozontest;

import org.testng.annotations.Test;
import ru.vilgor.ozontest.page.MainPage;
import ru.vilgor.ozontest.util.TestSupport;

public class CityChangeTest extends TestSupport {

    @Test
    public void cityChangeTest() {
        MainPage mainPage = new MainPage(webDriver, webDriverWait);

        mainPage.clickChangeCityButton();
        mainPage.inputCityName();
        mainPage.checkNewCityName();
    }
}
