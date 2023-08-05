package pl.qlnus.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.qlnus.menu.VerifyMenu;
import pl.qlnus.services.InventoryService;

public final class PlayerJoinListener implements Listener {
    private final InventoryService inventoryService;

    public PlayerJoinListener(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        inventoryService.addUser(player.getUniqueId());
        VerifyMenu verifyMenu = new VerifyMenu(inventoryService);
        verifyMenu.openGui(player);
    }
}
