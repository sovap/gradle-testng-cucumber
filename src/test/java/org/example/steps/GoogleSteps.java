package org.example.steps;

import io.cucumber.java.en.When;
import org.example.pages.GooglePage;
import org.example.utils.ConfigUtils;
import org.example.utils.DriverUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class GoogleSteps {

    private WebDriver driver;

    @When("User will open google search")
    public void user_will_open_google_search() {

        // init driver
        driver = DriverUtil.initDriver();
        DriverUtil.driverCache.putIfAbsent(Thread.currentThread().getId(), driver);

        // get to the app url
        String appUrl = (String) ConfigUtils.envConfig.get("app.url");
        driver.get(appUrl);

    }

    @When("User will search for {string}")
    public void user_will_search_for(String searchString) {

        // init first page object
        GooglePage googlePage = new GooglePage(driver);

        // make some actions
        googlePage.setInputSearch(searchString);
        googlePage.clickBtnSearch();

    }

    @When("User will sleep for {int} seconds")
    public void user_will_sleep(int seconds) {

        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @When("Scenario will fail")
    public void scenario_will_fail() {
        Assert.fail();
    }

}