package week_6.class_problems;

public class PremiumMember extends LibraryMember {

    public PremiumMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        super(membershipId, branchCode, finesOwed, displayName);
        this.setPremiumMember(true);
    }
}
