package app.engine;

import app.core.Language;
import app.core.Translator;
import app.core.TranslationResult;

import java.util.LinkedHashMap;
import java.util.Map;

public class ContextualTranslator implements Translator {
    private final Translator fallback;

    // Very lightweight “context rules” for Sprint 1:
    // If a phrase contains certain patterns, prefer an idiomatic translation.
    private final Map<String, String> enToEsRules = new LinkedHashMap<>();
    private final Map<String, String> esToEnRules = new LinkedHashMap<>();

    public ContextualTranslator(Translator fallback) {
        this.fallback = fallback;

        // EN→ES idioms/context
        enToEsRules.put("i'm looking for", "estoy buscando");
        enToEsRules.put("can i have", "¿puedo tener?");
        enToEsRules.put("i need help", "necesito ayuda");
        enToEsRules.put("what does .* mean\\?", "¿qué significa .*\\?");

        // ES→EN
        esToEnRules.put("estoy buscando", "i'm looking for");
        esToEnRules.put("¿puedo tener\\?", "can i have?");
        esToEnRules.put("necesito ayuda", "i need help");
        esToEnRules.put("¿qué significa .*\\?", "what does .* mean?");
    }

    @Override
    public TranslationResult translate(String text, Language from, Language to) {
        String norm = text == null ? "" : text.trim().toLowerCase();

        if (from == Language.EN && to == Language.ES) {
            for (var e : enToEsRules.entrySet()) {
                if (norm.matches(".*" + e.getKey() + ".*")) {
                    String out = norm.replaceAll(e.getKey(), e.getValue());
                    return new TranslationResult(text, out, 0.98, "Context rule (EN→ES) applied.");
                }
            }
        } else if (from == Language.ES && to == Language.EN) {
            for (var e : esToEnRules.entrySet()) {
                if (norm.matches(".*" + e.getKey() + ".*")) {
                    String out = norm.replaceAll(e.getKey(), e.getValue());
                    return new TranslationResult(text, out, 0.98, "Context rule (ES→EN) applied.");
                }
            }
        }

        // Otherwise, fallback to dictionary translator
        return fallback.translate(text, from, to);
    }
}
