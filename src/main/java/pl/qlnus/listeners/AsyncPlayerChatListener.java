package pl.qlnus.listeners;

import me.cocos.gui.helper.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.qlnus.Main;
import pl.qlnus.services.InventoryService;

public final class AsyncPlayerChatListener implements Listener {
    private final InventoryService inventoryService;

    public AsyncPlayerChatListener(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (inventoryService.containsUser(player.getUniqueId())) {
            event.setCancelled(true);
            inventoryService.removeUser(player.getUniqueId());
            Bukkit.getScheduler().runTask(Main.getInstance(), () -> player.kickPlayer(ChatHelper.colored("&cWystapil blad podczas weryfikacji!")));
        }
    }
}
