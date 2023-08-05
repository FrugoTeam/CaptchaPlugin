package pl.qlnus.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

import java.util.Arrays;
import java.util.List;

public final class Configuration extends OkaeriConfig {

    @Comment({"Ustaw typ captchy, dostepne: default, letters",
            "Set the captcha type, available options: default, letters."})
    public CaptchaType type = CaptchaType.DEFAULT;
    @Comment({"Wybierz jezyk, dostepne: PL,EN",
            "Choose a language, available: PL, EN"})
    public LanguageType language = LanguageType.PL;
    @Comment({"Odblokowane komendy ktore mozna wpisywac przed wykonaniem captchy",
            "Unlocked commands that can be entered before completing the captcha"})
    public List<String> commands = Arrays.asList("/tpa", "/spawn");

    public enum CaptchaType {
        DEFAULT, LETTERS
    }

    public enum LanguageType{
        PL, EN
    }
}
