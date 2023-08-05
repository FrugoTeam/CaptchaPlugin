package pl.qlnus.language;

import pl.qlnus.configuration.LanguageConfiguration;

public final class LanguageContainer {

    private LanguageContainer() {
    }

    private static LanguageConfiguration LANGUAGE;

    public static void setLanguage(LanguageConfiguration language) {
        if (LANGUAGE != null) {
            throw new IllegalStateException();
        }
        LANGUAGE = language;
    }

    public static <T> T translate(String text, Class<T> type) {
        return LANGUAGE.translate(text, type);
    }
}