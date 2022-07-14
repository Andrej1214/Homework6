package com.pavlov.onliner.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class CatalogPage extends BasePage {

    private static final String CATALOG_CLASSIFIER_MAIN_LINK_XPATH_PATTERN =
            "//*[contains(@class,'classifier__item') and contains(text(),'%s')]";
    private static final String COMPUTERS_AND_NETWORKS_LINK_XPATH_PATTERN =
            "//*[contains(@class,'classifier__item') and contains(text(),'Компьютеры и')]";
    private static final String LINK_XPATH_PATTERN =
            "//*[contains(@class,'classifier__item') and contains(text(),'Компьютеры и')]";
    private static final String MENU_XPATH_PATTERN =
            "//div[@data-id='2']//div[@class='catalog-navigation-list__aside-list']";
    private static final String MENU_ITEM_XPATH_PATTERN =
            "//div[@data-id='2']//div[@class='catalog-navigation-list__aside-list']//div[contains(text(),'%s')]";
    private static final String MENU_ITEM_ACCESSORIES_XPATH_PATTERN =
      "//div[@data-id='2']//div[@class='catalog-navigation-list__aside-list']//div[contains(text(),'Комплектующие')]";
    private static final String ALL_ELEMENTS_MENU_ITEM_ACCESSORIES_XPATH_PATTERN =
            "//div[contains(@class,'__aside-item_active')]" +
                    "//div[contains(@class,'list__dropdown')]//div[contains(@class,'dropdown-list')]/child::*";
    private static final String TITLES_OF_FIELDS_OF_MENU_ITEM_XPATH_PATTERN =
            "//div[contains(@class,'__aside-item_active')]" +
                    "//a[contains(@class,'list__dropdown-item')]//span[contains(@class,'dropdown-title')]";
    private static final String GOODS_QUANTITY_AND_MIN_PRICE_OF_FIELDS_OF_MENU_ITEM_XPATH_PATTERN =
            "//div[contains(@class,'__aside-item_active')]" +
                    "//a[contains(@class,'list__dropdown-item')]//span[contains(@class,'dropdown-description')]";


    public boolean checkOfDisplayedElementInMainCatalogLink(String section) {
        return waitForElementVisible(By.xpath(String.format(CATALOG_CLASSIFIER_MAIN_LINK_XPATH_PATTERN, section)))
                .isDisplayed();
    }

    public CatalogPage selectComputersAndNetworks() {
        //здесь кликаю на "Компьютеры и сети"
        waitForElementVisible(By.xpath(COMPUTERS_AND_NETWORKS_LINK_XPATH_PATTERN))
                .click();
        return this;
    }

    public CatalogPage selectMenuItem(String itemOfMenu) {
        waitForElementVisible(By.xpath(String.format(LINK_XPATH_PATTERN, itemOfMenu)))
                .click();
        return this;
    }

    public CatalogPage selectMenuItemAccessories() {
        //здесь кликаю по меню "Комплектующие"
        waitForElementVisible(By.xpath(MENU_ITEM_ACCESSORIES_XPATH_PATTERN))
                .click();
        return this;
    }

    public boolean checkOfDisplayedMenuComputersAndNetworks() {
        Actions actions = getActionsElement();
        actions.moveToElement(waitForElementVisible(By.xpath(MENU_XPATH_PATTERN)))
                .build().perform();
        return waitForElementVisible(By.xpath(MENU_XPATH_PATTERN))
                .isDisplayed();
    }

    public boolean checkOfDisplayedMenuItemComputersAndNetworks(String itemOfMenu) {
        Actions actions = getActionsElement();
        actions.moveToElement(waitForElementVisible(By.xpath(String
                        .format(MENU_ITEM_XPATH_PATTERN, itemOfMenu))))
                .build().perform();
        return waitForElementVisible(By.xpath(String
                .format(MENU_ITEM_XPATH_PATTERN, itemOfMenu)))
                .isDisplayed();
    }

    public List<String> getTitlesOfOfMenuItemAccessories() {
        List<WebElement> webElements = selectMenuItemAccessories().findElements(By
                .xpath(TITLES_OF_FIELDS_OF_MENU_ITEM_XPATH_PATTERN));
        List<String> listOfTitles = new ArrayList<>();
        for (WebElement veb : webElements) {
            listOfTitles.add(veb.getText());
        }
        return listOfTitles;
    }

    public List<String> getListOfGoodsQuantityAccessoriesItems() {
        List<WebElement> webElements = selectMenuItemAccessories().findElements(By
                .xpath(GOODS_QUANTITY_AND_MIN_PRICE_OF_FIELDS_OF_MENU_ITEM_XPATH_PATTERN));
        List<String> goodsQuantity = new ArrayList<>();
        for (WebElement veb : webElements) {
            String[] masStr = veb.getText().split("\n");
            goodsQuantity.add(masStr[0]);
        }
        return goodsQuantity;
    }

    public List<String> getListOfMinPriceAccessoriesItems() {
        List<WebElement> webElements = selectMenuItemAccessories().findElements(By
                .xpath(GOODS_QUANTITY_AND_MIN_PRICE_OF_FIELDS_OF_MENU_ITEM_XPATH_PATTERN));
        List<String> minPrice = new ArrayList<>();
        for (WebElement veb : webElements) {
            String[] masStr = veb.getText().split("\n");
            minPrice.add(masStr[1]);
        }
        return minPrice;
    }
}
