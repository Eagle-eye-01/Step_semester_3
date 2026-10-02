package strings.class_problems;

public class BankTransactionReference {

    public static String normalizeReference(String raw) {
        if (raw == null) return null;
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    public static String validateAndFormat(String reference) {
        if (reference == null || reference.length() != 14) {
            return "Invalid: wrong length";
        }

        // Validate first 3 are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        // Validate remaining 11 are digits
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: reference body must be 11 digits";
            }
        }

        String bankCode = reference.substring(0, 3);
        String day = reference.substring(3, 5);
        String month = reference.substring(5, 7);
        String year = reference.substring(7, 9);
        String seq = reference.substring(9, 14);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] ");
        sb.append("DATE: ").append(day).append("/").append(month).append("/").append(year);
        sb.append(" | SEQ: ").append(seq);

        return sb.toString();
    }

    public static String process(String raw) {
        String normalized = normalizeReference(raw);
        return validateAndFormat(normalized);
    }

    public static void main(String[] args) {
        System.out.println(process(" hdf03022600042 "));
        System.out.println(process("12F03022600042"));
    }
}
