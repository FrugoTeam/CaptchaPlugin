package pl.qlnus;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import me.cocos.gui.CocosGui;
import org.bukkit.plugin.java.JavaPlugin;
import pl.qlnus.configuration.Configuration;
import pl.qlnus.configuration.EnglishLanguageConfiguration;
import pl.qlnus.configuration.LanguageConfiguration;
import pl.qlnus.configuration.PolishLanguageConfiguration;
import pl.qlnus.language.LanguageContainer;
import pl.qlnus.listeners.AsyncPlayerChatListener;
import pl.qlnus.listeners.PlayerCommandPreProcessListener;
import pl.qlnus.listeners.PlayerJoinListener;
import pl.qlnus.services.InventoryService;

import java.io.File;
import java.util.logging.Level;
import java.util.stream.Stream;

public final class Main extends JavaPlugin {

    private static Main INSTANCE;
  

    @Override
    public void onEnable() {
        INSTANCE = this;
        LanguageConfiguration language = null;
        Configuration configuration;
        try {
            configuration = ConfigManager.create(Configuration.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(this.getDataFolder(), "configuration.yml"));
                it.saveDefaults();
                it.load(true);
            });
            switch (configuration.language) {
                case EN -> language = ConfigManager.create(EnglishLanguageConfiguration.class, it -> {
                    it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                    it.withBindFile(new File(this.getDataFolder(), "lang_en.yml"));
                    it.saveDefaults();
                    it.load(true);
                });
                case PL -> language = ConfigManager.create(PolishLanguageConfiguration.class, it -> {
                    it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                    it.withBindFile(new File(this.getDataFolder(), "lang_pl.yml"));
                    it.saveDefaults();
                    it.load(true);
                });
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
                new AsyncPlayerChatListener(inventoryService),
                new PlayerCommandPreProcessListener(inventoryService, configuration)
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }

    public static Main getInstance() {
        return INSTANCE;
    }
    
    
}