package com.valuation.pageobjects;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.valuation.utils.WebDriverUtil.*;
import static com.valuation.utils.WebDriverUtil.waitForElementTobePresent;

@Slf4j
public class VehicleDetailsPage {

    @FindBy(xpath = "(//div[@id='vehicleImage'])[2]//div[contains(@class,'details-vrm')]")
    private WebElement registrationNumber;

    @FindBy(xpath = "(//div[contains(@class, 'd-lg-block vehicle-details')])[2]//div[contains(text(),'Manufacturer:')]/following-sibling::div")
    private WebElement make;

    @FindBy(xpath = "(//div[contains(@class, 'd-lg-block vehicle-details')])[2]//div[contains(text(),'Model:')]/following-sibling::div")
    private WebElement model;

    @FindBy(xpath = "(//div[contains(@class, 'd-lg-block vehicle-details')])[2]//div[contains(text(),'Year:')]/following-sibling::div")
    private WebElement year;

    @FindBy(id = "btn-back")
    private WebElement back;

    @FindBy(css = "div h1")
    private WebElement vehicleNotFound;

    public VehicleDetailsPage() {
        PageFactory.initElements(getWebDriverInstance(), this);
    }

    public String getVehicleRegistrationNumber() {
        return getText(registrationNumber);
    }

    public String getVehicleModel() {
        return getText(model);
    }

    public String getVehicleYear() {
        return getText(year);
    }

    public String getVehicleMake() {
        return getText(make);
    }

    private String getText(WebElement webElement) {
        try {
            waitForElementTobePresent(webElement);
            return webElement.getText();
        } catch (NoSuchElementException | TimeoutException e) {
            waitForElementTobePresent(vehicleNotFound);
            return vehicleNotFound.getText();
        }
    }

    public void navigateBackToHomePage() {
        back.click();
    }
}
