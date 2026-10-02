package week_8.assigment_problems;

import java.util.*;

public class CodeSprintJudgingDesk {

    public interface ScoringRule {
        double calculateScore(double idea, double execution, double presentation);
        String getTrackName();
    }

    public static class InnovationTrackScoring implements ScoringRule {
        @Override
        public double calculateScore(double idea, double execution, double presentation) {
            return (idea * 0.50) + (execution * 0.30) + (presentation * 0.20);
        }

        @Override
        public String getTrackName() {
            return "Innovation track";
        }
    }

    public static class OpenTrackScoring implements ScoringRule {
        @Override
        public double calculateScore(double idea, double execution, double presentation) {
            return (idea + execution + presentation) / 3.0;
        }

        @Override
        public String getTrackName() {
            return "Open track";
        }
    }

    public static class Student {
        private final String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class Project {
        private final String title;
        private double ideaScore;
        private double executionScore;
        private double presentationScore;
        private boolean scored;

        public Project(String title) {
            this.title = title;
            this.scored = false;
        }

        public String getTitle() {
            return title;
        }

        public void setScore(double idea, double execution, double presentation) {
            this.ideaScore = idea;
            this.executionScore = execution;
            this.presentationScore = presentation;
            this.scored = true;
        }

        public boolean isScored() {
            return scored;
        }

        public double getIdeaScore() { return ideaScore; }
        public double getExecutionScore() { return executionScore; }
        public double getPresentationScore() { return presentationScore; }
    }

    public static class Team {
        private final String teamName;
        private final List<Student> members;
        private final ScoringRule track;
        private Project project;

        public Team(String teamName, List<Student> members, ScoringRule track) {
            this.teamName = teamName;
            this.members = members;
            this.track = track;
        }

        public String getTeamName() { return teamName; }
        public List<Student> getMembers() { return members; }
        public ScoringRule getTrack() { return track; }

        public void submitProject(Project p) {
            if (this.project != null) {
                System.out.println("Submission failed: Team already submitted a project.");
                return;
            }
            this.project = p;
            System.out.println("Project '" + p.getTitle() + "' submitted by " + teamName + ".");
        }

        public Project getProject() { return project; }
    }

    public enum HackathonState { OPEN, JUDGING, PUBLISHED }

    public static class Hackathon {
        private HackathonState state;
        private final List<Team> teams = new ArrayList<>();
        private final Set<String> registeredStudentNames = new HashSet<>();

        public Hackathon() {
            this.state = HackathonState.OPEN;
        }

        public boolean registerTeam(String teamName, List<Student> members, ScoringRule track) {
            if (state == HackathonState.PUBLISHED) {
                System.out.println("Registration failed: Hackathon has ended.");
                return false;
            }
            if (members.size() < 2 || members.size() > 4) {
                System.out.println("Registration failed: A team must have 2 to 4 members.");
                return false;
            }
            for (Student s : members) {
                if (registeredStudentNames.contains(s.getName().toLowerCase())) {
                    System.out.println("Registration failed: Student " + s.getName() + " already belongs to a team.");
                    return false;
                }
            }
            for (Student s : members) {
                registeredStudentNames.add(s.getName().toLowerCase());
            }
            Team team = new Team(teamName, members, track);
            teams.add(team);
            System.out.println("Team " + teamName + " registered (" + members.size() + " members, " + track.getTrackName() + ").");
            return true;
        }

        public void scoreProject(Team team, double idea, double execution, double presentation) {
            if (state == HackathonState.PUBLISHED) {
                System.out.println("Rescore rejected: Results have already been published.");
                return;
            }
            Project p = team.getProject();
            if (p == null) {
                System.out.println("Scoring failed: No project submitted by team.");
                return;
            }
            p.setScore(idea, execution, presentation);
            double finalScore = team.getTrack().calculateScore(idea, execution, presentation);
            System.out.printf(Locale.US, "Score recorded for '%s'. Final score: %.2f.%n", p.getTitle(), finalScore);
        }

        public void publishResults() {
            this.state = HackathonState.PUBLISHED;
            System.out.println("Results published.");
        }
    }

    public static void main(String[] args) {
        Hackathon hackathon = new Hackathon();

        List<Student> busters = Arrays.asList(new Student("Asha"), new Student("Ravi"), new Student("Neha"));
        hackathon.registerTeam("ByteBusters", busters, new InnovationTrackScoring());

        List<Student> solo = Collections.singletonList(new Student("Kiran"));
        hackathon.registerTeam("SoloCoder", solo, new OpenTrackScoring());

        Team byteBusters = hackathon.teams.get(0);
        Project smartAttend = new Project("SmartAttend");
        byteBusters.submitProject(smartAttend);

        hackathon.scoreProject(byteBusters, 8, 7, 9);
        hackathon.publishResults();
        hackathon.scoreProject(byteBusters, 10, 7, 9);
    }
}
