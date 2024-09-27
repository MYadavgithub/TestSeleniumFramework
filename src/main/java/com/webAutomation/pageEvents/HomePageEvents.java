package com.webAutomation.pageEvents;

import com.webAutomation.base.BaseTest;
import com.webAutomation.pageElements.HomePageElements;
import com.webAutomation.utils.FetchElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class HomePageEvents {

    FetchElement fetchElement = new FetchElement();

    public void verifyHomePageLoaded(){
        Assert.assertTrue(fetchElement.getWebElement("ID", HomePageElements.searchBoxId).isDisplayed(),"search box is not loaded");
    }

    public void searchProduct(String productName){
        fetchElement.getWebElement("ID", HomePageElements.searchBoxId).sendKeys(productName);
        fetchElement.getWebElement("XPATH",HomePageElements.searchSubmitButtonXpath).click();
    }

    public void searchProductWithAutoSuggestion(String searchKeyword){
        fetchElement.getWebElement("ID", HomePageElements.searchBoxId).sendKeys(searchKeyword);
        WebDriverWait wait = new WebDriverWait(BaseTest.driver, 5);
        wait.until(ExpectedConditions.visibilityOf(fetchElement.getWebElement("XPATH","//div[contains(@class, 's-suggestion')]")));
        List<WebElement> suggestions = fetchElement.getWebElements("XPATH","//div[contains(@class, 's-suggestion')]");
        for(WebElement suggestion : suggestions){
            if (suggestion.getText().equalsIgnoreCase("iphone 15 128GB")){
                suggestion.click();
                break;
            }
        }
    }
}
