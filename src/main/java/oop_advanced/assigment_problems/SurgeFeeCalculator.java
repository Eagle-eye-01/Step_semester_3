package oop_advanced.assigment_problems;

public final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Values cannot be negative");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double feeRate = 0.0;
        
        if (delayMinutes >= 1) {
            int t1 = Math.min(delayMinutes, 5);
            feeRate += t1 * 0.005;
        }
        if (delayMinutes >= 6) {
            int t2 = Math.min(delayMinutes - 5, 10);
            feeRate += t2 * 0.01;
        }
        if (delayMinutes >= 16) {
            int t3 = delayMinutes - 15;
            feeRate += t3 * 0.02;
        }

        double calculatedFee = orderValue * feeRate;
        double minimumFee = orderValue * (minimumSurgePercent / 100.0);

        return Math.max(calculatedFee, minimumFee);
    }

    public static void main(String[] args) {
        SurgeFeeCalculator calc = new SurgeFeeCalculator(1.0); // 1% floor
        System.out.println("Rs " + calc.calculateSurgeFee(500, 0));
        System.out.println("Rs " + calc.calculateSurgeFee(500, 1));
        System.out.println("Rs " + calc.calculateSurgeFee(500, 16));
    }
}
