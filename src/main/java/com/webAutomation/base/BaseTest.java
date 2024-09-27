package com.webAutomation.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.webAutomation.utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


public class BaseTest {
    public static WebDriver driver;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;

    @BeforeTest
    public void initialisingHtmlReporting() throws IOException {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "AutomationReport.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Selenium Automation Report");
        htmlReporter.config().setReportName("Selenium Automation Results");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("SDET", "Automation QA");
        //delete all screenshots captured in previous run
//        File directory = new File(System.getProperty("user.dir")+File.separator+"Screenshots");
//        FileUtils.cleanDirectory(directory);
    }

    @BeforeMethod()
    @Parameters(value={"browserName"})
    public void beforeMethod(String browserName,Method testMethod){
        initialisation(browserName);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.switchTo().defaultContent();
        logger = extent.createTest(testMethod.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.SUCCESS) {
                String methodName = result.getMethod().getMethodName();
                String logText = " Test Case : " + methodName + " Passed";
                Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
                logger.log(Status.PASS, m);
            } else if (result.getStatus() == ITestResult.FAILURE) {
                String methodName = result.getMethod().getMethodName();
                String logText = " Test Case : " + methodName + " Failed";
                Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
                logger.log(Status.FAIL, m);
            } else if (result.getStatus() == ITestResult.SKIP) {
                String methodName = result.getMethod().getMethodName();
                String logText = " Test Case : " + methodName + " Skipped";
                Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
                logger.log(Status.SKIP, m);
            }
        } finally {
            if (driver != null) {
               // driver.quit();  // Ensures the driver quits even in case of an exception
            }
        }
    }


    @AfterTest
    public void AfterTest(){
        extent.flush();
    }

    public void initialisation(String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            // Automatically manage ChromeDriver
            WebDriverManager.chromedriver().setup();
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--disable-notifications");
            driver = new ChromeDriver(opt);
        }
        else if(browserName.equalsIgnoreCase("mozilla")){
            // Automatically manage GeckoDriver
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
    }

    public static void outputLogger(String logMessage){
        System.out.println(logMessage);
        Reporter.log(logMessage);
        logger.info(logMessage);
    }




}