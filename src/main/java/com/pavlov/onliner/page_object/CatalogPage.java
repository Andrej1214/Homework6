package com.pavlov.onliner.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.String.format;

public class CatalogPage extends BasePage {
    private static final int GROUPSNUMBER_OF_GOOD_QUANTITY = 0;
    private static final int GROUPSNUMBER_OF_MIN_PRICE = 1;

    private static final String CATALOG_CLASSIFIER_MAIN_LINK_XPATH_PATTERN =
            "//*[contains(@class,'classifier__item') and contains(text(),'%s')]";
    private static final String COMPUTERS_AND_NETWORKS_LINK =
            "//*[contains(@class,'classifier__item') and contains(text(),'Компьютеры и')]";
    private static final String COMPUTERS_AND_NETWORKS_MENU =
            "//div[@data-id='2']//div[@class='catalog-navigation-list__aside-list']";
    private static final String COMPUTERS_AND_NETWORKS_MENU_ITEM_XPATH_PATTERN =
            "//div[@data-id='2']//div[@class='catalog-navigation-list__aside-list']//div[contains(text(),'%s')]";
    private static final String COMPUTERS_AND_NETWORKS_MENU_ITEM_ACCESSORIES =
      "//div[@data-id='2']//div[@class='catalog-navigation-list__aside-list']//div[contains(text(),'Комплектующие')]";
    private static final String ALL_ELEMENTS_MENU_ITEM_ACCESSORIES =
            "//div[contains(@class,'__aside-item_active')]" +
                    "//div[contains(@class,'list__dropdown')]//div[contains(@class,'dropdown-list')]/child::*";
    private static final String COMPUTERS_AND_NETWORKS_TITLES_OF_FIELDS_OF_MENU_ITEM =
            "//div[contains(@class,'__aside-item_active')]" +
                    "//a[contains(@class,'list__dropdown-item')]//span[contains(@class,'dropdown-title')]";
    private static final String COMPUTERS_AND_NETWORKS_GOODS_QUANTITY_AND_MIN_PRICE_FIELDS_OF_MENU_ITEM =
            "//div[contains(@class,'__aside-item_active')]" +
                    "//a[contains(@class,'list__dropdown-item')]//span[contains(@class,'dropdown-description')]";

    public boolean checkOfDisplayedElementInMainCatalogLink(String section) {
        try{
            return waitForElementVisible(By.xpath(format(CATALOG_CLASSIFIER_MAIN_LINK_XPATH_PATTERN, section)))
                    .isDisplayed();
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public CatalogPage selectComputersAndNetworks() {
        //I click here on "Компьютеры и сети"
        waitForElementVisible(By.xpath(COMPUTERS_AND_NETWORKS_LINK))
                .click();
        return this;
    }

    public CatalogPage selectMenuItem(String itemOfMenu) {
        waitForElementVisible(By.xpath(format(COMPUTERS_AND_NETWORKS_LINK, itemOfMenu)))
                .click();
        return this;
    }

    public CatalogPage selectMenuItemAccessories() {
        //I click here on menu "Комплектующие"
        waitForElementVisible(By.xpath(COMPUTERS_AND_NETWORKS_MENU_ITEM_ACCESSORIES))
                .click();
        return this;
    }

    public boolean checkOfDisplayedMenuComputersAndNetworks() {
        return checkExistingOfElement(By.xpath(COMPUTERS_AND_NETWORKS_MENU));
    }

    public boolean checkOfDisplayedMenuItemComputersAndNetworks(String itemOfMenu) {
        return checkExistingOfElement(By.xpath(format(COMPUTERS_AND_NETWORKS_MENU_ITEM_XPATH_PATTERN, itemOfMenu)));
    }

    public List<String> getTitlesOfOfMenuItemAccessories() {
        List<WebElement> webElements = selectMenuItemAccessories().findElements(By
                .xpath(COMPUTERS_AND_NETWORKS_TITLES_OF_FIELDS_OF_MENU_ITEM));
        List<String> listOfTitles = new ArrayList<>();
        for (WebElement veb : webElements) {
            listOfTitles.add(veb.getText());
        }
        return listOfTitles;
    }

    public List<String> getItemsOfGoodsQuantityCategoryAccessoriesMenuComputerAndNetworks() {
        return getTextValueOfElementsSubmenuComputersAndNetworks(GROUPSNUMBER_OF_GOOD_QUANTITY);
    }

    public List<String> getItemsOfMinPriceCategoryAccessoriesMenuComputerAndNetworks() {
        return getTextValueOfElementsSubmenuComputersAndNetworks(GROUPSNUMBER_OF_MIN_PRICE);
    }
    private List<String> getTextValueOfElementsSubmenuComputersAndNetworks(int groupsNumberOfArray){
        List<WebElement> webElements = selectMenuItemAccessories().findElements(By
                .xpath(COMPUTERS_AND_NETWORKS_GOODS_QUANTITY_AND_MIN_PRICE_FIELDS_OF_MENU_ITEM));
        List<String> stringList = new ArrayList<>();
        for (WebElement veb : webElements) {
            String[] masStr = veb.getText().split("\n");
            stringList.add(masStr[groupsNumberOfArray]);
        }
        return stringList;
    }
}
