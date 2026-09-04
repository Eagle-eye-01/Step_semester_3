package oop_basics.assigment_problems;

class LibraryMemberBroken {
    static String name;
    static String memberId;
    static int booksIssued;
    
    /*
     * Explanation of why static is wrong here:
     * - name: A member's name is unique to the individual. Making it static means all members share one name,
     *   so creating a new member overwrites everyone else's name.
     * - memberId: Like name, the ID must be unique per member. Static makes it shared.
     * - booksIssued: Each member tracks their own borrowed books. Static would track one global total
     *   instead of per-member totals.
     */
}

class LibraryMember {
    String name;
    String memberId;
    int booksIssued;

    static String libraryName = "Central Library";
    static int memberCount = 0;

    public LibraryMember(String name) {
        this.name = name;
        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
        this.booksIssued = 0;
    }

    public void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    public static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }

    public static void main(String[] args) {
        System.out.println("Broken version:");
        LibraryMemberBroken.name = "Aditi";
        LibraryMemberBroken.memberId = "LM-1001";
        
        LibraryMemberBroken.name = "Rohan";
        LibraryMemberBroken.memberId = "LM-1002";
        
        System.out.println(LibraryMemberBroken.name);
        System.out.println(LibraryMemberBroken.name);
        System.out.println("(Aditi's data was overwritten \u2014 both members now show \"Rohan\")\n");

        System.out.println("Fixed version: same two members created");
        LibraryMember m1 = new LibraryMember("Aditi");
        LibraryMember m2 = new LibraryMember("Rohan");
        
        m1.printMemberCard();
        m2.printMemberCard();
        LibraryMember.printTotalMembers();
    }
}
