package pl.qlnus.utils;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.List;

public final class ChatUtil {

    private ChatUtil() {
    }

    public static final LegacyComponentSerializer SERIALIZER =
            LegacyComponentSerializer
                    .builder()
                    .hexColors()
                    .extractUrls()
                    .hexCharacter('#')
                    .character('&')
                    .useUnusualXRepeatedCharacterHexFormat()
                    .build();

    public static TextComponent colored(String s) {
        return SERIALIZER.deserialize(s.replace(">>", "»")).decoration(TextDecoration.ITALIC, s.contains("&o"));
    }

    public static List<TextComponent> colored(List<String> text) {
        return text.stream().map(ChatUtil::colored).toList();
    }
}
