package week_6.class_problems;

public class CriticalCareDischargeSummary extends DischargeSummary {
    private final int icuDays;

    public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
        super(patientId, medicationCodes);
        if (icuDays < 0) {
            throw new IllegalArgumentException("icuDays cannot be negative");
        }
        this.icuDays = icuDays;
    }

    public int getIcuDays() {
        return icuDays;
    }
}
