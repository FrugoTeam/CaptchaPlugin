package pl.qlnus.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

import java.util.Set;

public final class Configuration extends OkaeriConfig {

    @Comment({"Ustaw typ captchy, dostepne: default, letters", "Set the captcha type, available options: default, letters."})
    private String type = "default";
    @Comment({"Wybierz jezyk, dostepne: PL,EN", "Choose a language, available: PL, EN"})
    String language = "pl";
    @Comment({"Odblokowane komendy ktore mozna wpisywac przed wykonaniem captchy", "Unlocked commands that can be entered before completing the captcha"})
    private Set<String> set = Set.of(
            "/tpa"
    );

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    public Set<String> getSet() {
        return set;
    }
}
