package com.pavlov.onliner;

import com.pavlov.onliner.navigation.OnlinerNavigation;
import com.pavlov.onliner.page_object.CatalogPage;
import com.pavlov.onliner.page_object.HomePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class CheckListOfSectionPointsInPageComputersAndNetworksTest {
    private final HomePage homePage = new HomePage();
    private final CatalogPage catalogPage = homePage.clickOnCatalogNavigationLink().selectComputersAndNetworks();

    @BeforeAll
    public static void navigateToOnliner() {
        OnlinerNavigation.navigateToOnlinerHomePage();
    }

    @Test
    public void testCheckExistingMenu() {
        assertThat(catalogPage.checkOfDisplayedMenuComputersAndNetworks())
                .as("Main menu is not displayed on ComputersAndNetworksPage")
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings =
            {"Ноутбуки, компьютеры, мониторы", "Комплектующие", "Хранение данных", "Сетевое оборудование"})
    public void testExistingElementsInMenuOFComputersAndNetworks(String section) {
        assertThat(catalogPage.checkOfDisplayedMenuItemComputersAndNetworks(section))
                .as("Element " + section + " doesn't exist in this link").isTrue();
    }

    @AfterAll
    public static void closeBrowser() {
        new CatalogPage().closeBrowser();
    }
}
