package oop_basics.class_problems;

public class SrmStudent {
    // Instance fields
    private String name;
    private String regNo;
    private int attendance;
    private HostelFeeAccount feeAccount;
    private HostelRoom room;

    // Static fields
    public static String university = "SRM Institute of Science and Technology";
    public static int admissionCount = 1010;
    public static int totalStudents = 0;

    // F1 Constructor
    public SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
        totalStudents++;
    }

    // F4 Constructor: derives regNo automatically from admissionCount
    public SrmStudent(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        admissionCount++;
        this.regNo = "RA23110030" + admissionCount;
        totalStudents++;
    }

    // F5 Constructor: Capstone tying student, fee account, and room
    public SrmStudent(String name, String regNo, HostelFeeAccount feeAccount, HostelRoom room) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = room;
        totalStudents++;
    }

    // F1: Instance method - operates on this specific student's attendance state
    public void addAttendanceUpdate(int newAttendance) {
        this.attendance = newAttendance;
    }

    // F1: Instance method - checks eligibility of this individual student
    public boolean isEligible() {
        return this.attendance >= 75;
    }

    /*
     * Code comment justification:
     * classAverage is declared static because the average attendance is a property of the collective
     * class of students as a whole, rather than an attribute or state of any single student instance.
     * Conversely, isEligible is an instance method because eligibility is calculated strictly based on
     * an individual student's own attendance record.
     */
    public static double classAverage(SrmStudent[] students) {
        if (students == null || students.length == 0) return 0.0;
        double sum = 0.0;
        for (SrmStudent student : students) {
            if (student != null) {
                sum += student.attendance;
            }
        }
        return sum / students.length;
    }

    // F4: printIdCard
    public void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    // F4: printTotalAdmissions
    public static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + (admissionCount - 1010));
    }

    // F5: Capstone fullStatus
    public String fullStatus() {
        double due = (feeAccount != null) ? feeAccount.getDue() : 0.0;
        String roomStr = (room != null) ? "Room: " + room.getRoomNo() : "Room: unallotted";
        return name + " | Due: Rs " + due + " | " + roomStr;
    }

    public String getName() {
        return name;
    }

    public String getRegNo() {
        return regNo;
    }

    public int getAttendance() {
        return attendance;
    }

    public HostelFeeAccount getFeeAccount() {
        return feeAccount;
    }

    public HostelRoom getRoom() {
        return room;
    }

    public void setRoom(HostelRoom room) {
        this.room = room;
    }
}
