package strings.assigment_problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class WordFrequencyReport {
    public static void printFilteredWordFrequency(String feedback) {
        String[] stopWordsArray = {"the", "was", "and", "a", "is", "of", "in"};
        List<String> stopWords = Arrays.asList(stopWordsArray);
        
        String normalized = feedback.toLowerCase();
        normalized = normalized.replace(".", "").replace(",", "");
        
        String[] words = normalized.split("\\s+");
        HashMap<String, Integer> frequencyMap = new HashMap<>();
        
        for (String word : words) {
            if (!stopWords.contains(word) && !word.isEmpty()) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
        }
        
        List<Map.Entry<String, Integer>> list = new ArrayList<>(frequencyMap.entrySet());
        list.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        printFilteredWordFrequency("The mentor was great, the session was great and clear.");
    }
}
