package week_8.class_problems;

import java.util.*;

public class VehicleRentalSystem {

    public static abstract class Vehicle {
        private final String name;
        private boolean rented = false;

        public Vehicle(String name) {
            this.name = name;
        }

        public abstract double calculateCharge(int days);

        public String getName() { return name; }
        public boolean isRented() { return rented; }
        public void setRented(boolean rented) { this.rented = rented; }
    }

    public static class LuxuryCar extends Vehicle {
        public LuxuryCar(String name) {
            super(name);
        }

        @Override
        public double calculateCharge(int days) {
            return days * 100.00;
        }
    }

    public static class StandardCar extends Vehicle {
        public StandardCar(String name) {
            super(name);
        }

        @Override
        public double calculateCharge(int days) {
            return days * 50.00;
        }
    }

    public static class Rental {
        private final Vehicle vehicle;
        private final int days;
        private final double totalCharge;

        public Rental(Vehicle vehicle, int days) {
            this.vehicle = vehicle;
            this.days = days;
            this.totalCharge = vehicle.calculateCharge(days);
            vehicle.setRented(true);
        }

        public Vehicle getVehicle() { return vehicle; }
        public int getDays() { return days; }
        public double getTotalCharge() { return totalCharge; }
    }

    public static class RentalService {
        private final List<Rental> activeRentals = new ArrayList<>();

        public Rental rentVehicle(Vehicle vehicle, int days) {
            if (vehicle.isRented()) {
                System.out.println("Rental failed: " + vehicle.getName() + " is currently rented.");
                return null;
            }
            Rental rental = new Rental(vehicle, days);
            activeRentals.add(rental);
            System.out.printf(Locale.US, "%s rented for %d days. Total charge: $%.2f (example value).%n",
                    vehicle.getName(), days, rental.getTotalCharge());
            return rental;
        }

        public void returnVehicle(Vehicle vehicle) {
            Rental target = null;
            for (Rental r : activeRentals) {
                if (r.getVehicle().equals(vehicle)) {
                    target = r;
                    break;
                }
            }
            if (target != null) {
                activeRentals.remove(target);
                vehicle.setRented(false);
                System.out.println(vehicle.getName() + " returned. Now available.");
            } else {
                System.out.println("Return failed: No active rental for " + vehicle.getName());
            }
        }
    }

    public static void main(String[] args) {
        RentalService service = new RentalService();
        Vehicle luxA = new LuxuryCar("Luxury Car A");
        Vehicle stdB = new StandardCar("Standard Car B");

        service.rentVehicle(luxA, 3);
        service.rentVehicle(stdB, 5);
        service.returnVehicle(luxA);
    }
}
