package week_8.assigment_problems;

import java.util.*;

public class CampusCanteenSmartCard {

    public interface PricingPlan {
        double calculateDiscountedPrice(double originalPrice);
        String getPlanName();
    }

    public static class DayScholarPlan implements PricingPlan {
        @Override
        public double calculateDiscountedPrice(double originalPrice) {
            return originalPrice;
        }
        @Override
        public String getPlanName() { return "Day Scholar plan"; }
    }

    public static class HostellerPlan implements PricingPlan {
        @Override
        public double calculateDiscountedPrice(double originalPrice) {
            return originalPrice * 0.90;
        }
        @Override
        public String getPlanName() { return "Hosteller plan"; }
    }

    public static class StaffPlan implements PricingPlan {
        @Override
        public double calculateDiscountedPrice(double originalPrice) {
            return originalPrice * 0.80;
        }
        @Override
        public String getPlanName() { return "Staff plan"; }
    }

    public static class Transaction {
        private final double amount; // positive for top-up/refund, negative for purchase
        private final String description;
        private boolean refunded;

        public Transaction(double amount, String description) {
            this.amount = amount;
            this.description = description;
            this.refunded = false;
        }

        public double getAmount() { return amount; }
        public String getDescription() { return description; }
        public boolean isRefunded() { return refunded; }
        public void setRefunded(boolean refunded) { this.refunded = refunded; }
    }

    public static class SmartCard {
        private final String cardId;
        private final PricingPlan plan;
        private boolean blocked;
        private final List<Transaction> transactions = new ArrayList<>();

        public SmartCard(String cardId, PricingPlan plan) {
            this.cardId = cardId;
            this.plan = plan;
            this.blocked = false;
        }

        public double getBalance() {
            double sum = 0.0;
            for (Transaction tx : transactions) {
                sum += tx.getAmount();
            }
            return sum;
        }

        public boolean topUp(double amount) {
            if (blocked) {
                System.out.println("Top-up rejected: Card " + cardId + " is blocked.");
                return false;
            }
            if (amount < 100.0) {
                System.out.println("Top-up rejected: Minimum top-up amount is ₹100.00.");
                return false;
            }
            if (getBalance() + amount > 5000.0) {
                System.out.println("Top-up rejected: Exceeds maximum balance limit of ₹5,000.00.");
                return false;
            }
            transactions.add(new Transaction(amount, "Top-up"));
            System.out.printf(Locale.US, "%s topped up with ₹%.2f. Balance: ₹%.2f.%n",
                    cardId, amount, getBalance());
            return true;
        }

        public boolean purchase(String item, double price) {
            if (blocked) {
                System.out.println("Purchase rejected: Card " + cardId + " is blocked.");
                return false;
            }
            double discounted = plan.calculateDiscountedPrice(price);
            double currentBalance = getBalance();
            if (currentBalance < discounted) {
                System.out.printf(Locale.US, "Purchase failed: Insufficient balance (required ₹%.2f, available ₹%.2f).%n",
                        discounted, currentBalance);
                return false;
            }
            transactions.add(new Transaction(-discounted, item));
            System.out.printf(Locale.US, "%s purchased for ₹%.2f. Balance: ₹%.2f.%n",
                    item, discounted, getBalance());
            return true;
        }

        public boolean refund(String item) {
            if (blocked) {
                System.out.println("Refund rejected: Card is blocked.");
                return false;
            }
            Transaction originalPurchase = null;
            for (Transaction tx : transactions) {
                if (tx.getDescription().equalsIgnoreCase(item) && tx.getAmount() < 0) {
                    originalPurchase = tx;
                    break;
                }
            }

            if (originalPurchase == null) {
                System.out.println("Refund rejected: No purchase found for " + item + ".");
                return false;
            }
            if (originalPurchase.isRefunded()) {
                System.out.println("Refund rejected: " + item + " has already been refunded.");
                return false;
            }

            double refundAmount = Math.abs(originalPurchase.getAmount());
            originalPurchase.setRefunded(true);
            transactions.add(new Transaction(refundAmount, "Refund: " + item));
            System.out.printf(Locale.US, "Refund of ₹%.2f for %s processed. Balance: ₹%.2f.%n",
                    refundAmount, item, getBalance());
            return true;
        }

        public void printMiniStatement() {
            StringBuilder sb = new StringBuilder();
            sb.append("Mini-statement for ").append(cardId).append(": ");
            for (int i = 0; i < transactions.size(); i++) {
                Transaction tx = transactions.get(i);
                if (tx.getAmount() >= 0) {
                    sb.append(String.format(Locale.US, "+%.2f", tx.getAmount()));
                } else {
                    sb.append(String.format(Locale.US, "%.2f", tx.getAmount()));
                }
                if (i < transactions.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(String.format(Locale.US, " = ₹%.2f.", getBalance()));
            System.out.println(sb.toString());
        }

        public String getCardId() { return cardId; }
        public boolean isBlocked() { return blocked; }
        public void setBlocked(boolean blocked) { this.blocked = blocked; }
    }

    public static void main(String[] args) {
        SmartCard card = new SmartCard("C-2045", new HostellerPlan());
        card.topUp(500.0);
        card.purchase("Veg Thali", 120.0);
        card.purchase("Cold Coffee", 60.0);
        card.purchase("Items", 400.0);
        card.refund("Veg Thali");
        card.refund("Veg Thali");
        card.printMiniStatement();
    }
}
