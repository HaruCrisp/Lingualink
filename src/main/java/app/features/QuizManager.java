package app.features;

import app.core.Language;
import app.core.Translator;
import app.util.ConsoleIO;

import java.util.*;

public class QuizManager {
    private final Translator translator;
    private final Random rng = new Random();

    // Seed items for practice
    private final List<String> practice = List.of(
            "hello", "thank you", "good morning", "student", "teacher",
            "how are you", "computer", "book", "word", "phrase", "translate"
    );

    public QuizManager(Translator translator) {
        this.translator = translator;
    }

    public void run(ConsoleIO io, Language from, Language to) {
        io.println("--- Quiz (5 questions) ---");
        int score = 0;
        for (int i = 1; i <= 5; i++) {
            String prompt = practice.get(rng.nextInt(practice.size()));
            String answer = translator.translate(prompt, from, to).output();

            // Build choices
            Set<String> choices = new LinkedHashSet<>();
            choices.add(answer);
            while (choices.size() < 4) {
                String other = translator.translate(
                        practice.get(rng.nextInt(practice.size())), from, to).output();
                choices.add(other);
            }
            List<String> opts = new ArrayList<>(choices);
            Collections.shuffle(opts, rng);

            io.println("\nQ" + i + ") Translate: \"" + prompt + "\" (" + from + "→" + to + ")");
            for (int k = 0; k < opts.size(); k++) {
                io.println((k + 1) + ") " + opts.get(k));
            }
            int pick = io.readInt("Your answer (1-4): ");
            if (pick >= 1 && pick <= 4 && opts.get(pick - 1).equals(answer)) {
                io.println("✔ Correct!");
                score++;
            } else {
                io.println("✘ Incorrect. Correct answer: " + answer);
            }
        }
        io.println("\nScore: " + score + "/5");
    }
}
