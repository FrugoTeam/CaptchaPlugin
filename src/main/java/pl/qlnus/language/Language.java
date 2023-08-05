package pl.qlnus.language;

import java.util.Map;

public interface Language {

    <T> T translate(String text, Class<T> type);

    Map<String, Object> getTranslations();
}