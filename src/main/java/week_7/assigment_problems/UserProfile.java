package week_7.assigment_problems;

public class UserProfile implements Exportable {
    private final String username;

    public UserProfile(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("username cannot be blank");
        }
        this.username = username;
    }

    @Override
    public String exportData() {
        Exportable.recordExport();
        return "Exported profile: " + username;
    }

    public String getUsername() {
        return username;
    }
}
