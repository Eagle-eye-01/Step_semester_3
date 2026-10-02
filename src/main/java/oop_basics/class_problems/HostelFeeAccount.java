package oop_basics.class_problems;

public class HostelFeeAccount extends FeeAccount {

    public HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    public HostelFeeAccount(String regNo, double totalFee) {
        super(regNo, totalFee);
    }

    public void payInTwoInstallments(double amount) {
        if (amount <= 0) {
            System.out.println("Payment rejected: installment amount must be positive");
            return;
        }
        double installment = amount / 2.0;
        pay(installment);
        pay(installment);
    }
}
