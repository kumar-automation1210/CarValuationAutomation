package com.valuation.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import com.valuation.models.Vehicle;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvUtil {

    public static List<String> getVehicleRegistrationNumbers(String textFromInputFile) throws IOException {
        Pattern regex = Pattern.compile("[A-Z][A-Z]\\s?([0][2-9]|[1-9][0-9])\\s?[A-Z]{3}");
        Matcher regexMatcher = regex.matcher(textFromInputFile);
        List<String> registrationNumbers = new ArrayList<>();
        while (regexMatcher.find()) {
            if (regexMatcher.group(0) != null) {
                registrationNumbers.add(regexMatcher.group(0));
            }
        }
        return registrationNumbers;

    }

    public static String readInputFileAsString(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }


    public static Map<String, Vehicle> getExpectedVehicleDetailsFromCSV(String outputCsvFile) throws FileNotFoundException {
        FileReader fileReader =  new FileReader(outputCsvFile);
        List<Vehicle> vehicleDetailsFromCsv = new CsvToBeanBuilder(fileReader)
                .withType(Vehicle.class).build().parse();
        return vehicleDetailsFromCsv.stream().collect(Collectors.toMap(Vehicle::getRegistration, vehicle -> vehicle));
    }
}
