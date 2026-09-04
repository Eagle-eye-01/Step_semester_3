package oop_advanced.assigment_problems;

class PremiumAccount extends DeliveryAccount {
    public PremiumAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }
}

public class DeliveryAccount {
    private String studentId;
    private double orderValue;
    
    // One-time class-level state
    static final double BASE_FEE;
    static {
        BASE_FEE = 0.0;
    }

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(int delayMinutes) {
        // Simpler flat-rate version since we don't have direct access to problem 4's instance easily without coupling
        return (delayMinutes > 0) ? (orderValue * 0.01) : 0;
    }
    
    public double getOrderValue() {
        return orderValue;
    }

    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        int processed = 0;
        int nullSkipped = 0;
        int premiumCount = 0;
        int regularCount = 0;
        double grandTotal = 0;

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            DeliveryAccount acc = accounts[i];
            
            // Assume the amounts array might not strictly match the object's orderValue, 
            // but we process it anyway based on the requirement.
            // Requirement asks to compute surge fee for every account actually processed
            double surgeFee;
            if (acc instanceof PremiumAccount) {
                premiumCount++;
                // Premium accounts might get a 50% discount on surge fees
                surgeFee = acc.calculateSurgeFee(delayMinutesArray[i]) * 0.5;
            } else {
                regularCount++;
                surgeFee = acc.calculateSurgeFee(delayMinutesArray[i]);
            }
            
            grandTotal += surgeFee;
            processed++;
        }

        System.out.printf("%d processed | %d null skipped | %d premium | %d regular | grand total surge fees = %.2f\n",
                processed, nullSkipped, premiumCount, regularCount, grandTotal);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new PremiumAccount("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };
        double[] amounts = {500, 400, 300};
        int[] delays = {10, 5, 0};
        
        processBatch(accounts, amounts, delays);
    }
}
