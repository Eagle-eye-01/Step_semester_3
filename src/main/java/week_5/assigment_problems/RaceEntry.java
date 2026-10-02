package week_5.assigment_problems;

public class RaceEntry {
    private final String bibNumber;
    private final double entryFee;
    private double balanceDue;
    
    private double[] lateFeeHistory;
    private int lateFeeCount;

    private static int bibCounter = 0;
    private final String entryCode;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("bibNumber must be at least 4 characters long and not blank.");
        }
        this.bibNumber = bibNumber.trim();
        this.entryFee = entryFee;
        this.balanceDue = entryFee;
        
        this.lateFeeHistory = new double[10];
        this.lateFeeCount = 0;

        bibCounter++;
        this.entryCode = "ENTRY-" + bibCounter;
    }

    public void pay(double amount) {
        this.balanceDue -= amount;
    }

    public void pay(double amount, String mode) {
        pay(amount);
        System.out.println("Paying via " + mode);
    }

    public double getBalanceDue() {
        return this.balanceDue;
    }
    
    public String getBibNumber() {
        return this.bibNumber;
    }
    
    public double getEntryFee() {
        return this.entryFee;
    }

    protected void applyLateFee(double amount) {
        if (amount > 0 && lateFeeCount < 10) {
            this.balanceDue += amount;
            this.lateFeeHistory[lateFeeCount++] = amount;
        }
    }

    public double[] getLateFeeHistory() {
        double[] history = new double[lateFeeCount];
        System.arraycopy(this.lateFeeHistory, 0, history, 0, lateFeeCount);
        return history;
    }

    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) return false;
        if (code.charAt(0) != 'M') return false;
        if (!Character.isDigit(code.charAt(1))) return false;
        if (!Character.isDigit(code.charAt(2))) return false;
        if (!Character.isDigit(code.charAt(3))) return false;
        if (!Character.isUpperCase(code.charAt(4))) return false;
        return true;
    }

    public static int getBibCounter() {
        return bibCounter;
    }

    public String announce() {
        return "Race Entry | Bib: " + bibNumber + " | Balance: " + balanceDue;
    }
}
