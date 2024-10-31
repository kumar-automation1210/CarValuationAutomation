@ui
Feature: Vehicle valuation verification

  Scenario Outline: Validate vehicle valuation from webuyanycar website

    Given I have vehicle registration numbers in the file "<input_file>"
    And I have expected vehicle details in the file "<output_file>"
    When I navigate to webuyanycar website to valuate my vehicle
    Then Expected vehicle details should match with Actual details on the website
    Examples:
      | input_file                                | output_file                                |
      | src/test/resources/testdata/car_input.txt | src/test/resources/testdata/car_output.txt |