package com.pavlov.onliner.page_object;

import com.pavlov.onliner.WebDriverDiscovery;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    private static final int DRIVER_EXPLICIT_WAIT_TIME = 20;

    private final WebDriver driver;
    private WebDriverDiscovery webDriverDiscovery;

    public BasePage() {
        this.webDriverDiscovery = new WebDriverDiscovery();
        this.driver = webDriverDiscovery.getWebDriver();
    }

    public WebElement waitForElementVisible(By by) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_EXPLICIT_WAIT_TIME));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public Actions getActionsElement() {
        return new Actions(driver);
    }

    public void navigate(String url) {
        driver.get(url);
    }

    public void closeBrowser() {
        driver.quit();
    }
}
