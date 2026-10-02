package week_7.class_problems;

public interface Alertable {
    String sendAlert(String message);

    static void broadcastAll(Alertable[] devices, String message) {
        if (devices == null) return;
        for (Alertable device : devices) {
            if (device != null) {
                System.out.println(device.sendAlert(message));
            }
        }
    }

    static String getZoneIfMotionSensor(Alertable a) {
        if (a instanceof MotionSensor) {
            MotionSensor ms = (MotionSensor) a;
            return ms.getZoneName();
        }
        return "Not a motion sensor";
    }
}
