package week_7.assigment_problems;

public class MobileApp implements RemoteControllable {
    private final String appName;

    public MobileApp(String appName) {
        if (appName == null || appName.trim().isEmpty()) {
            throw new IllegalArgumentException("appName cannot be blank");
        }
        this.appName = appName;
    }

    @Override
    public String connect(String appId) {
        return appName + " connected to " + appId;
    }

    public String getAppName() {
        return appName;
    }
}
