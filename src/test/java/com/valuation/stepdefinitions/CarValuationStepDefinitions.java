package com.valuation.stepdefinitions;

import com.valuation.models.Vehicle;
import com.valuation.pageobjects.CarValuationHomePage;
import com.valuation.pageobjects.VehicleDetailsPage;
import com.valuation.utils.CsvUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class CarValuationStepDefinitions {

    private CarValuationHomePage carValuationHomePage;
    private VehicleDetailsPage vehicleDetailsPage;
    private List<String> inputVehicleRegNumbersList;
    private Map<String, Vehicle> outputVehicleDetailsList;

    // Pico container is used for dependency injection here. We can also use frameworks like spring IOC
    public CarValuationStepDefinitions(CarValuationHomePage carValuationHomePage, VehicleDetailsPage vehicleDetailsPage) {
        this.carValuationHomePage = carValuationHomePage;
        this.vehicleDetailsPage = vehicleDetailsPage;
    }

    @Given("^I have vehicle registration numbers in the file \"([^\"]*)\"$")
    public void vehicleRegistrationNumbersInTheFile(String filePath) throws Throwable {
        String fileContent = CsvUtil.readInputFileAsString(filePath);
        inputVehicleRegNumbersList = CsvUtil.getVehicleRegistrationNumbers(fileContent);
    }

    @When("^I have expected vehicle details in the file \"([^\"]*)\"$")
    public void expectedVehicleDetailsInTheFile(String outputFile) throws Throwable {
        outputVehicleDetailsList = CsvUtil.getExpectedVehicleDetailsFromCSV(outputFile);
    }

    @Then("^Expected vehicle details should match with Actual details on the website$")
    public void expectedVehicleDetailsShouldMatchWithActualDetailsOnTheWebsite() throws Throwable {
        verifyVehicleDetails(outputVehicleDetailsList);
    }

    @Then("^I navigate to webuyanycar website to valuate my vehicle$")
    public void navigateToWeBuyAnyCarWebSite() {
        carValuationHomePage.navigateToWeBuyAnyCarWebSite();
    }

    private void verifyVehicleDetails(Map<String, Vehicle> vehicleDetailsMap) throws Exception {

        String expVehicleNotFound = "Sorry, we couldn't find your car";
        for (String vehicleRegistrationNumber : inputVehicleRegNumbersList) {
            boolean isVehicleFound = false;
            Vehicle vehicleDetailsFromCsv = vehicleDetailsMap.get(vehicleRegistrationNumber);

            if (vehicleDetailsFromCsv != null) {
                carValuationHomePage.enterAndSearchRegistrationNumber(vehicleRegistrationNumber);

                // Asserting Vehicle details
                if (!expVehicleNotFound.equals(vehicleDetailsPage.getVehicleRegistrationNumber())) {
                    log.info("Vehicle details found for Registration Number " + vehicleRegistrationNumber);
                    isVehicleFound = true;
                    assertThat("verify vehicle registration", vehicleDetailsPage.getVehicleRegistrationNumber().equals(vehicleDetailsFromCsv.getRegistration()));
                    assertThat("verify vehicle make", vehicleDetailsPage.getVehicleMake().equals(vehicleDetailsFromCsv.getMake()));
                    assertThat("verify vehicle model", vehicleDetailsPage.getVehicleModel().equals(vehicleDetailsFromCsv.getModel()));
                    assertThat("verify vehicle year", vehicleDetailsPage.getVehicleYear().equals(vehicleDetailsFromCsv.getYear()));
                } else {
                    log.info("Vehicle details not found for Registration Number " + vehicleRegistrationNumber);
                    String actualVehicleNotFoundMsg = vehicleDetailsPage.getVehicleRegistrationNumber();
                    assertThat("verify car not found", expVehicleNotFound.equals(actualVehicleNotFoundMsg));
                }
            } else {
                log.error("Expected vehicle data not found for Registration Number :  " + vehicleRegistrationNumber);
            }

            if (vehicleRegistrationNumber != null && isVehicleFound) {
                //Navigate back to home page to check for next vehicle
                vehicleDetailsPage.navigateBackToHomePage();
            }
        }

    }
}
