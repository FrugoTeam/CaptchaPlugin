package pl.qlnus.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.qlnus.configuration.Configuration;
import pl.qlnus.menu.LettersVerifyMenu;
import pl.qlnus.menu.VerifyMenu;
import pl.qlnus.services.InventoryService;

public final class PlayerJoinListener implements Listener {
    private final InventoryService inventoryService;
    private final Configuration configuration;

    public PlayerJoinListener(InventoryService inventoryService, Configuration configuration) {
        this.inventoryService = inventoryService;
        this.configuration = configuration;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        inventoryService.addUser(player.getUniqueId());
        switch (configuration.getType()) {
            case "letters" -> {
                LettersVerifyMenu lettersVerifyMenu = new LettersVerifyMenu(inventoryService);
                lettersVerifyMenu.openGui(player);
            }
            case "default" -> {
                VerifyMenu verifyMenu = new VerifyMenu(inventoryService);
                verifyMenu.openGui(player);
            }
        }
    }
}
