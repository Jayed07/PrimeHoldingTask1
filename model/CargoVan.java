package model;

public class CargoVan extends Vehicle {

    public CargoVan(String brand, String model, double value) {
        super(brand, model, value);
    }

    @Override
    public double calculateRentalCost(int days) {
        return days * 40.0;
    }

    @Override
    public double calculateInsuranceCost(int days, int driverExperienceYears) {
        double dailyRate = getValue() * 0.0003;
        if (driverExperienceYears > 5) {
            dailyRate *= 0.85;
        }
        return days * dailyRate;
    }
}
