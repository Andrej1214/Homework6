package com.pavlov.onliner;

import com.pavlov.onliner.navigation.OnlinerNavigation;
import com.pavlov.onliner.page_object.CatalogPage;
import com.pavlov.onliner.page_object.HomePage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckingItemsOnCatalogPageTest {
    private HomePage homePage = new HomePage();
    private final CatalogPage catalogPage = homePage.clickOnCatalogNavigationLink();

    @BeforeAll
    public static void navigateToOnliner() {
        OnlinerNavigation.navigateToOnlinerHomePage();
    }

    @ParameterizedTest
    // @ValueSource(strings =  {"Электроника", "Компьютеры и&nbsp;сети","Бытовая техника","Стройка и&nbsp;ремонт",
    // "Дом и&nbsp;сад","Авто и&nbsp;мото","Красота и&nbsp;спорт","Детям и&nbsp;мамам","Работа и&nbsp;офис"})
    //ввиду некорректной работы метода contains на неразрывный пробел '&nbsp;' использую следующий массив:
    @ValueSource(strings = {"Электроника", "Компьютеры и", "Бытовая техника",
            "Стройка и", "Дом и", "Авто и", "Красота и", "Детям и", "Работа и"})
    public void testExistingElementsInMainLinkOfCatalog(String section) {
        boolean checkExistingElement = catalogPage.checkOfDisplayedElementInMainCatalogLink(section);
        assertThat(checkExistingElement).as("Element " + section + " doesn't exist in this link").isTrue();
    }

    @Test
    public void testExistingElementInMainLinkOfCatalog() {
        Throwable throwable = catchThrowable(
                () -> {
                    homePage.clickOnCatalogNavigationLink()
                            .checkOfDisplayedElementInMainCatalogLink("Еда");
                }
        );
        assertThat(throwable).hasNoSuppressedExceptions();
    }

    @AfterAll
    @DisabledIf("@Suite")
    public static void closeBrowser() {
        new CatalogPage().closeBrowser();
    }
}
