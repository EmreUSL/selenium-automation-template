package utils;


import core.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Objects;
import java.util.function.Supplier;

public class UIActions {

    private static final int DEFAULT_RETRY_COUNT = 3;

    private static <T> T executeWithRetry(Supplier<T> action, String actionName) {
        RuntimeException lastException = null;

        for (int attempt = 1 ; attempt <= DEFAULT_RETRY_COUNT ; attempt++) {
            try {
                return action.get();
            } catch (RuntimeException e) {
                lastException = e;
            }
        }

        throw new RuntimeException(
                actionName + " failed after " + DEFAULT_RETRY_COUNT + " attempts",
                lastException
        );
    }

    public static void click(By locator) {
        try {
            executeWithRetry(() -> {
                WaitActions.waitForClickable(locator).click();
                return null;
            }, "Click");
        } catch (RuntimeException e) {
            jsClickInternal(locator);
        }
    }

    public static void type(By locator, String text) {
        executeWithRetry(() -> {
            WebElement element = WaitActions.waitForVisible(locator);
            element.clear();
            element.sendKeys(text);
            return null;
        }, "Type");
    }

    public static String getText(By locator) {
        return executeWithRetry(
                () -> WaitActions.waitForVisible(locator).getText(),
                "Get Text"
        );
    }

    public static boolean isDisplayed(By locator) {
        try {
            return executeWithRetry(
                    () -> DriverManager.getDriver()
                            .findElement(locator)
                            .isDisplayed(),
                    "Is Displayed"
            );
        } catch (Exception e) {
            return false;
        }
    }

    public static void scrollToElement(By locator) {
        executeWithRetry(() -> {
            WebElement element = WaitActions.waitForVisible(locator);
            ((JavascriptExecutor) DriverManager.getDriver())
                    .executeScript("arguments[0].scrollIntoView(true);", element);
            return null;
        }, "Scroll To Element");
    }

    public static void hover(By locator) {
        executeWithRetry(() -> {
            new Actions(DriverManager.getDriver())
                    .moveToElement(WaitActions.waitForVisible(locator))
                    .perform();
            return null;
        }, "Hover");
    }

    public static void switchToFrame(By locator) {
        executeWithRetry(() -> {
            DriverManager.getDriver()
                    .switchTo()
                    .frame(WaitActions.waitForVisible(locator));
            return null;
        }, "Switch To Frame");
    }

    public static void switchToWindow(String windowTitle) {
        executeWithRetry(() -> {
            for (String handle : DriverManager.getDriver().getWindowHandles()) {
                if (Objects.equals(
                        DriverManager.getDriver()
                                .switchTo()
                                .window(handle)
                                .getTitle(),
                        windowTitle)) {
                    return null;
                }
            }
            throw new RuntimeException("Window not found: " + windowTitle);
        }, "Switch To Window");
    }

    private static void jsClickInternal(By locator) {
        WebElement element = WaitActions.waitForVisible(locator);
        ((JavascriptExecutor) DriverManager.getDriver())
                .executeScript("arguments[0].click();", element);
    }
}

