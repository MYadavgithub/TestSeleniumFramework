package com.webAutomation.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {
    int count =0;
    int retryCount = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        while (count<retryCount){
            count++;
            return true;
        }
        return false;
    }
}
