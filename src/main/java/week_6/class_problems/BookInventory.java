package week_6.class_problems;

public class BookInventory {
    private int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {
        if (copiesTotal <= 0) {
            throw new IllegalArgumentException("copiesTotal must be greater than zero");
        }
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {
        if (this.copiesAvailable > 0) {
            this.copiesAvailable--;
        }
    }

    public void checkIn() {
        if (this.copiesAvailable < this.copiesTotal) {
            this.copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {
        return this.copiesAvailable;
    }
}
