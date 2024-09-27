package com.webAutomation.pageEvents;

import com.webAutomation.base.BaseTest;
import com.webAutomation.pageElements.SearchResultPageElements;
import com.webAutomation.utils.FetchElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class SearchResultPageEvents {

    FetchElement fetchElement = new FetchElement();

    public void getProductDetailsWithProductTitle(){

    }

    public Map<String,Double> getProductDetailsWithPrice() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(BaseTest.driver, 5);
        wait.until(ExpectedConditions.visibilityOf(fetchElement.getWebElement("CSS", SearchResultPageElements.productLocatorByCSS)));
        scrollToBottom(BaseTest.driver);
        Map<String,Double> productTitleWithPrice = new HashMap<>();
            List<WebElement> products = BaseTest.driver.findElements(By.cssSelector("div.s-result-item:not([data-asin^='B0']):not(.s-sponsored-result)"));
        BaseTest.outputLogger("Total results loaded: " + products.size());
        for(WebElement product : products){
            try {
                List<WebElement> sponsoredLabel = product.findElements(By.xpath(".//*[contains(text(), 'Sponsored')]"));
                if (!(sponsoredLabel.size() > 0)) {
                    String productTitle = fetchElement.getWebElement(product, "XPATH", SearchResultPageElements.productTitleByXpath).getText();
                    String priceWhole = fetchElement.getWebElement(product,"XPATH",SearchResultPageElements.productPriceInWholeXpath).getText().replace(",","");
                    String priceFraction = fetchElement.getWebElement(product,"XPATH",SearchResultPageElements.productPriceInFractionXpath).getText();
                    double productPrice = Double.parseDouble(priceWhole + "." + priceFraction);
                    productTitleWithPrice.put(productTitle,productPrice);
                } else {
                    System.out.println("This product is sponsored: " + product.getText());
                }

            }
            catch (NoSuchElementException e){
                BaseTest.outputLogger("Product without a price or incomplete details, skipping.");

            }
        }
        return productTitleWithPrice;
    }

    public static void scrollToBottom(WebDriver driver) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long lastHeight = (long) js.executeScript("return document.body.scrollHeight");
        while (true) {
            // Scroll down to the bottom
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            // Wait for new content to load (adjust the wait time if necessary)
            Thread.sleep(2000);
            // Calculate new scroll height and check if new content has loaded
            long newHeight = (long) js.executeScript("return document.body.scrollHeight");
            if (newHeight == lastHeight) {
                break;  // No more content to load, exit the loop
            }
            lastHeight = newHeight;
        }
    }


}
