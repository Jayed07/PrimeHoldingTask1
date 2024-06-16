package model;

public class Car extends Vehicle {
    private final int safetyRating;

    public Car(String brand, String model, double value, int safetyRating) {
        super(brand, model, value);
        this.safetyRating = safetyRating;
    }

    public int getSafetyRating() {
        return safetyRating;
    }

    @Override
    public double calculateRentalCost(int days) {
        double dailyRate = days > 7 ? 15.0 : 20.0;
        return days * dailyRate;
    }

    @Override
    public double calculateInsuranceCost(int days, int unusedParameter) {
        double dailyRate = getValue() * 0.0001;
        if (safetyRating >= 4) {
            dailyRate *= 0.9;
        }
        return days * dailyRate;
    }
}
