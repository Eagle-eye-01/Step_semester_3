package oop_advanced.assigment_problems;

import java.util.Arrays;

public class Canteen implements Comparable<Canteen> {
    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    @Override
    public int compareTo(Canteen other) {
        // Sort by trustScore descending
        if (this.trustScore != other.trustScore) {
            return Integer.compare(other.trustScore, this.trustScore);
        }
        
        // Tie breaker 1: canteen code (case-insensitive)
        int codeComparison = this.canteenCode.compareToIgnoreCase(other.canteenCode);
        if (codeComparison != 0) {
            return codeComparison;
        }
        
        // Tie breaker 2: name length
        return Integer.compare(this.canteenName.length(), other.canteenName.length());
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {
        // Bubble sort to rank without built-in utilities
        for (int i = 0; i < canteens.length - 1; i++) {
            for (int j = 0; j < canteens.length - 1 - i; j++) {
                if (canteens[j].compareTo(canteens[j + 1]) > 0) {
                    Canteen temp = canteens[j];
                    canteens[j] = canteens[j + 1];
                    canteens[j + 1] = temp;
                }
            }
        }
        return canteens;
    }

    public String getCanteenCode() {
        return canteenCode;
    }

    public static void main(String[] args) {
        Canteen c1 = new Canteen("HB3-C", "Spice Junction", 3);
        Canteen c2 = new Canteen("hb1-c", "Grand Mess", 5);
        Canteen c3 = new Canteen("HB2-C", "Southern Treats");
        
        Canteen[] arr = {c1, c2, c3};
        Canteen[] ranked = rankCanteens(arr);
        
        String[] result = new String[ranked.length];
        for (int i = 0; i < ranked.length; i++) {
            result[i] = ranked[i].getCanteenCode();
        }
        System.out.println(Arrays.toString(result));
    }
}
