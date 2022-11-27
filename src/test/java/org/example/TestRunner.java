package org.example;

import io.cucumber.java.BeforeAll;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.example.utils.ConfigUtils;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.util.Map;

@CucumberOptions(
        features = {"src/test/resources/features/"}
        , glue = {"org.example.steps"}
        , plugin = {
                "pretty",
                "json:build/cucumber-reports/cucumber.json"
                }
)
public class TestRunner extends AbstractTestNGCucumberTests {
        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }



}

