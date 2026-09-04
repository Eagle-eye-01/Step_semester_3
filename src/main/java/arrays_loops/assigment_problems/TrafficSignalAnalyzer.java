package arrays_loops.assigment_problems;

public class TrafficSignalAnalyzer {
    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) {
            System.out.println("Empty log.");
            return;
        }

        int maxStreak = 0;
        char maxColor = ' ';
        
        int currentStreak = 1;
        char currentColor = signalLog.charAt(0);

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == currentColor) {
                currentStreak++;
            } else {
                if (currentStreak > maxStreak) {
                    maxStreak = currentStreak;
                    maxColor = currentColor;
                }
                currentColor = signalLog.charAt(i);
                currentStreak = 1;
            }
        }

        if (currentStreak > maxStreak) {
            maxStreak = currentStreak;
            maxColor = currentColor;
        }

        System.out.printf("Longest Streak: '%c' repeated %d times\n", maxColor, maxStreak);
    }

    public static void main(String[] args) {
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYGG");
    }
}
