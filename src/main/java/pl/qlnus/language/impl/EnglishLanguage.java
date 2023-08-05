package pl.qlnus.language.impl;

import pl.qlnus.Main;
import pl.qlnus.configuration.EnglishLanguageConfiguration;
import pl.qlnus.language.Language;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class EnglishLanguage implements Language {
    private final Map<String, Object> translations;

    public EnglishLanguage() {
        this.translations = new HashMap<>();
        EnglishLanguageConfiguration englishLanguage = Main.getInstance().getEnglishLanguage();
        this.translations.put("kick", englishLanguage.getKickMessage());
        this.translations.put("barrier", englishLanguage.getBarrier());
        this.translations.put("letters-gui", englishLanguage.getItemBuilderLetter());
        this.translations.put("letters-gui-name", englishLanguage.getLetterMenuName());
        this.translations.put("default-gui", englishLanguage.getItemBuilderDefault());
        this.translations.put("default-gui-name", englishLanguage.getDefaultMenuName());
    }

    @Override
    public <T> T translate(String text, Class<T> type) {
        Object object = this.translations.getOrDefault(text, "");
        return type.cast(object);
    }

    @Override
    public Map<String, Object> getTranslations() {
        return Collections.unmodifiableMap(this.translations);
    }
}
