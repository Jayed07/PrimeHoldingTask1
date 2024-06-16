package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract  class Vehicle {
    private String brand;
    private String model;
    private double value;
    private LocalDate rentFrom;
    private LocalDate rentTo;

    public Vehicle(String brand, String model, double value) {
        this.brand = brand;
        this.model = model;
        this.value = value;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getValue() {
        return value;
    }

    public LocalDate getRentFrom() {
        return rentFrom;
    }

    public LocalDate getRentTo() {
        return rentTo;
    }

    public void setRentalPeriod(LocalDate rentFrom, LocalDate rentTo) {
        this.rentFrom = rentFrom;
        this.rentTo = rentTo;
    }

    public long getRentalDays() {
        return ChronoUnit.DAYS.between(rentFrom, rentTo);
    }

    public abstract double calculateRentalCost(int days);
    public abstract double calculateInsuranceCost(int days, int additionalParameter);
}
