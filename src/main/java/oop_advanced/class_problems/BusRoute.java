package oop_advanced.class_problems;

import java.util.Arrays;

public class BusRoute implements Comparable<BusRoute> {
    private final String routeCode;
    private final String routeName;
    private final int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 1);
    }

    @Override
    public int compareTo(BusRoute other) {
        // Priority descending: higher priority ranks first
        if (this.priority != other.priority) {
            return Integer.compare(other.priority, this.priority);
        }

        // Secondary: case-insensitive route code alphabetical
        int codeCmp = this.routeCode.compareToIgnoreCase(other.routeCode);
        if (codeCmp != 0) {
            return codeCmp;
        }

        // Tertiary: route name alphabetical
        return this.routeName.compareTo(other.routeName);
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        if (routes == null) return null;
        BusRoute[] sorted = Arrays.copyOf(routes, routes.length);

        // Stable insertion sort without built-in sort utility
        for (int i = 1; i < sorted.length; i++) {
            BusRoute current = sorted[i];
            int j = i - 1;
            while (j >= 0 && sorted[j].compareTo(current) > 0) {
                sorted[j + 1] = sorted[j];
                j--;
            }
            sorted[j + 1] = current;
        }
        return sorted;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public String getRouteName() {
        return routeName;
    }

    public int getPriority() {
        return priority;
    }

    public static void main(String[] args) {
        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };
        BusRoute[] ranked = rankRoutes(routes);
        String[] codes = new String[ranked.length];
        for (int i = 0; i < ranked.length; i++) {
            codes[i] = ranked[i].getRouteCode();
        }
        System.out.println(Arrays.toString(codes));
    }
}
