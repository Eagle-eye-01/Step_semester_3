package week_6.class_problems;

public class AccessChecker {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "public":
                return "ALLOWED";
            case "private":
                if ("SAME_CLASS".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";
            case "default":
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";
            case "protected":
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext) || 
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";
            default:
                return "DENIED";
        }
    }

    public static String summarizeByModifier(String[][] attempts) {
        int privateAllowed = 0, privateDenied = 0;
        int defaultAllowed = 0, defaultDenied = 0;
        int protectedAllowed = 0, protectedDenied = 0;
        int publicAllowed = 0, publicDenied = 0;

        for (String[] attempt : attempts) {
            String mod = attempt[0];
            String ctx = attempt[1];
            boolean allowed = "ALLOWED".equals(classifyAccess(mod, ctx));

            switch (mod) {
                case "private":
                    if (allowed) privateAllowed++; else privateDenied++;
                    break;
                case "default":
                    if (allowed) defaultAllowed++; else defaultDenied++;
                    break;
                case "protected":
                    if (allowed) protectedAllowed++; else protectedDenied++;
                    break;
                case "public":
                    if (allowed) publicAllowed++; else publicDenied++;
                    break;
            }
        }

        return "private: " + privateAllowed + " allowed / " + privateDenied + " denied | " +
               "default: " + defaultAllowed + " allowed / " + defaultDenied + " denied | " +
               "protected: " + protectedAllowed + " allowed / " + protectedDenied + " denied | " +
               "public: " + publicAllowed + " allowed / " + publicDenied + " denied";
    }

    public static String describeContext(String accessorContext) {
        String[] parts = accessorContext.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].isEmpty()) continue;
            sb.append(parts[i].substring(0, 1).toUpperCase());
            if (parts[i].length() > 1) {
                sb.append(parts[i].substring(1).toLowerCase());
            }
            if (i < parts.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
