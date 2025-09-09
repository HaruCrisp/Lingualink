package app.core;

public interface Translator {
    TranslationResult translate(String text, Language from, Language to);
}
