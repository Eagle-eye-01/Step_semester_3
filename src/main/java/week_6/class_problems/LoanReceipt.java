package week_6.class_problems;

import java.util.Arrays;

public class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    static {
        // Shared state for the nightly processor
        System.out.println("Nightly Circulation Ledger Initialized");
    }

    public LoanReceipt(String memberId, String[] bookIds) {
        if (bookIds == null) {
            throw new IllegalArgumentException("bookIds cannot be null");
        }
        for (String bookId : bookIds) {
            if (bookId == null || !bookId.matches("BK-\\d{3}")) {
                throw new IllegalArgumentException("Invalid book ID format: " + bookId);
            }
        }
        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }
    
    // Internal constructor for wither method
    protected LoanReceipt(String memberId, String[] bookIds, boolean skipValidation) {
        this.memberId = memberId;
        this.bookIds = bookIds;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(this.bookIds, this.bookIds.length);
    }

    public String getMemberId() {
        return memberId;
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (newId == null || !newId.matches("BK-\\d{3}")) {
            throw new IllegalArgumentException("Invalid book ID format: " + newId);
        }
        if (index < 0 || index >= this.bookIds.length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        String[] newBookIds = Arrays.copyOf(this.bookIds, this.bookIds.length);
        newBookIds[index] = newId;
        
        if (this instanceof ReferenceOnlyLoanReceipt) {
            return new ReferenceOnlyLoanReceipt(this.memberId, newBookIds, ((ReferenceOnlyLoanReceipt) this).getRoomNumber(), true);
        }
        return new LoanReceipt(this.memberId, newBookIds, true);
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int skipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        }

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                skipped++;
            } else {
                processed++;
                if (receipt instanceof ReferenceOnlyLoanReceipt) {
                    referenceOnly++;
                } else {
                    regular++;
                }
            }
        }

        return processed + " processed | " + skipped + " null skipped | " +
               referenceOnly + " reference-only | " + regular + " regular";
    }
}
