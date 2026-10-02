package oop_advanced.class_problems;

import java.util.Arrays;

public class FareSplitter {
    private final String tripId;
    private final double totalFare;
    private final int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0) {
            throw new IllegalArgumentException("Total fare cannot be negative");
        }
        if (passengerCount <= 0) {
            throw new IllegalArgumentException("Passenger count must be positive");
        }
        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {
        double[] shares = new double[passengerCount];
        if (totalFare == 0.0) {
            Arrays.fill(shares, 0.0);
            return shares;
        }

        long totalPaise = Math.round(totalFare * 100.0);
        long basePaise = totalPaise / passengerCount;
        long remainder = totalPaise % passengerCount;

        for (int i = 0; i < passengerCount; i++) {
            shares[i] = basePaise / 100.0;
        }

        // Remainder paisa absorbed by the last share(s)
        for (int i = 0; i < remainder; i++) {
            int targetIndex = passengerCount - 1 - i;
            shares[targetIndex] = Math.round((shares[targetIndex] + 0.01) * 100.0) / 100.0;
        }

        return shares;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public String getTripId() {
        return tripId;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FareSplitter("TRIP001", 100000, 3).fareBreakdown()));
        System.out.println(Arrays.toString(new FareSplitter("TRIP003").fareBreakdown()));
    }
}
