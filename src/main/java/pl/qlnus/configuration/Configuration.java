package pl.qlnus.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

public final class Configuration extends OkaeriConfig {

    @Comment({"Ustaw typ captchy, dostepne: default, letters", "Set the captcha type, available options: default, letters."})
    private String type = "default";
    @Comment({"Wybierz jezyk, dostepne: PL,EN", "Choose a language, available: PL, EN"})
    String language = "pl";

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }
}
