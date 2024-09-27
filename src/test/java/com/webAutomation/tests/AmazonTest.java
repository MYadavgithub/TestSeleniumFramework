package com.webAutomation.tests;

import com.webAutomation.base.BaseTest;
import com.webAutomation.pageElements.HomePageElements;
import com.webAutomation.pageEvents.HomePageEvents;
import com.webAutomation.pageEvents.SearchResultPageEvents;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

public class AmazonTest extends BaseTest {

    HomePageEvents homePageEvents;
    SearchResultPageEvents searchResultPageEvents;
    AmazonTest(){
      homePageEvents = new HomePageEvents();
      searchResultPageEvents = new SearchResultPageEvents();
    }

    //@Test
    public void searchProductTest(){
            try {
                homePageEvents.verifyHomePageLoaded();
                homePageEvents.searchProduct("iphone 15");
            } catch (Exception e) {
                throw e;
            }
    }

    //@Test
    public void searchProductFromAutoSuggestions(){
        try {
            homePageEvents.verifyHomePageLoaded();
            homePageEvents.searchProductWithAutoSuggestion("iphone");
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void findHighestPriceProductFromSearchResults(){
        try {
            homePageEvents.verifyHomePageLoaded();
            homePageEvents.searchProduct("samsung phones");
            Map<String,Double> productDetails = searchResultPageEvents.getProductDetailsWithPrice();
            for (Map.Entry<String, Double> entry : productDetails.entrySet()) {
                BaseTest.outputLogger("Product title: " + entry.getKey() + ", Product Price: " + entry.getValue());
            }
        } catch (Exception e) {
            BaseTest.outputLogger(String.valueOf(e));
        }
    }
}
