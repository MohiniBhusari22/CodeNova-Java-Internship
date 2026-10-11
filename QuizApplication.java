
import java.util.ArrayList;
import java.util.Scanner;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    void display() {
        System.out.println("\n" + question);

        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }
}

public class QuizApplication {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Question> questions = new ArrayList<>();

        // Adding questions
        questions.add(new Question(
            "1. Which language is used for Java development?",
            new String[]{"Java", "HTML", "CSS", "SQL"}, 1));

        questions.add(new Question(
            "2. Which keyword creates an object in Java?",
            new String[]{"class", "new", "static", "void"}, 2));

        questions.add(new Question(
            "3. Which collection allows dynamic resizing?",
            new String[]{"Array", "int", "ArrayList", "char"}, 3));

        questions.add(new Question(
            "4. Which method is the entry point of a Java program?",
            new String[]{"start()", "run()", "main()", "init()"}, 3));

        questions.add(new Question(
            "5. Which keyword is used for inheritance in Java?",
            new String[]{"this", "extends", "import", "final"}, 2));

        int score = 0;

        System.out.println("===== SIMPLE QUIZ APPLICATION =====");
        System.out.println("Choose the correct option (1-4).");

        for (Question q : questions) {
            q.display();

            int answer;

            while (true) {
                System.out.print("Your answer: ");

                try {
                    answer = Integer.parseInt(sc.nextLine());

                    if (answer >= 1 && answer <= 4) {
                        break;
                    }

                    System.out.println("Enter a number from 1 to 4.");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }

            if (answer == q.correctAnswer) {
                System.out.println("Correct answer!");
                score++;
            } else {
                System.out.println("Wrong answer!");
            }
        }

        System.out.println("\n===== QUIZ RESULT =====");
        System.out.println("Total Questions: " + questions.size());
        System.out.println("Correct Answers: " + score);
        System.out.println("Wrong Answers: " + (questions.size() - score));
        System.out.println("Final Score: " + score + "/" + questions.size());
        System.out.println("Percentage: " +
            (score * 100.0 / questions.size()) + "%");

        sc.close();
    }
}
