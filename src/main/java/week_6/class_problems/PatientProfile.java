package week_6.class_problems;

public class PatientProfile {
    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPinHash;
    private boolean patientIdSet;

    public PatientProfile() {
        this(null, null);
    }

    public PatientProfile(String name) {
        this(null, name);
    }

    public PatientProfile(String patientId, String name) {
        if (patientId != null) {
            this.patientId = patientId;
            this.patientIdSet = true;
        } else {
            this.patientId = null;
            this.patientIdSet = false;
        }
        this.name = name;
        this.discharged = false;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String id) {
        if (!patientIdSet && id != null) {
            this.patientId = id;
            this.patientIdSet = true;
        }
        // subsequent calls are silently ignored (write-once)
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    public void setLockerPin(String pin) {
        if (pin != null && pin.matches("\\d{4,6}")) {
            this.lockerPinHash = "HASH_" + pin.hashCode();
        }
    }
}
