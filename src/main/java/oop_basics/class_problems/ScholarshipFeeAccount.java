package oop_basics.class_problems;

public class ScholarshipFeeAccount extends FeeAccount {
    private final double scholarshipPercent;

    public ScholarshipFeeAccount(String regNo, double totalFee, double amountPaid, double scholarshipPercent) {
        super(regNo, totalFee, amountPaid);
        if (scholarmentOutOfRange(scholarshipPercent)) {
            throw new IllegalArgumentException("Scholarship percentage must be between 0 and 100");
        }
        this.scholarshipPercent = scholarshipPercent;
    }

    public ScholarshipFeeAccount(String regNo, double totalFee, double scholarshipPercent) {
        this(regNo, totalFee, 0.0, scholarshipPercent);
    }

    private static boolean scholarmentOutOfRange(double percent) {
        return percent < 0 || percent > 100;
    }

    public double effectiveDue() {
        return getDue() * (1.0 - (scholarshipPercent / 100.0));
    }

    public double getScholarshipPercent() {
        return scholarshipPercent;
    }
}
