package week_6.assigment_problems;

public class GroupTicket extends EventTicket {
    private final int groupSize;

    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice);
        if (groupSize <= 0) {
            throw new IllegalArgumentException("groupSize must be positive");
        }
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}
