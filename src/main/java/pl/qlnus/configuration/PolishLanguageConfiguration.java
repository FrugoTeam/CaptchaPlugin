package pl.qlnus.configuration;

import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.Exclude;
import me.cocos.gui.builder.item.impl.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PolishLanguageConfiguration extends LanguageConfiguration {

    @Exclude
    private final Map<String, Object> translations;
    @Comment("Wiadomosc ktora pokazuje sie po zle wykonanej captchy")
    private String kickMessage = "&cWystapil blad podczas weryfikacji";
    @Comment("Nazwa gui (literowego)")
    private String letterMenuName = "&8x &aWeryfikacja %letter%";
    @Comment("Nazwa gui (default)")
    private String defaultMenuName = "&8x &aWeryfikacja";
    @Comment("Item gdy weryfikacja przebiegnie pomyslnie")
    private ItemStack barrier = ItemBuilder.from(Material.BARRIER).name("&cKliknieto").asGuiItem().getItemStack();
    @Comment("Item po ktorym kliknieciu przechodzi sie weryfikacje")
    private List<String> itemBuilderDefault = List.of(" ", "&8>> &aKliknij aby sie zweryfikowac!", "");
    @Comment("Informacja jak dziala captcha (literowa)")
    private ItemStack itemBuilderLetter = ItemBuilder.from(Material.PAPER).name(" ").lore("&7Kliknij literke &e%letter% &7wyzej aby przejsc &aweryfikacje", " ").asGuiItem().getItemStack();
    @Comment("Wiadomosc ktora ma sie wyslac gdy gracz nie zrobil captchy i chce wpisac komende")
    private String message = "&cNie mozesz tego uzyc";


    public PolishLanguageConfiguration() {
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
