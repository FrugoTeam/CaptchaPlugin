package pl.qlnus.configuration;

import eu.okaeri.configs.OkaeriConfig;

public abstract class LanguageConfiguration extends OkaeriConfig {
    public abstract <T> T translate(String text, Class<T> type);
}
