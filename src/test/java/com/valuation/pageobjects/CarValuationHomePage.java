package com.valuation.pageobjects;

import com.valuation.utils.TestUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.valuation.utils.WebDriverUtil.getWebDriverInstance;
import static com.valuation.utils.WebDriverUtil.waitForElementTobePresent;

public class CarValuationHomePage {

    @FindBy(id = "vehicleReg")
    private WebElement enterRegNumber;

    @FindBy(id = "Mileage")
    private WebElement mileage;

    @FindBy(id = "btn-go")
    private WebElement getMyCarEvaluation;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookies;

    public CarValuationHomePage() {
        PageFactory.initElements(getWebDriverInstance(), this);
    }

    public void enterAndSearchRegistrationNumber(String registrationNumber) {
        enterRegistrationNumber(registrationNumber);
        enterMileage();
        clickOnFreeCarCheckButton();
    }

    private void enterRegistrationNumber(String regNumber) {
        waitForElementTobePresent(enterRegNumber);
        enterRegNumber.clear();
        enterRegNumber.sendKeys(regNumber);
    }

    private void enterMileage() {
        mileage.clear();
        mileage.sendKeys(TestUtil.getRandomMileage());
    }

    private void clickOnFreeCarCheckButton() {
        waitForElementTobePresent(getMyCarEvaluation);
        getMyCarEvaluation.click();
    }

    public void navigateToWeBuyAnyCarWebSite(){
        getWebDriverInstance().navigate().to("https://webuyanycar.co.uk/");
        acceptCookies();
    }

    public void acceptCookies() {
        waitForElementTobePresent(acceptCookies);
        acceptCookies.click();
    }
}
