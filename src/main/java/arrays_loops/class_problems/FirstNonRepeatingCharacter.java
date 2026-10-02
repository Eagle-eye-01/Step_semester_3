package arrays_loops.class_problems;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeatingCharacter {

    public static char findFirstNonRepeatingChar(String text) {
        if (text == null || text.isEmpty()) return '\0';

        int[] freq = new int[256];
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 256) {
                freq[c]++;
            }
        }

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 256 && freq[c] == 1) {
                return c;
            }
        }
        return '\0';
    }

    public static String getDisplayResult(String text) {
        char result = findFirstNonRepeatingChar(text);
        if (result != '\0') {
            return "First Non-Repeating Character: '" + result + "'";
        }
        return "No Non-Repeating Character Found";
    }

    public static void main(String[] args) {
        System.out.println(getDisplayResult("swiss"));
        System.out.println(getDisplayResult("aabbcc"));
    }
}
