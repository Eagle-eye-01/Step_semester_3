package week_8.class_problems;

import java.util.*;

public class FoodOrderSystem {

    public interface IPaymentMethod {
        boolean processPayment(double amount);
        String getMethodName();
    }

    public static class CreditCardPayment implements IPaymentMethod {
        private final boolean simulateSuccess;
        public CreditCardPayment(boolean simulateSuccess) { this.simulateSuccess = simulateSuccess; }
        public CreditCardPayment() { this(true); }

        @Override
        public boolean processPayment(double amount) {
            return simulateSuccess;
        }

        @Override
        public String getMethodName() { return "Credit Card"; }
    }

    public static class DigitalWalletPayment implements IPaymentMethod {
        private final boolean simulateSuccess;
        public DigitalWalletPayment(boolean simulateSuccess) { this.simulateSuccess = simulateSuccess; }
        public DigitalWalletPayment() { this(false); }

        @Override
        public boolean processPayment(double amount) {
            return simulateSuccess;
        }

        @Override
        public String getMethodName() { return "Digital Wallet"; }
    }

    public static class LineItem {
        private final String itemName;
        private final int quantity;

        public LineItem(String itemName, int quantity) {
            this.itemName = itemName;
            this.quantity = quantity;
        }

        public String getItemName() { return itemName; }
        public int getQuantity() { return quantity; }
    }

    public static class Order {
        private static int orderCounter = 122;
        private int orderId;
        private final List<LineItem> items = new ArrayList<>();
        private String status = "Created";

        public Order() {
        }

        public void addItem(String name, int qty) {
            items.add(new LineItem(name, qty));
        }

        public boolean placeAndPay(IPaymentMethod paymentMethod) {
            if (items.isEmpty()) {
                System.out.println("Cannot place order: Order must contain at least one item.");
                return false;
            }
            this.orderId = ++orderCounter;

            boolean success = paymentMethod.processPayment(100.0);
            if (success) {
                this.status = "Paid";
                System.out.println("Order placed successfully. Payment via " + paymentMethod.getMethodName() +
                        " successful. Order status: Paid. Notification: Order #" + orderId + " placed and paid.");
                return true;
            } else {
                this.status = "Pending Payment";
                System.out.println("Order placed. Payment via " + paymentMethod.getMethodName() +
                        " failed. Order status: Pending Payment. Notification: Order #" + orderId + " placed, awaiting payment.");
                return false;
            }
        }

        public int getOrderId() { return orderId; }
        public String getStatus() { return status; }
        public List<LineItem> getItems() { return items; }
    }

    public static void main(String[] args) {
        Order o1 = new Order();
        o1.addItem("Pizza", 2);
        o1.addItem("Soda", 1);
        System.out.println("Order created. Added Pizza (Qty 2), Soda (Qty 1).");

        Order emptyOrder = new Order();
        emptyOrder.placeAndPay(new CreditCardPayment(true));

        o1.placeAndPay(new CreditCardPayment(true));

        Order o2 = new Order();
        o2.addItem("Burger", 1);
        o2.placeAndPay(new DigitalWalletPayment(false));
    }
}
