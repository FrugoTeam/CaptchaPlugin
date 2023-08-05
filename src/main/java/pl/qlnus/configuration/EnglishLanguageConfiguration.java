package pl.qlnus.configuration;

import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.Exclude;
import me.cocos.gui.builder.item.impl.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class EnglishLanguageConfiguration extends LanguageConfiguration {
    @Exclude
    private final Map<String, Object> translations;
    @Comment("Message displayed after an incorrect captcha attempt")
    private String kickMessage = "&cAn error occurred during verification";
    @Comment("GUI name (letter-based)")
    private String letterMenuName = "&8x &aVerification %letter%";
    @Comment("GUI name (default)")
    private String defaultMenuName = "&8x &aVerification";
    @Comment("Item when verification is successful")
    private ItemStack barrier = ItemBuilder.from(Material.BARRIER).name("&cClicked").asGuiItem().getItemStack();
    @Comment("Item that triggers verification when clicked")
    private List<String> itemBuilderDefault = List.of(" ", "&8>> &aClick to verify!", "");
    @Comment("Information on how the captcha works (letter-based)")
    private ItemStack itemBuilderLetter = ItemBuilder.from(Material.PAPER).name(" ").lore("&7Click the letter &e%letter% &7above to complete &averification", " ").asGuiItem().getItemStack();
    @Comment("Message to be sent when the player hasn't completed the captcha and tries to enter a command")
    private String message = "&cYou can't use this.";

    public EnglishLanguageConfiguration() {
        this.translations = new HashMap<>();
        this.translations.put("kick", kickMessage);
        this.translations.put("barrier", barrier);
        this.translations.put("letters-gui", itemBuilderLetter);
        this.translations.put("letters-gui-name", letterMenuName);
        this.translations.put("default-gui", itemBuilderDefault);
        this.translations.put("default-gui-name", defaultMenuName);
        this.translations.put("message", message);
    }

    @Override
    public <T> T translate(String text, Class<T> type) {
        Object object = this.translations.getOrDefault(text, "");
        return type.cast(object);
    }
}