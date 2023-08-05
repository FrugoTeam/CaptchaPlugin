package pl.qlnus.menu;

import me.cocos.gui.builder.gui.GuiBuilder;
import me.cocos.gui.builder.item.impl.ItemBuilder;
import me.cocos.gui.data.GuiItem;
import me.cocos.gui.gui.Gui;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.qlnus.Main;
import pl.qlnus.language.LanguageContainer;
import pl.qlnus.services.InventoryService;
import pl.qlnus.utils.ChatUtil;
import pl.qlnus.utils.RandomUtil;

public final class VerifyMenu {

    private final InventoryService inventoryService;
    private static final Material[] materials = new Material[]{
            Material.LIME_DYE,
            Material.LIME_CONCRETE_POWDER,
            Material.LIME_STAINED_GLASS_PANE,
            Material.LIME_WOOL,
            Material.LIME_BANNER,
            Material.SLIME_BALL,
            Material.SLIME_BLOCK
    };

    public VerifyMenu(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void openGui(Player player) {
        Gui gui = GuiBuilder.normal(LanguageContainer.translate("default-gui-name", String.class), 6).disposable(true).build();
        gui.setBlockPlayerInventory(true);
        for (int i = 0; i < 54; i++) {
            gui.setItem(gui.getInventory().firstEmpty(), ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).name(" ").asGuiItem());
        }
        ItemStack itemStack = LanguageContainer.translate("default-gui", ItemStack.class);
        itemStack.setType(materials[RandomUtil.getRandom(0, materials.length)]);
        gui.setItem(RandomUtil.getRandom(0, 54), GuiItem.of(itemStack).onClick((inventoryClickEvent, p) -> {
            inventoryClickEvent.setCancelled(true);
            inventoryService.removeUser(player.getUniqueId());
            //player.showTitle(defaultLetterConfiguration.getTitle());
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
            gui.setItem(inventoryClickEvent.getSlot(), GuiItem.of(LanguageContainer.translate("barrier", ItemStack.class)));
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> player.closeInventory(), 15);
        }));
        gui.setOnClose((event, p) -> {
            if (!inventoryService.containsUser(p.getUniqueId())) return;
            inventoryService.removeUser(p.getUniqueId());
            p.kick(ChatUtil.colored(LanguageContainer.translate("kick", String.class)));
        });
        gui.setOnClick((inventoryClickEvent, p) -> inventoryClickEvent.setCancelled(true));
        gui.open(player);
    }
}
