package week_7.assigment_problems;

public class Trap implements Defendable {
    private final String trapType;

    public Trap(String trapType) {
        if (trapType == null || trapType.trim().isEmpty()) {
            throw new IllegalArgumentException("trapType cannot be blank");
        }
        this.trapType = trapType;
    }

    @Override
    public String defend() {
        return trapType + " triggers automatically";
    }

    public String getTrapType() {
        return trapType;
    }
}
