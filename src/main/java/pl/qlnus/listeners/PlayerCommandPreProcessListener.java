package pl.qlnus.listeners;

import me.cocos.gui.helper.ChatHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pl.qlnus.configuration.Configuration;
import pl.qlnus.language.LanguageContainer;
import pl.qlnus.services.InventoryService;

public final class PlayerCommandPreProcessListener implements Listener {

    private final Configuration configuration;

    private final InventoryService inventoryService;

    public PlayerCommandPreProcessListener(InventoryService inventoryService, Configuration configuration) {
        this.inventoryService = inventoryService;
        this.configuration = configuration;
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String[] args = event.getMessage().split(" ");
        if (inventoryService.containsUser(player.getUniqueId())) {
            if (!configuration.commands.contains(args[0])) {
                event.setCancelled(true);
                player.sendMessage(ChatHelper.colored(LanguageContainer.translate("message", String.class)));
            }
        }
    }
}
