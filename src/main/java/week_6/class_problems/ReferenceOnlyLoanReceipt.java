package week_6.class_problems;

public class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
    
    // Internal constructor for wither method
    protected ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber, boolean skipValidation) {
        super(memberId, bookIds, skipValidation);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}
