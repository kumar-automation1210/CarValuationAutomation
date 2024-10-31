package com.valuation;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = { "@ui" },
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        features = "src/test/resources/features",
        glue = {"com.valuation.stepdefinitions",
                "com.valuation.hooks"
        })
public class TestRunner {
}
