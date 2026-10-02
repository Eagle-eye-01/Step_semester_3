package week_6.class_problems;

public class PatientRecord {
    private final String patientId;
    protected String wardCode;
    double vitalsScore;
    public String facilityName;

    public PatientRecord(String patientId, String wardCode, double vitalsScore, String facilityName) {
        if (patientId == null || patientId.trim().length() < 4) {
            throw new IllegalArgumentException("patientId must be at least 4 characters long and not blank");
        }
        this.patientId = patientId.trim();
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getWardCode() {
        return wardCode;
    }

    public double getVitalsScore() {
        return vitalsScore;
    }

    public String getFacilityName() {
        return facilityName;
    }
}
