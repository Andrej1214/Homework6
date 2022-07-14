package com.pavlov.onliner.navigation;

import com.pavlov.onliner.page_object.HomePage;

public class OnlinerNavigation {
    public static void navigateToOnlinerHomePage() {
        new HomePage().navigate("https://www.onliner.by");
    }
}
