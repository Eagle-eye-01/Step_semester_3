package arrays_loops.assigment_problems;

public class TypingAccuracyChecker {
    public static void checkTypingAccuracy(String original, String typed) {
        if (original == null || typed == null || original.length() != typed.length()) {
            System.out.println("Invalid input strings.");
            return;
        }

        int matchCount = 0;
        int firstMismatch = -1;

        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matchCount++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }

        double accuracy = ((double) matchCount / original.length()) * 100;
        System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | ", matchCount, original.length(), accuracy);

        if (firstMismatch != -1) {
            System.out.printf("First Mismatch at position %d ('%c' vs '%c')\n",
                    firstMismatch, original.charAt(firstMismatch), typed.charAt(firstMismatch));
        } else {
            System.out.println("No Mismatches");
        }
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}
