package ru.vilgor.ozontest.allure;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.vilgor.ozontest.util.TestSupport;

public class ScreenshotMaker {

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] takeScreenshot(){
        return ((TakesScreenshot) TestSupport.webDriver).getScreenshotAs(OutputType.BYTES);
    }
}

