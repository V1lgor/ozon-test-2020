package ru.vilgor.ozontest.allure;

import ru.vilgor.ozontest.allure.ScreenshotMaker;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result){
        ScreenshotMaker.takeScreenshot();
    }
}