package week_8.assigment_problems;

import java.util.*;

public class ElectiveSeatRush {

    public enum StudentType {
        REGULAR(24), HONORS(28), EXCHANGE(20);

        private final int creditLimit;
        StudentType(int limit) { this.creditLimit = limit; }
        public int getCreditLimit() { return creditLimit; }
    }

    public static class Student {
        private final String name;
        private final StudentType type;
        private int currentCredits;

        public Student(String name, StudentType type, int currentCredits) {
            this.name = name;
            this.type = type;
            this.currentCredits = currentCredits;
        }

        public String getName() { return name; }
        public StudentType getType() { return type; }
        public int getCurrentCredits() { return currentCredits; }

        public boolean canAddCredits(int credits) {
            return (currentCredits + credits) <= type.getCreditLimit();
        }

        public void addCredits(int credits) {
            this.currentCredits += credits;
        }

        public void removeCredits(int credits) {
            this.currentCredits -= credits;
        }
    }

    public static class Elective {
        private final String name;
        private final int credits;
        private final int capacity;
        private final List<Student> enrolled = new ArrayList<>();
        private final Queue<Student> waitlist = new LinkedList<>();

        public Elective(String name, int credits, int capacity) {
            this.name = name;
            this.credits = credits;
            this.capacity = capacity;
        }

        public boolean enroll(Student student) {
            if (isAssociated(student)) {
                System.out.println("Enrollment failed: " + student.getName() + " is already enrolled or waitlisted.");
                return false;
            }

            if (!student.canAddCredits(credits)) {
                int attempted = student.getCurrentCredits() + credits;
                System.out.println("Enrollment failed: " + student.getName() + " would exceed the " +
                        student.getType().name().charAt(0) + student.getType().name().substring(1).toLowerCase() +
                        " credit limit (" + attempted + "/" + student.getType().getCreditLimit() + ").");
                return false;
            }

            if (enrolled.size() < capacity) {
                enrolled.add(student);
                student.addCredits(credits);
                System.out.println(student.getName() + " enrolled in " + name + " (credits: " +
                        student.getCurrentCredits() + "/" + student.getType().getCreditLimit() + ").");
                return true;
            } else {
                System.out.print(name + " is full. ");
                waitlist.add(student);
                System.out.println(student.getName() + " added to waitlist (position " + waitlist.size() + ").");
                return true;
            }
        }

        public void drop(Student student) {
            if (!enrolled.contains(student)) {
                System.out.println("Drop failed: " + student.getName() + " is not enrolled in " + name + ".");
                return;
            }

            enrolled.remove(student);
            student.removeCredits(credits);
            System.out.print(student.getName() + " dropped " + name + " (credits: " +
                    student.getCurrentCredits() + "/" + student.getType().getCreditLimit() + "). ");

            // Promote first eligible from waitlist
            while (!waitlist.isEmpty()) {
                Student candidate = waitlist.poll();
                if (candidate.canAddCredits(credits)) {
                    enrolled.add(candidate);
                    candidate.addCredits(credits);
                    System.out.println(candidate.getName() + " promoted from waitlist and enrolled in " +
                            name + " (credits: " + candidate.getCurrentCredits() + "/" +
                            candidate.getType().getCreditLimit() + ").");
                    return;
                }
            }
            System.out.println();
        }

        private boolean isAssociated(Student s) {
            return enrolled.contains(s) || waitlist.contains(s);
        }

        public String getName() { return name; }
        public int getCredits() { return credits; }
        public int getCapacity() { return capacity; }
    }

    public static void main(String[] args) {
        Elective cloud = new Elective("Cloud Computing", 4, 2);

        Student asha = new Student("Asha", StudentType.REGULAR, 20);
        Student ravi = new Student("Ravi", StudentType.HONORS, 22);
        Student neha = new Student("Neha", StudentType.EXCHANGE, 12);
        Student kiran = new Student("Kiran", StudentType.REGULAR, 22);

        cloud.enroll(asha);
        cloud.enroll(ravi);
        cloud.enroll(neha);
        cloud.enroll(kiran);
        cloud.drop(asha);
    }
}
