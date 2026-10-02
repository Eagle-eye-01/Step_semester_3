package oop_basics.class_problems;

public class HostelManagementMiniSystem {

    public static void main(String[] args) {
        HostelRoom r1 = new HostelRoom("C-214", 3, 2); // 1 free bed
        HostelRoom r2 = new HostelRoom("C-507", 2, 1); // 1 free bed
        HostelRoom[] rooms = {r1, r2};

        HostelFeeAccount acc1 = new HostelFeeAccount("RA01", 200000, 60000);
        HostelFeeAccount acc2 = new HostelFeeAccount("RA02", 180000, 0);
        HostelFeeAccount acc3 = new HostelFeeAccount("RA03", 200000, 0);

        // Attempt payments including a negative amount rejected
        acc3.pay(-5000); // rejected

        // Allot rooms to only two students
        HostelRoom roomForStudent1 = HostelRoom.findAvailableRoom(rooms);
        if (roomForStudent1 != null) roomForStudent1.allot("Ravi");

        HostelRoom roomForStudent2 = HostelRoom.findAvailableRoom(rooms);
        if (roomForStudent2 != null) roomForStudent2.allot("Anitha");

        // Third student left unallotted on purpose
        HostelRoom roomForStudent3 = null;

        SrmStudent s1 = new SrmStudent("Ravi", "RA01", acc1, roomForStudent1);
        SrmStudent s2 = new SrmStudent("Anitha", "RA02", acc2, roomForStudent2);
        SrmStudent s3 = new SrmStudent("Karthik", "RA03", acc3, roomForStudent3);

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}
