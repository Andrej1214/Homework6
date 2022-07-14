package com.pavlov.onliner;

import com.pavlov.onliner.navigation.OnlinerNavigation;
import com.pavlov.onliner.page_object.CatalogPage;
import com.pavlov.onliner.page_object.HomePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class CheckTitleAndExtraInformationAboutElementsOfAccessoriesTest {
    private static final int itemQuantityInAccessoriesSection = 14;

    private final HomePage homePage = new HomePage();
    private final CatalogPage catalogPage = homePage.clickOnCatalogNavigationLink().selectComputersAndNetworks();

    @BeforeAll
    public static void navigateToOnliner() {
        OnlinerNavigation.navigateToOnlinerHomePage();
    }

    @Test
    public void testNoEmptyValuesInTitleInItemAccessories() {
        List<String> list = catalogPage.getTitlesOfOfMenuItemAccessories();
        assertThat(list.size()).isEqualTo(itemQuantityInAccessoriesSection);
        assertThat(list).allMatch(s -> !(s.isEmpty() || s.equals(null)));
    }

    @Test
    public void testNoEmptyValuesInGoodsQuantityInItemAccessories() {
        List<String> list = catalogPage.getListOfGoodsQuantityAccessoriesItems();
        assertThat(list.size()).isEqualTo(itemQuantityInAccessoriesSection);
        assertThat(list).allMatch(s -> !(s.isEmpty() || s.equals(null)));
    }

    @Test
    public void testNoEmptyValuesInMinPriceInItemAccessories() {
        List<String> list = catalogPage.getListOfMinPriceAccessoriesItems();
        assertThat(list.size()).isEqualTo(itemQuantityInAccessoriesSection);
        assertThat(list).allMatch(s -> !(s.isEmpty() || s.equals(null)));
    }

    @AfterAll
    public static void closeBrowser() {
        new CatalogPage().closeBrowser();
    }
}
