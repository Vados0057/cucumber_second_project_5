package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {
    public static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception ignored) {
        }

    }
}