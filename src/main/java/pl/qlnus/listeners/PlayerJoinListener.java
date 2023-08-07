package pl.qlnus.listeners;

import me.cocos.gui.helper.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.qlnus.Main;
import pl.qlnus.configuration.Configuration;
import pl.qlnus.language.LanguageContainer;
import pl.qlnus.menu.ColorVerifyMenu;
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
        new BukkitRunnable() {
            @Override
            public void run() {
                if (inventoryService.containsUser(player.getUniqueId())) {
                    Bukkit.getScheduler().runTask(Main.getInstance(), () -> player.kickPlayer(ChatHelper.colored(LanguageContainer.translate("kick", String.class))));
                    inventoryService.removeUser(player.getUniqueId());
                }
            }
        }.runTaskLaterAsynchronously(Main.getInstance(), 20 * 30);
        switch (configuration.type) {
            case LETTERS -> {
                LettersVerifyMenu lettersVerifyMenu = new LettersVerifyMenu(inventoryService);
                lettersVerifyMenu.openGui(player);
            }
            case DEFAULT -> {
                VerifyMenu verifyMenu = new VerifyMenu(inventoryService);
                verifyMenu.openGui(player);
            }
            case COLORS -> {
                ColorVerifyMenu colorVerifyMenu = new ColorVerifyMenu(inventoryService);
                colorVerifyMenu.openGui(player);
            }
        }
    }
}
