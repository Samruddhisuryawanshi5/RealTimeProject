package com.base.Util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@Listeners(CustomListerners.class)
public class TestBase {
    private static final Logger log= LogManager.getLogger(TestBase.class);
    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("target/Reports/ExtentReport.html");
    public static ExtentTest extentTest;
    public static WebDriver driver = null;

    public WebDriver openBrowser(String name) {
        if (name.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        } else if (name.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        } else {
            log.info("Browser name does not exist");
        }
        driver.manage().window().maximize();
        return driver;

    }

    public void navigateTo(String url) {

        driver.get(url);
        log.info("Navigated to url - "+url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        takeScreenshot();
    }

    public String takeScreenshot() {
        String destination = null;
        File output = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String path = System.getProperty("user.dir") + "\\target\\Reports\\Screenshots\\" + timeStamp+".png";
            FileUtils.copyFile(output, new File(path));
            log.info("screenshot is taken - "+path);
            destination="./Screenshots/" + timeStamp+".png";
            //extentTest.info("ScreenShot",MediaEntityBuilder.createScreenCaptureFromPath("./Screenshots/" + timeStamp+".png").build());

        } catch (IOException e) {
            log.info("failed to copy screenshot");
        }
        return destination;
    }

    public void quitBrowser() {
        driver.quit();
    }
    public void handleLogging(String type,String value,boolean screenshot) {
        String logMessgae="";
        switch (type){
            case "sendkeys":
                logMessgae= (value+" sendkeys is perfomed on webelement");
                break;
            case "click":
                logMessgae=(value+" is clicked");
                break;
            case "select":
                logMessgae=(value+"is selcted");
            default:
                logMessgae="Unknown selnium action is performed";

        }
        if(screenshot){
            extentTest.info(logMessgae, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        else {
            extentTest.info(logMessgae);
        }

        log.info(logMessgae);

    }
    public void typeUsingWebElement(WebElement webElement,String value,boolean screenshot){
        webElement.sendKeys(value);
        handleLogging("sendkeys","webElement",screenshot);
    }
    public void clickUsingWebElement(WebElement webElement, boolean screenshot){
        webElement.click();
        handleLogging("click","WebElement",screenshot);
    }
    public void selectFromDropdown(String xpath,String text,boolean screenshot){
        Select value=new Select(driver.findElement(By.xpath(xpath)));
        value.selectByVisibleText(text);
        handleLogging("select",text,screenshot);
    }
    public void selectFromDropdown(WebElement element,String type,String value,boolean screenshot){
        Select select=new Select(element);
        switch (type){
            case "text":
                select.selectByVisibleText(value);
                break;
            case "index":
                select.selectByIndex(Integer.parseInt(value));
                break;
            case "visibleText":
                select.selectByVisibleText(value);
                break;
            case "selectByvalue":
                select.selectByValue(value);
                handleLogging("select",value,screenshot);
        }


    }

}
