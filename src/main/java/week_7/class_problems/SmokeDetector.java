package week_7.class_problems;

public class SmokeDetector implements Alertable {
    private final String deviceId;

    public SmokeDetector(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public String sendAlert(String message) {
        return "[" + deviceId + "] " + message;
    }
}
