import model.*;
import service.InvoicePrinter;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer customer1 = new Customer("John Doe");
        Vehicle vehicle1 = new Car("Mitsubishi", "Mirage", 15000, 3);
        LocalDate rentalStart1 = LocalDate.of(2024, 6, 3);
        LocalDate rentalEnd1 = LocalDate.of(2024, 6, 13);
        vehicle1.setRentalPeriod(rentalStart1, rentalEnd1);
        LocalDate actualReturn1 = LocalDate.of(2024, 6, 13);
        InvoicePrinter invoicePrinter = new InvoicePrinter();
        invoicePrinter.printInvoice(customer1, vehicle1, actualReturn1, 0);

        Customer customer2 = new Customer("Mary Johnson");
        Vehicle vehicle2 = new Motorcycle("Triumph", "Tiger Sport 660", 10000);
        LocalDate rentalStart2 = LocalDate.of(2024, 6, 3);
        LocalDate rentalEnd2 = LocalDate.of(2024, 6, 13);
        vehicle2.setRentalPeriod(rentalStart2, rentalEnd2);
        LocalDate actualReturn2 = LocalDate.of(2024, 6, 13);
        invoicePrinter.printInvoice(customer2, vehicle2, actualReturn2, 20);

        Customer customer3 = new Customer("John Markson");
        Vehicle vehicle3 = new CargoVan("Citroen", "Jumper", 20000);
        LocalDate rentalStart3 = LocalDate.of(2024, 6, 3);
        LocalDate rentalEnd3 = LocalDate.of(2024, 6, 18);
        vehicle3.setRentalPeriod(rentalStart3, rentalEnd3);
        LocalDate actualReturn3 = LocalDate.of(2024, 6, 13);
        invoicePrinter.printInvoice(customer3, vehicle3, actualReturn3, 8);


    }
}