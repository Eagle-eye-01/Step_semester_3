package oop_advanced.class_problems;

public class BusTicketAccount {
    private final String bookingId;
    private final double ticketFare;

    static {
        System.out.println("Nightly Fleet Reconciliation Initialized");
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public final double calculatePenalty(int minutesLate) {
        if (minutesLate <= 0) return 0.0;
        BoardingPenaltyCalculator calc = new BoardingPenaltyCalculator(1.0);
        return calc.calculatePenalty(this.ticketFare, minutesLate);
    }

    public void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        double penalty = account.calculatePenalty(minutesLate);
        System.out.printf("Processed account %s | Amount: %.2f | Penalty: %.2f%n",
                account.bookingId, amount, penalty);
    }

    public static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        int processed = 0;
        int nullSkipped = 0;
        int sleeperCount = 0;
        int regularCount = 0;
        double grandTotalPenalties = 0.0;

        if (accounts != null) {
            for (int i = 0; i < accounts.length; i++) {
                if (accounts[i] == null) {
                    nullSkipped++;
                    continue;
                }

                BusTicketAccount acc = accounts[i];
                int minutesLate = (minutesLateArray != null && i < minutesLateArray.length) ? minutesLateArray[i] : 0;
                double penalty = acc.calculatePenalty(minutesLate);

                if (acc instanceof Sleeper) {
                    sleeperCount++;
                } else {
                    regularCount++;
                }

                grandTotalPenalties += penalty;
                processed++;
            }
        }

        System.out.printf("%d processed | %d null skipped | %d sleeper | %d regular | grand total penalties = Rs %.2f%n",
                processed, nullSkipped, sleeperCount, regularCount, grandTotalPenalties);
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getTicketFare() {
        return ticketFare;
    }
}
