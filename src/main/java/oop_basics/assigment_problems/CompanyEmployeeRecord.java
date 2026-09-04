package oop_basics.assigment_problems;

class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;
    
    static int totalRecords = 0;

    public CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }

    public String fullProfile() {
        double pay = employee.getSalary();
        if (employee instanceof ManagerEmployee) {
            pay = ((ManagerEmployee) employee).effectiveSalary();
        } else if (employee instanceof InternEmployee) {
            pay = ((InternEmployee) employee).effectiveSalary();
        }
        
        String slotDisplay = (slot != null) ? slot.slotNo : "no parking assigned";
        
        return name + " | Pay: Rs " + pay + " | Slot: " + slotDisplay;
    }

    public static void main(String[] args) {
        ManagerEmployee divya = new ManagerEmployee("E1", "Divya", 70000, 8000);
        Employee karan = new Employee("E2", "Karan", 40000);
        InternEmployee meera = new InternEmployee("E3", "Meera", 10000, 10000);
        
        ParkingSlot s1 = new ParkingSlot("A1", 1, 1);
        ParkingSlot s2 = new ParkingSlot("A2", 1, 1);
        
        CompanyEmployeeRecord r1 = new CompanyEmployeeRecord("Divya", "E1", divya, s1);
        CompanyEmployeeRecord r2 = new CompanyEmployeeRecord("Karan", "E2", karan, s2);
        CompanyEmployeeRecord r3 = new CompanyEmployeeRecord("Meera", "E3", meera, null);
        
        System.out.println("3 records; parking allotted to 2 of them");
        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}
