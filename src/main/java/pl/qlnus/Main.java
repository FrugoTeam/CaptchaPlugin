package pl.qlnus;

import me.cocos.gui.CocosGui;
import org.bukkit.plugin.java.JavaPlugin;
import pl.qlnus.listeners.AsyncPlayerChatListener;
import pl.qlnus.listeners.PlayerJoinListener;
import pl.qlnus.services.InventoryService;

import java.util.stream.Stream;

public final class Main extends JavaPlugin {

    private static Main INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        InventoryService inventoryService = new InventoryService();
        CocosGui.initialize();
        Stream.of(
                new PlayerJoinListener(inventoryService),
                new AsyncPlayerChatListener(inventoryService)
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }

    public static Main getInstance() {
        return INSTANCE;
    }
}