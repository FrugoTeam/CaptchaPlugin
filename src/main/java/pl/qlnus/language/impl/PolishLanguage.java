package pl.qlnus.language.impl;

import pl.qlnus.Main;
import pl.qlnus.configuration.PolishLanguageConfiguration;
import pl.qlnus.language.Language;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class PolishLanguage implements Language {
    private final Map<String, Object> translations;

    public PolishLanguage() {
        this.translations = new HashMap<>();
        PolishLanguageConfiguration polishLanguage = Main.getInstance().getPolishLanguage();
        this.translations.put("kick", polishLanguage.getKickMessage());
        this.translations.put("barrier", polishLanguage.getBarrier());
        this.translations.put("letters-gui", polishLanguage.getItemBuilderLetter());
        this.translations.put("letters-gui-name", polishLanguage.getLetterMenuName());
        this.translations.put("default-gui", polishLanguage.getItemBuilderDefault());
        this.translations.put("default-gui-name", polishLanguage.getDefaultMenuName());
        this.translations.put("message", polishLanguage.getMessage());
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
