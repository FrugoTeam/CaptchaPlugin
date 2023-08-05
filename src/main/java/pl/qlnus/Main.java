package pl.qlnus;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import me.cocos.gui.CocosGui;
import org.bukkit.plugin.java.JavaPlugin;
import pl.qlnus.configuration.Configuration;
import pl.qlnus.configuration.PolishLanguageConfiguration;
import pl.qlnus.configuration.EnglishLanguageConfiguration;
import pl.qlnus.language.Language;
import pl.qlnus.language.LanguageContainer;
import pl.qlnus.language.impl.EnglishLanguage;
import pl.qlnus.language.impl.PolishLanguage;
import pl.qlnus.listeners.AsyncPlayerChatListener;
import pl.qlnus.listeners.PlayerJoinListener;
import pl.qlnus.services.InventoryService;

import java.io.File;
import java.util.logging.Level;
import java.util.stream.Stream;

public final class Main extends JavaPlugin {

    private static Main INSTANCE;
    private Configuration configuration;
    private EnglishLanguageConfiguration englishLanguage;
    private PolishLanguageConfiguration polishLanguage;

    @Override
    public void onEnable() {
        INSTANCE = this;
        Language language = null;
        try {
            configuration = ConfigManager.create(Configuration.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(this.getDataFolder(), "configuration.yml"));
                it.saveDefaults();
                it.load(true);
            });
            String languageCode = this.configuration.getLanguage().toLowerCase();
            switch (languageCode) {
                case "en" -> {
                    englishLanguage = ConfigManager.create(EnglishLanguageConfiguration.class, (it) -> {
                        it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                        it.withBindFile(new File(this.getDataFolder(), "lang_en.yml"));
                        it.saveDefaults();
                        it.load(true);
                    });
                    language = new EnglishLanguage();
                }
                case "pl" -> {
                    polishLanguage = ConfigManager.create(PolishLanguageConfiguration.class, (it) -> {
                        it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                        it.withBindFile(new File(this.getDataFolder(), "lang_pl.yml"));
                        it.saveDefaults();
                        it.load(true);
                    });
                    language = new PolishLanguage();
                }
                default -> this.getLogger().log(Level.SEVERE, "Unsupported language available: PL, EN");
            }
        } catch (Exception exception) {
            this.getLogger().log(Level.SEVERE, "Error loading configuration files.", exception);
            this.getPluginLoader().disablePlugin(this);
            return;
        }
        LanguageContainer.setLanguage(language);
        InventoryService inventoryService = new InventoryService();
        CocosGui.initialize();
        Stream.of(
                new PlayerJoinListener(inventoryService, configuration),
                new AsyncPlayerChatListener(inventoryService)
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }

    public static Main getInstance() {
        return INSTANCE;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public EnglishLanguageConfiguration getEnglishLanguage() {
        return englishLanguage;
    }

    public PolishLanguageConfiguration getPolishLanguage() {
        return polishLanguage;
    }
}