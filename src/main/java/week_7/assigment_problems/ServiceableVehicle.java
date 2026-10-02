package week_7.assigment_problems;

public abstract class ServiceableVehicle {
    private double mileage;

    public ServiceableVehicle() {
        this.mileage = 0.0;
    }

    public abstract String performMaintenance();

    public double getMileage() {
        return mileage;
    }

    public void addMileage(double km) {
        if (km < 0) {
            System.out.println("Rejected: negative distance not allowed");
            return;
        }
        this.mileage += km;
    }

    public static String getInsuranceIfApplicable(ServiceableVehicle v) {
        if (v instanceof Insurable) {
            Insurable insurable = (Insurable) v;
            return insurable.getInsuranceInfo();
        }
        return "No insurance record exists";
    }
}
