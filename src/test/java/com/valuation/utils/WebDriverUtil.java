package com.valuation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtil {

    private static WebDriver driver;

    private static final int WAIT_TIME = 10;

    private WebDriverUtil() {}

    public static WebDriver getWebDriverInstance() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions acceptCertificates = new ChromeOptions();
            acceptCertificates.setAcceptInsecureCerts(true);
            driver = new ChromeDriver(acceptCertificates);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void waitForElementTobePresent(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

}
