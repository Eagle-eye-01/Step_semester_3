package week_8.class_problems;

public class EmployeeLeaveSystem {

    public enum LeaveStatus {
        PENDING, APPROVED, REJECTED
    }

    public static abstract class Employee {
        private final String name;

        public Employee(String name) {
            this.name = name;
        }

        public abstract String getEmploymentType();

        public String getName() { return name; }
    }

    public static class FullTimeEmployee extends Employee {
        public FullTimeEmployee(String name) { super(name); }
        @Override public String getEmploymentType() { return "Full-time"; }
    }

    public static class PartTimeEmployee extends Employee {
        public PartTimeEmployee(String name) { super(name); }
        @Override public String getEmploymentType() { return "Part-time"; }
    }

    public static class ContractEmployee extends Employee {
        public ContractEmployee(String name) { super(name); }
        @Override public String getEmploymentType() { return "Contract"; }
    }

    public static class LeaveRequest {
        private final Employee employee;
        private final String fromDate;
        private final String toDate;
        private LeaveStatus status;

        public LeaveRequest(Employee employee, String fromDate, String toDate) {
            this.employee = employee;
            this.fromDate = fromDate;
            this.toDate = toDate;
            this.status = LeaveStatus.PENDING;
            System.out.println("Leave request submitted by " + employee.getName() + " for " +
                    fromDate + " to " + toDate + ". Status: " + status + ".");
        }

        public boolean setStatus(LeaveStatus newStatus) {
            if (this.status == LeaveStatus.APPROVED || this.status == LeaveStatus.REJECTED) {
                if (newStatus == LeaveStatus.PENDING) {
                    System.out.println("Cannot change status: " +
                            (this.status == LeaveStatus.APPROVED ? "Approved" : "Rejected") +
                            " request cannot revert to Pending.");
                    return false;
                }
            }
            this.status = newStatus;
            System.out.println("Leave request for " + employee.getName() + " " +
                    (newStatus == LeaveStatus.APPROVED ? "approved" : "rejected") + ". Status: " + newStatus + ".");
            return true;
        }

        public Employee getEmployee() { return employee; }
        public String getFromDate() { return fromDate; }
        public String getToDate() { return toDate; }
        public LeaveStatus getStatus() { return status; }
    }

    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John Doe");
        LeaveRequest r1 = new LeaveRequest(john, "2024-10-10", "2024-10-12");
        r1.setStatus(LeaveStatus.APPROVED);

        Employee jane = new PartTimeEmployee("Jane Smith");
        new LeaveRequest(jane, "2024-11-01", "2024-11-05");

        r1.setStatus(LeaveStatus.PENDING);
    }
}
