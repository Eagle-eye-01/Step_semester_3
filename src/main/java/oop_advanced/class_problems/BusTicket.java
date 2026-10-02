package oop_advanced.class_problems;

import java.util.HashSet;
import java.util.Set;

public class BusTicket {
    private final String passengerName;
    private final String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (!isValidName(passengerName)) {
            throw new IllegalArgumentException("Invalid passenger name: " + passengerName);
        }
        if (!isValidDestination(destination)) {
            throw new IllegalArgumentException("Invalid destination: " + destination);
        }
        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
        this.checkedIn = false;
    }

    private static boolean isValidName(String name) {
        if (name == null) return false;
        String trimmed = name.trim();
        if (trimmed.isEmpty()) return false;
        for (int i = 0; i < trimmed.length(); i++) {
            char c = trimmed.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidDestination(String dest) {
        if (dest == null) return false;
        return !dest.trim().isEmpty();
    }

    public void markCheckedIn() {
        if (checkedIn) {
            System.out.println("Warning: Ticket for " + passengerName + " is already checked in!");
            return;
        }
        this.checkedIn = true;
    }

    public static void processBatch(String[][] rawBookings) {
        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        Set<String> acceptedPairs = new HashSet<>();

        if (rawBookings != null) {
            for (String[] raw : rawBookings) {
                if (raw == null || raw.length != 2) {
                    rejected++;
                    continue;
                }
                String name = raw[0];
                String dest = raw[1];

                try {
                    BusTicket ticket = new BusTicket(name, dest);
                    String pairKey = ticket.passengerName.toLowerCase() + "|" + ticket.destination.toLowerCase();
                    if (acceptedPairs.contains(pairKey)) {
                        duplicates++;
                    } else {
                        acceptedPairs.add(pairKey);
                        valid++;
                    }
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected + " | Duplicates skipped: " + duplicates);
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getDestination() {
        return destination;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public static void main(String[] args) {
        String[][] batch = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };
        processBatch(batch);
    }
}
