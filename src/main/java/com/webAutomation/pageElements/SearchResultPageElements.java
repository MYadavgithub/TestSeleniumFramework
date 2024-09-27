package com.webAutomation.pageElements;

public interface SearchResultPageElements {


    String productLocatorByCSS = "div.s-result-item";
    String productTitleByXpath = ".//span[contains(@class, 'a-size-medium')]";
    String productPriceInWholeXpath = ".//span[@class='a-price-whole']";
    String productPriceInFractionXpath = ".//span[@class='a-price-fraction']";
}
