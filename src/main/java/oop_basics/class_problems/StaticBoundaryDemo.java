package oop_basics.class_problems;

/*
 * Demonstrating the static vs instance bug:
 * If fields like name, regNo, and attendance are incorrectly declared static,
 * every student object shares the exact same memory location for those fields.
 * Creating a second student silently overwrites the data of the first student.
 */
class BrokenSrmStudent {
    static String name;
    static String regNo;
    static int attendance;

    public BrokenSrmStudent(String name, String regNo, int attendance) {
        BrokenSrmStudent.name = name;
        BrokenSrmStudent.regNo = regNo;
        BrokenSrmStudent.attendance = attendance;
    }

    public String getName() {
        return name;
    }
}

public class StaticBoundaryDemo {
    public static void runDemo() {
        System.out.println("Broken version:");
        BrokenSrmStudent b1 = new BrokenSrmStudent("Ravi", "RA001", 82);
        BrokenSrmStudent b2 = new BrokenSrmStudent("Meera", "RA002", 74);
        System.out.println(b1.getName());
        System.out.println(b2.getName());
        System.out.println("(Ravi's data was overwritten — both students now show \"Meera\")\n");

        System.out.println("Fixed version:");
        SrmStudent s1 = new SrmStudent("Ravi", 82);
        SrmStudent s2 = new SrmStudent("Meera", 74);
        s1.printIdCard();
        s2.printIdCard();
        SrmStudent.printTotalAdmissions();
    }

    public static void main(String[] args) {
        runDemo();
    }
}
