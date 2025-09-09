package app.features;

import app.core.Language;
import app.util.ConsoleIO;

import java.util.LinkedHashMap;
import java.util.Map;

public class Lessons {
    // Simple flashcard-style mini-lessons
    private final Map<String, String> enToEs = new LinkedHashMap<>();

    public Lessons() {
        enToEs.put("Greetings: hello / good morning / good night", "hola / buenos días / buenas noches");
        enToEs.put("Politeness: please / thank you / yes / no", "por favor / gracias / sí / no");
        enToEs.put("Classroom: student / teacher / book / computer", "estudiante / profesor / libro / computadora");
        enToEs.put("Phrases: how are you? / see you later", "¿cómo estás? / hasta luego");
    }

    public void run(ConsoleIO io, Language from, Language to) {
        io.println("--- Learn ---");
        if (from.name().equals("EN") && to.name().equals("ES")) {
            for (var e : enToEs.entrySet()) {
                io.println("* " + e.getKey());
                io.println("  → " + e.getValue());
            }
        } else {
            // reverse view
            for (var e : enToEs.entrySet()) {
                io.println("* " + e.getValue());
                io.println("  → " + e.getKey());
            }
        }
        io.readLine("\nPress Enter to return to menu...");
    }
}
