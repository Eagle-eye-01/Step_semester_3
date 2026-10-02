package week_8.class_problems;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class HotelBookingSystem {

    public enum RoomCategory {
        STANDARD(150.0), DELUXE(200.0), SUITE(350.0);

        private final double ratePerNight;
        RoomCategory(double rate) { this.ratePerNight = rate; }
        public double getRatePerNight() { return ratePerNight; }
    }

    public static class Room {
        private final String roomNumber;
        private final RoomCategory category;
        private final List<Reservation> reservations = new ArrayList<>();

        public Room(String roomNumber, RoomCategory category) {
            this.roomNumber = roomNumber;
            this.category = category;
        }

        public boolean isAvailable(LocalDate checkIn, LocalDate checkOut) {
            for (Reservation res : reservations) {
                if (res.isActive()) {
                    if (checkIn.isBefore(res.getCheckOut()) && checkOut.isAfter(res.getCheckIn())) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void addReservation(Reservation res) {
            reservations.add(res);
        }

        public String getDisplayName() {
            String catName = category.name().charAt(0) + category.name().substring(1).toLowerCase();
            return catName + " Room " + roomNumber;
        }

        public RoomCategory getCategory() { return category; }
        public String getRoomNumber() { return roomNumber; }
    }

    public static class Customer {
        private final String name;
        public Customer(String name) { this.name = name; }
        public String getName() { return name; }
    }

    public static class Reservation {
        private final Room room;
        private final Customer customer;
        private final LocalDate checkIn;
        private final LocalDate checkOut;
        private final double totalPrice;
        private boolean active;

        public Reservation(Room room, Customer customer, LocalDate checkIn, LocalDate checkOut) {
            this.room = room;
            this.customer = customer;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
            this.totalPrice = nights * room.getCategory().getRatePerNight();
            this.active = true;
        }

        public boolean cancel(LocalDate currentDate) {
            if (!active) {
                System.out.println("Reservation already cancelled.");
                return false;
            }
            if (currentDate.isBefore(checkIn)) {
                this.active = false;
                System.out.println("Reservation for " + room.getDisplayName() + " cancelled successfully.");
                return true;
            } else {
                System.out.println("Cancellation failed: Past cancellation deadline.");
                return false;
            }
        }

        public Room getRoom() { return room; }
        public Customer getCustomer() { return customer; }
        public LocalDate getCheckIn() { return checkIn; }
        public LocalDate getCheckOut() { return checkOut; }
        public double getTotalPrice() { return totalPrice; }
        public boolean isActive() { return active; }
    }

    public static class BookingManager {
        public Reservation bookRoom(Room room, Customer customer, String fromDate, String toDate) {
            LocalDate checkIn = LocalDate.parse(fromDate);
            LocalDate checkOut = LocalDate.parse(toDate);

            if (!room.isAvailable(checkIn, checkOut)) {
                System.out.println("Booking failed: " + room.getDisplayName() + " is not available for " + fromDate + " to " + toDate + ".");
                return null;
            }

            Reservation res = new Reservation(room, customer, checkIn, checkOut);
            room.addReservation(res);
            System.out.printf(Locale.US, "%s booked from %s to %s. Total price: $%.2f (example value).%n",
                    room.getDisplayName(), fromDate, toDate, res.getTotalPrice());
            return res;
        }
    }

    public static void main(String[] args) {
        Room deluxe101 = new Room("101", RoomCategory.DELUXE);
        Room standard205 = new Room("205", RoomCategory.STANDARD);
        Customer customer = new Customer("Customer");
        BookingManager manager = new BookingManager();

        Reservation r1 = manager.bookRoom(deluxe101, customer, "2024-12-01", "2024-12-05");
        manager.bookRoom(standard205, customer, "2024-12-03", "2024-12-07");
        manager.bookRoom(deluxe101, customer, "2024-12-03", "2024-12-07");

        if (r1 != null) {
            r1.cancel(LocalDate.parse("2024-11-20"));
        }
    }
}
