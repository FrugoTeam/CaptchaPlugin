package pl.qlnus.language;

public final class LanguageContainer {

    private LanguageContainer() {
    }

    private static Language LANGUAGE;

    public static void setLanguage(Language language) {
        if (LANGUAGE != null) {
            throw new IllegalStateException();
        }
        LANGUAGE = language;
    }

    public static <T> T translate(String text, Class<T> type) {
        return LANGUAGE.translate(text, type);
    }
}