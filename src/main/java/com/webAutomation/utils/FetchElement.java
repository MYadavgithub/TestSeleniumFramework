package com.webAutomation.utils;

import com.webAutomation.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FetchElement {

    public WebElement getWebElement(String identifierType, String identifierValue) {

        switch (identifierType) {
            case "ID":
                return BaseTest.driver.findElement(By.id(identifierValue));
            case "CSS":
                return BaseTest.driver.findElement(By.cssSelector(identifierValue));
            case "TAGNAME":
                return BaseTest.driver.findElement(By.tagName(identifierValue));
            case "XPATH":
                return BaseTest.driver.findElement(By.xpath(identifierValue));
            default:
                return null;
        }
    }

    public WebElement getWebElement(WebElement scope, String identifierType, String identifierValue) {

        switch (identifierType) {
            case "ID":
                return scope.findElement(By.id(identifierValue));
            case "CSS":
                return scope.findElement(By.cssSelector(identifierValue));
            case "TAGNAME":
                return scope.findElement(By.tagName(identifierValue));
            case "XPATH":
                return scope.findElement(By.xpath(identifierValue));
            default:
                return null;
        }
    }

    public List<WebElement> getWebElements(String identifierType, String identifierValue) {

        switch (identifierType) {
            case "ID":
                return BaseTest.driver.findElements(By.id(identifierValue));
            case "CSS":
                return BaseTest.driver.findElements(By.cssSelector(identifierValue));
            case "TAGNAME":
                return BaseTest.driver.findElements(By.tagName(identifierValue));
            case "XPATH":
                return BaseTest.driver.findElements(By.xpath(identifierValue));
            default:
                return null;
        }
    }

    public List<WebElement> getWebElements(WebElement scope, String identifierType, String identifierValue) {
        switch (identifierType) {
            case "ID":
                return scope.findElements(By.id(identifierValue));
            case "CSS":
                return scope.findElements(By.cssSelector(identifierValue));
            case "TAGNAME":
                return scope.findElements(By.tagName(identifierValue));
            case "XPATH":
                return scope.findElements(By.xpath(identifierValue));
            default:
                return null;
        }
    }
}


