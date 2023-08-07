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
        EnglishLanguageConfiguration config = Main.getInstance().getEnglishLanguageConfiguration();
        this.translations = new HashMap<>();
        this.translations.put("kick", config.getKickMessage());
        this.translations.put("barrier", config.getBarrier());
        this.translations.put("letters-gui", config.getItemBuilderLetter());
        this.translations.put("letters-gui-name", config.getLetterMenuName());
        this.translations.put("default-gui", config.getItemBuilderDefault());
        this.translations.put("default-gui-name", config.getDefaultMenuName());
        this.translations.put("message", config.getMessage());
        this.translations.put("color-gui", config.getItemBuilderColor());
        this.translations.put("color-gui-name", config.getColorMenuName());
        this.translations.put("red", "&cred");
        this.translations.put("green", "&agreen");
        this.translations.put("blue", "&1blue");
        this.translations.put("yellow", "&eyellow");
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