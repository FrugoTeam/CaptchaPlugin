package pl.qlnus.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import me.cocos.gui.builder.item.impl.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class PolishLanguageConfiguration extends OkaeriConfig {

    @Comment("Wiadomosc ktora pokazuje sie po zle wykonanej captchy")
    private String kickMessage = "&cWystapil blad podczas weryfikacji";
    @Comment("Nazwa gui (literowego)")
    private String letterMenuName = "&8x &aWeryfikacja %letter%";
    @Comment("Nazwa gui (default)")
    private String defaultMenuName = "&8x &aWeryfikacja";
    @Comment("Item gdy weryfikacja przebiegnie pomyslnie")
    private ItemStack barrier = ItemBuilder.from(Material.BARRIER).name("&cKliknieto").asGuiItem().getItemStack();
    @Comment("Item po ktorym kliknieciu przechodzi sie weryfikacje")
    private final ItemStack itemBuilderDefault = ItemBuilder.from(Material.LIME_DYE)
            .name("&aWeryfikacja")
            .lore(" ", "&8>> &aKliknij aby sie zweryfikowac!", "")
            .asGuiItem()
            .getItemStack();
    @Comment("Informacja jak dziala captcha (literowa)")
    private ItemStack itemBuilderLetter = ItemBuilder.from(Material.PAPER).name(" ").lore("&7Kliknij literke &e%letter% &7wyzej aby przejsc &aweryfikacje", " ").asGuiItem().getItemStack();

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
