package week_7.assigment_problems;

public abstract class HomeDevice {
    private static int counter = 1000;
    private final String serialNumber;

    public HomeDevice() {
        this.serialNumber = "HD-" + (++counter);
    }

    public abstract String activate();

    public String getSerialNumber() {
        return serialNumber;
    }

    public static double getConsumptionIfTrackable(HomeDevice d) {
        if (d instanceof EnergyTrackable) {
            EnergyTrackable et = (EnergyTrackable) d;
            return et.getConsumptionWatts();
        }
        return -1.0;
    }
}
