package week_6.assigment_problems;

import java.util.Arrays;

public class EventTicket {
    private static int counter = 1000;
    private final String ticketId;
    private final String attendeeId;
    private final double basePrice;
    private double balanceDue;

    private final double[] lateFeeHistory;
    private int lateFeeCount;

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException("attendeeId must be at least 4 characters long and not blank");
        }
        this.ticketId = "TCK-" + (++counter);
        this.attendeeId = attendeeId.trim();
        this.basePrice = basePrice;
        this.balanceDue = basePrice;
        this.lateFeeHistory = new double[10];
        this.lateFeeCount = 0;
    }

    public EventTicket(double basePrice) {
        this("ATT-" + (counter + 1), basePrice);
    }

    public void pay(double amount) {
        this.balanceDue -= amount;
    }

    public void pay(double amount, String mode) {
        pay(amount);
        System.out.println("Paying via " + mode);
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public String getAttendeeId() {
        return attendeeId;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public String getTicketId() {
        return ticketId;
    }

    protected void applyLateFee(double amount) {
        if (amount > 0 && lateFeeCount < 10) {
            this.balanceDue += amount;
            this.lateFeeHistory[lateFeeCount++] = amount;
        }
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }

    public static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5) return false;
        if (code.charAt(0) != 'F') return false;
        if (!Character.isDigit(code.charAt(1))) return false;
        if (!Character.isDigit(code.charAt(2))) return false;
        if (!Character.isDigit(code.charAt(3))) return false;
        if (!Character.isUpperCase(code.charAt(4))) return false;
        return true;
    }

    public static int getTicketsIssued() {
        return counter - 1000;
    }

    public String printTicket() {
        return "Standard Event Ticket | Balance Due: " + balanceDue;
    }
}
