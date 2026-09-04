package oop_basics.assigment_problems;

public class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;

    public BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    public double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5;
        }
        return 0;
    }

    public boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    public static double totalFineCollected(BookIssue[] issues) {
        double total = 0;
        for (BookIssue issue : issues) {
            total += issue.fineAmount();
        }
        return total;
    }

    /*
     * totalFineCollected is static because it operates on a collection of BookIssue objects
     * rather than a single instance. It doesn't depend on the state of any single book issue.
     * fineAmount is an instance method because it relies on the specific state (daysOverdue)
     * of a single book issue to calculate its fine.
     */

    public static void main(String[] args) {
        BookIssue[] issues = new BookIssue[5];
        issues[0] = new BookIssue("Clean Code", "Alice", 18);
        issues[1] = new BookIssue("Effective Java", "Bob", 5);
        issues[2] = new BookIssue("Refactoring", "Charlie", 0);
        issues[3] = new BookIssue("DSA Handbook", "David", 21);
        issues[4] = new BookIssue("Design Patterns", "Eve", 9);

        for (BookIssue issue : issues) {
            String status = issue.isSeverelyOverdue() ? "Severely overdue" : "OK";
            System.out.printf("%s - %d days - %s\n", issue.title, issue.daysOverdue, status);
        }
        System.out.println("Total fine collected: Rs " + BookIssue.totalFineCollected(issues));
    }
}
