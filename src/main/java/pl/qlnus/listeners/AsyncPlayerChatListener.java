package pl.qlnus.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.qlnus.Main;
import pl.qlnus.language.LanguageContainer;
import pl.qlnus.services.InventoryService;
import pl.qlnus.utils.ChatUtil;

public final class AsyncPlayerChatListener implements Listener {
    private final InventoryService inventoryService;


    public AsyncPlayerChatListener(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        if (inventoryService.containsUser(player.getUniqueId())) {
            event.setCancelled(true);
            inventoryService.removeUser(player.getUniqueId());
            Bukkit.getScheduler().runTask(Main.getInstance(), () -> player.kick(ChatUtil.colored(LanguageContainer.translate("kick", String.class))));
        }
    }
}
