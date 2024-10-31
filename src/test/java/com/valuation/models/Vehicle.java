package com.valuation.models;


import com.opencsv.bean.CsvBindByName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Vehicle {

    @CsvBindByName(column="variant_reg")
    String registration;

    @CsvBindByName(column="make")
    String make;

    @CsvBindByName(column="model")
    String model;

    @CsvBindByName(column="year")
    String year;

    @Override
    public String toString() {
        return "Vehicle{" +
                "Registration: '" + registration + '\'' +
                ", Make: '" + make + '\'' +
                ", Model: '" + model + '\'' +
                ", Year: '" + year + '\'' +
                '}';
    }
}
