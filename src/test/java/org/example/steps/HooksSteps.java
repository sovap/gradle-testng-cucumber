package org.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.example.utils.ConfigUtils;
import org.example.utils.DriverUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class HooksSteps {

    @BeforeAll
    public static void beforeAll(){

        // get property 'environment'
        String env = System.getProperty("environment");

        // default env configuration file
        String envFilePath = "src/test/resources/environments/Test.yaml";

        if(!env.isEmpty()){
            envFilePath = "src/test/resources/environments/" + env + ".yaml";
        }

        // store the environment configuration to the public map
        ConfigUtils.readEnvConfiguration(envFilePath);
    }

    @Before
    public void InitializeTest(Scenario scenario) {
    }

    @After
    public void TearDownTest(Scenario scenario) {

        // get the driver from the hash map
        WebDriver driver = DriverUtil.driverCache.get(Thread.currentThread().getId());

        // delete the record from the hash map
        DriverUtil.driverCache.remove(Thread.currentThread().getId(), driver);

        if (scenario.isFailed()) {
            // Take a screenshot and embed it in the report.
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png","screenshot");
        }

        // close the browser
        DriverUtil.quitDriver(driver);
    }
}
