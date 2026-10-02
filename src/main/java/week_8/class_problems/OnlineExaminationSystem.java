package week_8.class_problems;

import java.util.*;

public class OnlineExaminationSystem {

    public interface Question {
        String getId();
        String getPrompt();
        boolean isCorrect(String studentAnswer);
    }

    public static class MultipleChoiceQuestion implements Question {
        private final String id;
        private final String prompt;
        private final String correctAnswer;

        public MultipleChoiceQuestion(String id, String prompt, String correctAnswer) {
            this.id = id;
            this.prompt = prompt;
            this.correctAnswer = correctAnswer;
        }

        @Override
        public String getId() { return id; }

        @Override
        public String getPrompt() { return prompt; }

        @Override
        public boolean isCorrect(String studentAnswer) {
            return correctAnswer.equalsIgnoreCase(studentAnswer);
        }
    }

    public static class Student {
        private final String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() { return name; }
    }

    public static class Examination {
        private final String title;
        private final List<Question> questions = new ArrayList<>();

        public Examination(String title) {
            this.title = title;
        }

        public void addQuestion(Question q) {
            questions.add(q);
        }

        public String getTitle() { return title; }
        public List<Question> getQuestions() { return questions; }
    }

    public static class Attempt {
        private final Student student;
        private final Examination examination;
        private final Map<String, String> answers = new LinkedHashMap<>();
        private boolean submitted = false;

        public Attempt(Student student, Examination examination) {
            this.student = student;
            this.examination = examination;
            System.out.println("Examination '" + examination.getTitle() + "' started by " + student.getName() + ".");
        }

        public void answerQuestion(int questionNumber, String answer) {
            if (submitted) {
                System.out.println("Cannot change answers: Attempt has already been submitted.");
                return;
            }
            answers.put("Q" + questionNumber, answer);
            System.out.println("Question " + questionNumber + " answered with '" + answer + "'.");
        }

        public void submit() {
            if (submitted) {
                System.out.println("Examination already submitted.");
                return;
            }
            this.submitted = true;
            System.out.println("Examination '" + examination.getTitle() + "' submitted successfully.");

            int score = 0;
            List<Question> questions = examination.getQuestions();
            for (int i = 0; i < questions.size(); i++) {
                Question q = questions.get(i);
                String ans = answers.get("Q" + (i + 1));
                if (ans != null && q.isCorrect(ans)) {
                    score++;
                }
            }

            System.out.println("Result for '" + examination.getTitle() + "' attempt: " +
                    score + "/" + questions.size() + " correct (assuming Question 1 correct answer is 'A', Question 2 correct answer is 'B').");
        }
    }

    public static void main(String[] args) {
        Examination exam = new Examination("Math Quiz");
        exam.addQuestion(new MultipleChoiceQuestion("1", "What is 2+2?", "A"));
        exam.addQuestion(new MultipleChoiceQuestion("2", "What is 3+3?", "B"));

        Student student = new Student("Student");
        Attempt attempt = new Attempt(student, exam);
        attempt.answerQuestion(1, "A");
        attempt.answerQuestion(2, "C");
        attempt.submit();
    }
}
