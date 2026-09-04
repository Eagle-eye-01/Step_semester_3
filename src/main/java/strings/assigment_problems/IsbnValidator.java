package strings.assigment_problems;

public class IsbnValidator {
    public static String normalizeCode(String raw) {
        String trimmed = raw.trim();
        if (trimmed.length() >= 3) {
            return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
        }
        return trimmed;
    }

    public static void validateAndFormat(String code) {
        String normalized = normalizeCode(code);
        
        if (normalized.length() != 13) {
            System.out.println("Invalid: wrong length");
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(normalized.charAt(i))) {
                System.out.println("Invalid: publisher code must be 3 letters");
                return;
            }
        }

        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(normalized.charAt(i))) {
                System.out.println("Invalid: non-digit body");
                return;
            }
        }

        String pubCode = normalized.substring(0, 3);
        String year = normalized.substring(3, 7);
        String catalog = normalized.substring(7, 13);
        
        System.out.println("[" + pubCode + "] YEAR: " + year + " | CATALOG: " + catalog);
    }

    public static void main(String[] args) {
        validateAndFormat(" pen2026004251 ");
        validateAndFormat("12N2026004251");
    }
}
