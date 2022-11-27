package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

public final class DriverUtil {

	public static Map<Long, WebDriver> driverCache = new ConcurrentHashMap<>();
	private static final int browserWidth = (int) ConfigUtils.envConfig.get("browser.width");
	private static final int browserHeight = (int) ConfigUtils.envConfig.get("browser.height");

	
	public static WebDriver initDriver() {

		// if needed update the chromedriver
		WebDriverManager.chromedriver().setup();

		// Chrome browser capabilities (Chrome options)
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.setCapability("takesScreenshot", true);
		options.setCapability("browserName", "chrome");
		options.setCapability("acceptSslCerts", true);

		// disable cookies
		var prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.cookies", 2);
		options.setExperimentalOption("prefs", prefs);

		WebDriver driver = null;
		try {
			// disable logging in console
			java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

			driver = new ChromeDriver(options);

			// Selenium synchronization timeouts
			driver.manage().timeouts(). scriptTimeout(Duration.ofSeconds(30));
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
	        
	        // set resolution
	        driver.manage().window().setSize(new Dimension(browserWidth, browserHeight));

		} catch (Exception e ) {
			Assert.fail("[ERROR] Driver initialisation error: " + e);
		}
		return driver;
    }
		
	public static void quitDriver(WebDriver driver) {
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
				Assert.fail("[ERROR] An error occurred while trying to close the driver: " + e);
			}
		}
	}
}
