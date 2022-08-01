package com.pavlov.onliner;

import com.pavlov.onliner.navigation.OnlinerNavigation;
import com.pavlov.onliner.page_object.CatalogPage;
import com.pavlov.onliner.page_object.HomePage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

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
     @ValueSource(strings =  {"Электроника", "Компьютеры и", "Бытовая техника",
             "Стройка и", "Дом и", "Авто и", "Красота и", "Детям и", "Работа и"})
    // "Дом и&nbsp;сад","Авто и&nbsp;мото","Красота и&nbsp;спорт","Детям и&nbsp;мамам","Работа и&nbsp;офис"})
    //ввиду некорректной работы метода contains на неразрывный пробел '&nbsp;' использую следующий массив:
    public void testExistingElementsInMainLinkOfCatalog(String value) {
        List<String> stringList = List.of("Электроника", "Компьютеры и", "Бытовая техника",
                "Стройка и", "Дом и", "Авто и", "Красота и", "Детям и", "Работа и");
        boolean checkExistingElement = catalogPage.checkOfDisplayedElementInMainCatalogLink(value);
        assertThat(checkExistingElement).isTrue();
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
