# Car Valuation Check Automation
### Description
We are doing below While validating the vehicle details in webuyanycar website against the output file
- when we find the Vehicle information then we are validating the details Manufacturer, Model, Year and First Registered
- When we don't find the vehicle in the website then we are just validating the error message returned by the website instead of failing the test
### Technology Stack
- Java 11
- Cucumber
- Selenium Webdriver
- Pico Container
- Maven
- OpenCSV
- AssertJ
- Slf4j


### IDE

- IntelliJ IDEA


### Browser Support

- Google Chrome


### Run Tests

- `mvn clean test`


### Reports
- Generated reports are under target/cucumber-reports/cucumber.html/index.html

### Screen Recordings
- Test execution is recorded and can be found under screen_recordings folder.
