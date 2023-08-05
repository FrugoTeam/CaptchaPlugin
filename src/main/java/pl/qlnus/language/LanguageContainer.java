package pl.qlnus.language;

import pl.qlnus.configuration.Configuration;

public final class LanguageContainer {

    private LanguageContainer() {
    }

    private static Language LANGUAGE;

    public static void setLanguage(Language language) {
        LANGUAGE = language;
    }

    public static <T> T translate(String text, Class<T> type) {
        return LANGUAGE.translate(text, type);
    }
}