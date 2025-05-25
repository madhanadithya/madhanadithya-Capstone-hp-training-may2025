package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

    WebDriver driver;
    WebDriverWait wait;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForTextInElement(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }
    
    public void waitForToastToAppear(WebElement element) {
        wait.until(driver -> element.isDisplayed() && !element.getText().trim().isEmpty());
    }
    
    public void waitForInputFieldToBeReady(WebElement inputField) {
        wait.until(ExpectedConditions.visibilityOf(inputField));
        wait.until(ExpectedConditions.elementToBeClickable(inputField));
    }

    
    public void waitForNumberOfWindowsToBe(int numberOfWindows) {
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.getWindowHandles().size() == numberOfWindows;
            }
        });
    }
	
}
