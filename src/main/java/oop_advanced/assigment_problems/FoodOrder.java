package oop_advanced.assigment_problems;

public class FoodOrder {
    private String studentName;
    private String dishName;
    private boolean delivered = false;

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        if (dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Dish name cannot be empty");
        }
        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
    }

    public void markDelivered() {
        if (delivered) {
            System.out.println("Warning: Order for " + studentName + " (" + dishName + ") was already marked delivered!");
        } else {
            delivered = true;
            System.out.println("Order for " + studentName + " (" + dishName + ") marked as delivered.");
        }
    }

    public static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        for (String[] rawOrder : rawOrders) {
            if (rawOrder.length != 2) {
                rejected++;
                continue;
            }
            try {
                new FoodOrder(rawOrder[0], rawOrder[1]);
                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        
        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {
        String[][] batch = {
                {"Ravi", "Paneer Butter Masala"},
                {"", "Chole Bhature"},
                {"Meera", " "}
        };
        processBatch(batch);
    }
}
