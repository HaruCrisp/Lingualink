package app.engine;

import app.core.Language;
import app.core.Translator;
import app.core.TranslationResult;

import java.util.*;

public class SimpleDictionaryTranslator implements Translator {
    // Minimal bilingual dictionary; extend in later sprints or data files.
    // Keys are lowercase.
    private final Map<String, String> enToEs = new HashMap<>();
    private final Map<String, String> esToEn = new HashMap<>();

    public SimpleDictionaryTranslator() {
        // words
        put("hello", "hola");
        put("goodbye", "adiós");
        put("please", "por favor");
        put("thank you", "gracias");
        put("yes", "sí");
        put("no", "no");
        put("student", "estudiante");
        put("teacher", "profesor");
        put("book", "libro");
        put("computer", "computadora");
        put("translate", "traducir");
        put("word", "palabra");
        put("phrase", "frase");

        // short phrases
        put("how are you", "¿cómo estás?");
        put("see you later", "hasta luego");
        put("good morning", "buenos días");
        put("good night", "buenas noches");
        put("thank you very much", "muchas gracias");
    }

    private void put(String en, String es) {
        enToEs.put(en, es);
        esToEn.put(es, en);
    }

    @Override
    public TranslationResult translate(String text, Language from, Language to) {
        if (text == null || text.isBlank() || from == to) {
            return new TranslationResult(text, text, 1.0, "");
        }
        String norm = text.trim().toLowerCase();

        // Try full phrase first
        String direct = (from == Language.EN) ? enToEs.get(norm) : esToEn.get(norm);
        if (direct != null) {
            return TranslationResult.of(text, direct, 0.95);
        }

        // Token-by-token fallback
        String[] tokens = norm.split("\\s+");
        List<String> out = new ArrayList<>();
        int known = 0;
        for (String t : tokens) {
            String mapped = (from == Language.EN) ? enToEs.get(t) : esToEn.get(t);
            if (mapped != null) {
                out.add(mapped);
                known++;
            } else {
                out.add("[" + t + "]"); // unknown marker
            }
        }
        double conf = tokens.length == 0 ? 0 : (0.60 + 0.35 * (known / (double) tokens.length));
        return TranslationResult.of(text, String.join(" ", out), conf);
    }
}
