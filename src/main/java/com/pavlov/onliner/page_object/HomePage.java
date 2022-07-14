package com.pavlov.onliner.page_object;

import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private static final String MAIN_NAVIGATION_LINK_PATTERN =
            "//span [@class='b-main-navigation__text' and text()='%s']";

    public HomePage clickOnMainLinkOfHomePage(String link) {
        waitForElementVisible(By.xpath(String.format(MAIN_NAVIGATION_LINK_PATTERN, link))).click();
        return this;
    }

    public CatalogPage clickOnCatalogNavigationLink() {
        clickOnMainLinkOfHomePage("Каталог");
        return new CatalogPage();
    }
}
