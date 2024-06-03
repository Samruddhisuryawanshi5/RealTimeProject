package com.base.Util;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;

public class CustomListerners extends TestBase implements ITestListener, ISuiteListener {

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        extentTest=extent.createTest(result.getName());
        // System.out.println("Test" + result.getTestName()+"is starting");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        extentTest.log(Status.PASS, "Test Passed!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        takeScreenshot();
        extentTest.log(Status.FAIL, "Test Failed!");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }

    @Override
    public void onStart(ISuite suite) {
        ISuiteListener.super.onStart(suite);
        extent.attachReporter(spark);
        System.out.println("suite execution is starting");
    }

    @Override
    public void onFinish(ISuite suite) {
        ISuiteListener.super.onFinish(suite);
        extent.setSystemInfo("User","Samruddhi");
        extent.setSystemInfo("Device",System.getProperty("os.name"));
        extent.setSystemInfo("Application Name","Test Application");
        spark.config().setDocumentTitle("Samruddhi Report");
        spark.config().setTheme(Theme.STANDARD);
        extent.flush();
    }
}
