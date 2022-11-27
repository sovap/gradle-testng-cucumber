package org.example.pages;

import org.example.utils.ConfigUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

	protected WebDriver driver;
    private final int explicitWait = (int) ConfigUtils.envConfig.get("wait.explicit");

    public BasePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

// ******************** WEB ELEMENT SYNCHRONIZATION METHODS *******************************************************************************

    public WebElement waitForElementToBeClickable(WebElement elementToWaitFor) {

    	// wait for all scripts on page to finish (javascript, jquery, angular)
    	waitWrapper();

    	// wait until element is clickable (visible and enabled)
        return new WebDriverWait(driver, Duration.ofSeconds(explicitWait)).until(ExpectedConditions.elementToBeClickable(elementToWaitFor));
    }

// ******************** TECHNOLOGY SYNCHRONIZATION METHODS ********************************************************************************

    public void waitWrapper() {

    	// wait for javascript to finish (this will also provide enough time for jquery and angular to be recognized in browser)
    	waitForJavaScript();

    	// in case jquery is present, wait for it to finish
    	waitForJquery();

    	// in case angular is present, wait for it to finish
    	waitForAngular();
    }

    public void waitForJavaScript() {

        try {
        	// create a new condition
        	ExpectedCondition<Boolean> condition = driver -> {
                assert driver != null;
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            };

           // wait until condition is true
        	new WebDriverWait(driver, Duration.ofSeconds(explicitWait)).until(condition);

        } catch (WebDriverException ignored) {
        }
    }

    public void waitForAngular() {

    	try {

        	// create a new condition
        	ExpectedCondition<Boolean> condition = driver -> {
                assert driver != null;
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1");
            };

           // wait until condition is true
        	new WebDriverWait(driver, Duration.ofSeconds(explicitWait)).until(condition);

        } catch (WebDriverException ignored) {
        }
    }

    public void waitForJquery() {

    	try {

    		// create a new condition
    		ExpectedCondition<Boolean> condition = driver -> {
                assert driver != null;
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
            };

            // wait until condition is true
        	new WebDriverWait(driver, Duration.ofSeconds(explicitWait)).until(condition);

        } catch (WebDriverException ignored) {
        }
    }

// ******************** COMMON METHODS ****************************************************************************************************

    public void waitThenClick(WebElement elementToClick) {

        waitForElementToBeClickable(elementToClick).click();
    }

    public void waitThenSendKeys(WebElement elementToSendKeys, String keysToSend) {

        waitForElementToBeClickable(elementToSendKeys).clear();
        waitForElementToBeClickable(elementToSendKeys).sendKeys(keysToSend);
    }

}