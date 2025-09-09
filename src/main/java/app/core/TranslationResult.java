package app.core;

public record TranslationResult(String input, String output, double confidence, String explanation) {
    public static TranslationResult of(String in, String out, double conf) {
        return new TranslationResult(in, out, conf, "");
    }
    public TranslationResult withExplanation(String note) {
        return new TranslationResult(input, output, confidence, note == null ? "" : note);
    }
}
