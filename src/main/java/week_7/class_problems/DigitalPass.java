package week_7.class_problems;

public class DigitalPass implements Renewable {
    private final String resourceName;

    public DigitalPass(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String renew() {
        return resourceName + " renewed";
    }

    public String getResourceName() {
        return resourceName;
    }
}
