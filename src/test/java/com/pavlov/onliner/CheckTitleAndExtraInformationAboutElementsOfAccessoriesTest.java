package com.pavlov.onliner;

import com.pavlov.onliner.navigation.OnlinerNavigation;
import com.pavlov.onliner.page_object.CatalogPage;
import com.pavlov.onliner.page_object.HomePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CheckTitleAndExtraInformationAboutElementsOfAccessoriesTest {
    private static final int itemQuantityInAccessoriesSection = 14;

    private final HomePage homePage = new HomePage();
    private CatalogPage catalogPage ;

    @BeforeAll
    public static void navigateToOnliner() {
        OnlinerNavigation.navigateToOnlinerHomePage();
    }

    @BeforeEach
    public void catalogInitialize(){
        catalogPage = homePage.clickOnCatalogNavigationLink().selectComputersAndNetworks();
    }

    @Test
    public void testNoEmptyValuesInTitleInItemAccessories() {
        List<String> list = catalogPage.getTitlesOfOfMenuItemAccessories();
        assertThat(list).hasSize(itemQuantityInAccessoriesSection);
        assertThat(list).allMatch(s -> !(s.isEmpty() || s == null));
    }

    @Test
    public void testNoEmptyValuesInGoodsQuantityInItemAccessories() {
        List<String> list = catalogPage.getItemsOfGoodsQuantityCategoryAccessoriesMenuComputerAndNetworks();
        assertThat(list).hasSize(itemQuantityInAccessoriesSection);
        assertThat(list).allMatch(s -> !(s.isEmpty() || s.equals(null)));
    }

    @Test
    public void testNoEmptyValuesInMinPriceInItemAccessories() {
        List<String> list = catalogPage.getItemsOfMinPriceCategoryAccessoriesMenuComputerAndNetworks();
        assertThat(list).hasSize(itemQuantityInAccessoriesSection);
        assertThat(list).allMatch(s -> !(s.isEmpty() || s.equals(null)));
    }

    @AfterAll
    public static void closeBrowser() {
        new CatalogPage().closeBrowser();
    }
}
