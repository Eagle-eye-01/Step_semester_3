package week_8.assigment_problems;

import java.util.*;

public class SmartLabControlPanel {

    public interface Capability {
        String getName();
        boolean execute(String action, Object value, String deviceName);
    }

    public static class PowerCapability implements Capability {
        private boolean on = false;

        @Override
        public String getName() { return "Power"; }

        @Override
        public boolean execute(String action, Object value, String deviceName) {
            if ("SET_POWER".equalsIgnoreCase(action)) {
                this.on = Boolean.parseBoolean(value.toString());
                System.out.println(deviceName + ": " + (on ? "ON" : "OFF") + ".");
                return true;
            }
            return false;
        }

        public boolean isOn() { return on; }
    }

    public static class BrightnessCapability implements Capability {
        private int brightness = 100;

        @Override
        public String getName() { return "Brightness"; }

        @Override
        public boolean execute(String action, Object value, String deviceName) {
            if ("SET_BRIGHTNESS".equalsIgnoreCase(action)) {
                int val = Integer.parseInt(value.toString());
                if (val < 0 || val > 100) {
                    System.out.println("Rejected: " + deviceName + " brightness must be between 0% and 100%.");
                    return false;
                }
                this.brightness = val;
                System.out.println(deviceName + ": brightness set to " + val + "%.");
                return true;
            }
            return false;
        }

        public int getBrightness() { return brightness; }
    }

    public static class TemperatureCapability implements Capability {
        private int temperature = 24;

        @Override
        public String getName() { return "Temperature"; }

        @Override
        public boolean execute(String action, Object value, String deviceName) {
            if ("SET_TEMPERATURE".equalsIgnoreCase(action)) {
                int val = Integer.parseInt(value.toString());
                if (val < 16 || val > 30) {
                    System.out.println("Rejected: " + deviceName + " temperature must be between 16°C and 30°C.");
                    return false;
                }
                this.temperature = val;
                System.out.println(deviceName + ": temperature set to " + val + "°C.");
                return true;
            }
            return false;
        }

        public int getTemperature() { return temperature; }
    }

    public static class Device {
        private final String name;
        private final Map<String, Capability> capabilities = new LinkedHashMap<>();

        public Device(String name) {
            this.name = name;
        }

        public void addCapability(Capability capability) {
            capabilities.put(capability.getName().toUpperCase(), capability);
        }

        public boolean hasCapability(String capName) {
            return capabilities.containsKey(capName.toUpperCase());
        }

        public Capability getCapability(String capName) {
            return capabilities.get(capName.toUpperCase());
        }

        public boolean applyAction(String capName, String action, Object value) {
            Capability cap = capabilities.get(capName.toUpperCase());
            if (cap != null) {
                return cap.execute(action, value, name);
            }
            return false;
        }

        public String getName() { return name; }
    }

    public static class SceneStep {
        final String capabilityName;
        final String action;
        final Object value;

        public SceneStep(String capabilityName, String action, Object value) {
            this.capabilityName = capabilityName;
            this.action = action;
            this.value = value;
        }
    }

    public static class Scene {
        private final String name;
        private final List<SceneStep> steps = new ArrayList<>();

        public Scene(String name) {
            this.name = name;
        }

        public void addStep(String capabilityName, String action, Object value) {
            steps.add(new SceneStep(capabilityName, action, value));
        }

        public void execute(List<Device> devices) {
            System.out.println("Scene '" + name + "' started.");
            int actionsApplied = 0;
            for (SceneStep step : steps) {
                for (Device device : devices) {
                    if (device.hasCapability(step.capabilityName)) {
                        boolean applied = device.applyAction(step.capabilityName, step.action, step.value);
                        if (applied) actionsApplied++;
                    }
                }
            }
            System.out.println("Scene '" + name + "' completed: " + actionsApplied + " actions applied.");
        }
    }

    public static void main(String[] args) {
        Device ac = new Device("Lab AC");
        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());

        Device lights = new Device("Ceiling Lights");
        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());

        Device proj = new Device("Projector");
        proj.addCapability(new PowerCapability());

        List<Device> labDevices = Arrays.asList(ac, lights, proj);

        Scene lectureMode = new Scene("Lecture Mode");
        lectureMode.addStep("Power", "SET_POWER", true);
        lectureMode.addStep("Brightness", "SET_BRIGHTNESS", 40);
        lectureMode.addStep("Temperature", "SET_TEMPERATURE", 24);

        lectureMode.execute(labDevices);

        ac.applyAction("Temperature", "SET_TEMPERATURE", 12);

        proj.addCapability(new BrightnessCapability());
        System.out.println("Projector: Brightness capability added.");
        proj.applyAction("Brightness", "SET_BRIGHTNESS", 70);
    }
}
