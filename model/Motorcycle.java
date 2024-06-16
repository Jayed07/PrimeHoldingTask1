package model;

public class Motorcycle extends Vehicle{

    public Motorcycle(String brand, String model, double value) {
        super(brand, model, value);
    }

    @Override
    public double calculateRentalCost(int days) {
        double dailyRate = days > 7 ? 10.0 : 15.0;
        return days * dailyRate;
    }

    @Override
    public double calculateInsuranceCost(int days, int driverAge) {
        double dailyRate = getValue() * 0.0002;
        if (driverAge < 25) {
            dailyRate *= 1.2;
        }
        return days * dailyRate;
    }
}
