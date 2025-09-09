package app;

import app.core.Language;
import app.core.Translator;
import app.engine.ContextualTranslator;
import app.engine.SimpleDictionaryTranslator;
import app.features.Lessons;
import app.features.QuizManager;
import app.util.ConsoleIO;

public class Main {
    public static void main(String[] args) {
        ConsoleIO io = new ConsoleIO();
        Translator base = new SimpleDictionaryTranslator();          // word/phrase dictionary
        Translator translator = new ContextualTranslator(base);      // wraps with simple context rules
        QuizManager quiz = new QuizManager(translator);
        Lessons lessons = new Lessons();

        io.println("=== LinguaLink (Sprint 1) ===");
        Language from = Language.EN;
        Language to = Language.ES;

        while (true) {
            io.println("\nMenu");
            io.println("1) Translate");
            io.println("2) Quiz (practice)");
            io.println("3) Learn (mini-lessons)");
            io.println("4) Swap Languages (current: " + from + " → " + to + ")");
            io.println("0) Exit");
            int choice = io.readInt("Choose: ");

            switch (choice) {
                case 1 -> {
                    String text = io.readLine("Enter text to translate: ").trim();
                    var res = translator.translate(text, from, to);
                    io.println(">> " + res.output() + "  (confidence: " + String.format("%.2f", res.confidence()) + ")");
                    if (!res.explanation().isBlank()) {
                        io.println("note: " + res.explanation());
                    }
                }
                case 2 -> quiz.run(io, from, to);
                case 3 -> lessons.run(io, from, to);
                case 4 -> {
                    Language tmp = from;
                    from = to;
                    to = tmp;
                    io.println("Switched: " + from + " → " + to);
                }
                case 0 -> {
                    io.println("Goodbye!");
                    return;
                }
                default -> io.println("Invalid option.");
            }
        }
    }
}
