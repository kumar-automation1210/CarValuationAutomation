package com.valuation.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;

import static com.valuation.utils.WebDriverUtil.getWebDriverInstance;

public class Hooks {

    @After("@ui")
    public void afterScenario(Scenario scenario) {
        getWebDriverInstance().quit();
    }
}
