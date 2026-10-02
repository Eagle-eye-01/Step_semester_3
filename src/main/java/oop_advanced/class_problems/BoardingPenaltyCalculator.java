package oop_advanced.class_problems;

public final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException("Values cannot be negative");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        double feeRate = 0.0;
        if (minutesLate >= 1) {
            int t1 = Math.min(minutesLate, 5);
            feeRate += t1 * 0.005;
        }
        if (minutesLate >= 6) {
            int t2 = Math.min(minutesLate - 5, 10);
            feeRate += t2 * 0.01;
        }
        if (minutesLate >= 16) {
            int t3 = minutesLate - 15;
            feeRate += t3 * 0.02;
        }

        double calculatedPenalty = ticketFare * feeRate;
        double floorPenalty = ticketFare * (minimumPenaltyPercent / 100.0);

        return Math.max(calculatedPenalty, floorPenalty);
    }

    public static void main(String[] args) {
        BoardingPenaltyCalculator calc = new BoardingPenaltyCalculator(1.0);
        System.out.println("Rs " + calc.calculatePenalty(1000, 0));
        System.out.println("Rs " + calc.calculatePenalty(1000, 1));
        System.out.println("Rs " + calc.calculatePenalty(1000, 16));
    }
}
