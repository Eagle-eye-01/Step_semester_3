package week_6.class_problems;

import java.util.Arrays;

public class DischargeSummary {
    private final String patientId;
    private final String[] medicationCodes;

    static {
        System.out.println("Discharge Ledger System Initialized");
    }

    public DischargeSummary(String patientId, String[] medicationCodes) {
        if (medicationCodes == null) {
            throw new IllegalArgumentException("medicationCodes cannot be null");
        }
        for (String code : medicationCodes) {
            if (!isValidMedCode(code)) {
                throw new IllegalArgumentException("Invalid medication code: " + code);
            }
        }
        this.patientId = patientId;
        this.medicationCodes = Arrays.copyOf(medicationCodes, medicationCodes.length);
    }

    private static boolean isValidMedCode(String code) {
        if (code == null || code.length() != 5) return false;
        if (!code.startsWith("MED-")) return false;
        return Character.isUpperCase(code.charAt(4));
    }

    public String getPatientId() {
        return patientId;
    }

    public String[] getMedicationCodes() {
        return Arrays.copyOf(medicationCodes, medicationCodes.length);
    }

    public DischargeSummary withCorrectedMedication(int index, String newCode) {
        if (!isValidMedCode(newCode)) {
            throw new IllegalArgumentException("Invalid medication code: " + newCode);
        }
        if (index < 0 || index >= medicationCodes.length) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        String[] newCodes = Arrays.copyOf(medicationCodes, medicationCodes.length);
        newCodes[index] = newCode;

        if (this instanceof CriticalCareDischargeSummary) {
            CriticalCareDischargeSummary cc = (CriticalCareDischargeSummary) this;
            return new CriticalCareDischargeSummary(this.patientId, newCodes, cc.getIcuDays());
        }
        return new DischargeSummary(this.patientId, newCodes);
    }

    public static String processNightlyBatch(DischargeSummary[] summaries) {
        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        if (summaries != null) {
            for (DischargeSummary summary : summaries) {
                if (summary == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (summary instanceof CriticalCareDischargeSummary) {
                        criticalCare++;
                    } else {
                        routine++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " +
               criticalCare + " critical-care | " + routine + " routine";
    }
}
