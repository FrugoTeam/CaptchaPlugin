package pl.qlnus.menu;

import me.cocos.gui.builder.gui.GuiBuilder;
import me.cocos.gui.builder.item.impl.SkullBuilder;
import me.cocos.gui.data.GuiItem;
import me.cocos.gui.gui.Gui;
import me.cocos.gui.helper.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.qlnus.Main;
import pl.qlnus.language.LanguageContainer;
import pl.qlnus.services.InventoryService;
import pl.qlnus.utils.RandomUtil;

import java.util.List;

public final class VerifyMenu {

    private final InventoryService inventoryService;

    public VerifyMenu(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void openGui(Player player) {
        Gui gui = GuiBuilder.normal(LanguageContainer.translate("default-gui-name", String.class), 6).disposable(true).build();
        gui.setBlockPlayerInventory(true);
        for (int i = 0; i < 54; i++) {
            gui.setItem(gui.getInventory().firstEmpty(), SkullBuilder.fromTexture("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2IxYTM1MjhjZDAwMmEwNmFmOTk1MzI1NTIwYjUwZDViZjk2ZDcwZDZiZjRiMmFlM2VjNzNjNWRmNTZkYjI0In19fQ==").name(" ").asGuiItem().onClick((inventoryClickEvent, p) -> {
                p.closeInventory();
            }));
        }
        gui.setItem(RandomUtil.getRandom(0, 54), SkullBuilder.fromTexture("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODZjMmVhMDA2ZmU2ODUyM2Q2YWRmNWJmY2U2ZWQwODZjZjgxNGM1YjA5MjYzMGU5NDI0YWFiNDUxYzJiMDNiYSJ9fX0=").name(" ").lore(LanguageContainer.translate("default-gui", List.class)).asGuiItem().onClick((inventoryClickEvent, p) -> {
            inventoryClickEvent.setCancelled(true);
            inventoryService.removeUser(player.getUniqueId());
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
            gui.setItem(inventoryClickEvent.getSlot(), GuiItem.of(LanguageContainer.translate("barrier", ItemStack.class)));
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), player::closeInventory, 15);
        }));
        gui.setOnClose((event, p) -> {
            if (!inventoryService.containsUser(p.getUniqueId())) return;
            inventoryService.removeUser(p.getUniqueId());
            p.kickPlayer(ChatHelper.colored(LanguageContainer.translate("kick", String.class)));
        });
        gui.setOnClick((inventoryClickEvent, p) -> inventoryClickEvent.setCancelled(true));
        gui.open(player);
    }
}
