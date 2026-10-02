package week_5.class_problems;

public class LibraryMember {
    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    private boolean premiumMember;
    private String securityAnswer;

    public LibraryMember() {
        this(null, null, 0.0, null);
    }

    public LibraryMember(String displayName) {
        this(null, null, 0.0, displayName);
    }

    public LibraryMember(String membershipId, String displayName) {
        this(membershipId, null, 0.0, displayName);
    }

    public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        if (membershipId != null) {
            String trimmed = membershipId.trim();
            if (trimmed.isEmpty() || trimmed.length() < 4) {
                throw new IllegalArgumentException("membershipId must be at least 4 characters long and not blank.");
            }
            this.membershipId = trimmed;
        }
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (this.membershipId != null) {
            return; 
        }
        if (id != null) {
            String trimmed = id.trim();
            if (trimmed.isEmpty() || trimmed.length() < 4) {
                throw new IllegalArgumentException("membershipId must be at least 4 characters long and not blank.");
            }
            this.membershipId = trimmed;
        }
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public double getFinesOwed() {
        return finesOwed;
    }

    public void setFinesOwed(double finesOwed) {
        this.finesOwed = finesOwed;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premiumMember) {
        this.premiumMember = premiumMember;
    }

    public void setSecurityAnswer(String answer) {
        if (answer != null) {
            this.securityAnswer = String.valueOf(answer.hashCode());
        }
    }
}
