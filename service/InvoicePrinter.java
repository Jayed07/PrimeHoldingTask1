package service;

import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class InvoicePrinter {

    public void printInvoice(Customer customer, Vehicle vehicle, LocalDate actualReturnDate, int additionalParameter) {
        long reservedDays = vehicle.getRentalDays();
        long actualDays = ChronoUnit.DAYS.between(vehicle.getRentFrom(), actualReturnDate);
        actualDays = Math.min(actualDays, reservedDays);

        double rentalCostPerDay = vehicle.calculateRentalCost((int) actualDays) / actualDays;
        double insuranceCostPerDay = vehicle.calculateInsuranceCost(1, additionalParameter);

        double totalRentalCost = vehicle.calculateRentalCost((int) actualDays) +
                vehicle.calculateRentalCost((int) (reservedDays - actualDays)) / 2;
        double totalInsuranceCost = vehicle.calculateInsuranceCost((int) actualDays, additionalParameter);

        double totalCost = totalRentalCost + totalInsuranceCost;

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        StringBuilder invoice = new StringBuilder();
        invoice.append("XXXXXXXXXX\n")
                .append("Date: ").append(LocalDate.now().format(dateFormatter)).append("\n")
                .append("Customer name: ").append(customer.getName()).append("\n")
                .append("Rented Vehicle: ").append(vehicle.getBrand()).append(" ").append(vehicle.getModel()).append("\n")
                .append("\nReservation start date: ").append(vehicle.getRentFrom().format(dateFormatter)).append("\n")
                .append("Reservation end date: ").append(vehicle.getRentTo().format(dateFormatter)).append("\n")
                .append("Reserved rental days: ").append(reservedDays).append("\n")
                .append("\nActual return date: ").append(actualReturnDate.format(dateFormatter)).append("\n")
                .append("Actual rental days: ").append(actualDays).append("\n")
                .append("\nRental cost per day: $").append(String.format("%.2f", rentalCostPerDay)).append("\n");
                 appendInsuranceDetails(invoice, vehicle, additionalParameter);
                 invoice.append("Insurance per day: $").append(String.format("%.2f", insuranceCostPerDay)).append("\n");
                 appendEarlyReturnDiscount(invoice, vehicle, actualDays, reservedDays, additionalParameter);
                 invoice.append("\nTotal rent: $").append(String.format("%.2f", totalRentalCost))
                .append("\nTotal insurance: $").append(String.format("%.2f", totalInsuranceCost))
                .append("\nTotal: $").append(String.format("%.2f", totalCost))
                .append("\nXXXXXXXXXX");

        System.out.println(invoice);
    }

    private void appendInsuranceDetails(StringBuilder invoice, Vehicle vehicle, int additionalParameter) {
        if (vehicle instanceof Motorcycle && additionalParameter < 25) {
            double initialInsuranceCostPerDay = vehicle.getValue() * 0.0002;
            double insuranceAdditionPerDay = initialInsuranceCostPerDay * 0.2;
            invoice.append("Initial insurance per day: $").append(String.format("%.2f", initialInsuranceCostPerDay))
                    .append("\nInsurance addition per day: $").append(String.format("%.2f", insuranceAdditionPerDay)).append("\n");
        } else if (vehicle instanceof CargoVan && additionalParameter > 5) {
            double initialInsuranceCostPerDay = vehicle.getValue() * 0.0003;
            double insuranceDiscountPerDay = initialInsuranceCostPerDay * 0.15;
            invoice.append("Initial insurance per day: $").append(String.format("%.2f", initialInsuranceCostPerDay))
                    .append("\nInsurance discount per day: $").append(String.format("%.2f", insuranceDiscountPerDay)).append("\n");
        } else if (vehicle instanceof Car && ((Car) vehicle).getSafetyRating() >= 4) {
            double initialInsuranceCostPerDay = vehicle.getValue() * 0.0001;
            double insuranceDiscountPerDay = initialInsuranceCostPerDay * 0.1;
            invoice.append("Initial insurance per day: $").append(String.format("%.2f", initialInsuranceCostPerDay))
                    .append("\nInsurance discount per day: $").append(String.format("%.2f", insuranceDiscountPerDay)).append("\n");
        }
    }

    private void appendEarlyReturnDiscount(StringBuilder invoice, Vehicle vehicle, long actualDays, long reservedDays, int additionalParameter) {
        if (actualDays < reservedDays) {
            double earlyReturnDiscountForRent = vehicle.calculateRentalCost((int) (reservedDays - actualDays)) / 2;
            double earlyReturnDiscountForInsurance = vehicle.calculateInsuranceCost((int) (reservedDays - actualDays), additionalParameter);
            invoice.append("\nEarly return discount for rent: $").append(String.format("%.2f", earlyReturnDiscountForRent))
                    .append("\nEarly return discount for insurance: $").append(String.format("%.2f", earlyReturnDiscountForInsurance)).append("\n");
        }
    }
}
