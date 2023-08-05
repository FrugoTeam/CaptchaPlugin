package pl.qlnus.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import me.cocos.gui.builder.item.impl.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


public final class EnglishLanguageConfiguration extends OkaeriConfig {
    @Comment("Message displayed after an incorrect captcha attempt")
    private String kickMessage = "&cAn error occurred during verification";
    @Comment("GUI name (letter-based)")
    private String letterMenuName = "&8x &aVerification %letter%";
    @Comment("GUI name (default)")
    private String defaultMenuName = "&8x &aVerification";
    @Comment("Item when verification is successful")
    private ItemStack barrier = ItemBuilder.from(Material.BARRIER).name("&cClicked").asGuiItem().getItemStack();
    @Comment("Item that triggers verification when clicked")
    private final ItemStack itemBuilderDefault = ItemBuilder.from(Material.LIME_DYE)
            .name("&aVerification")
            .lore(" ", "&8>> &aClick to verify!", "")
            .asGuiItem()
            .getItemStack();
    @Comment("Information on how the captcha works (letter-based)")
    private ItemStack itemBuilderLetter = ItemBuilder.from(Material.PAPER).name(" ").lore("&7Click the letter &e%letter% &7above to complete &averification", " ").asGuiItem().getItemStack();

    public String getKickMessage() {
        return kickMessage;
    }

    public String getLetterMenuName() {
        return letterMenuName;
    }

    public ItemStack getBarrier() {
        return barrier;
    }

    public String getDefaultMenuName() {
        return defaultMenuName;
    }

    public ItemStack getItemBuilderLetter() {
        return itemBuilderLetter;
    }

    public ItemStack getItemBuilderDefault() {
        return itemBuilderDefault;
    }
}