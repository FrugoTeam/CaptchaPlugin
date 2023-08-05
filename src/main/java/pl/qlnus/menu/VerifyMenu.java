package pl.qlnus.menu;

import me.cocos.gui.builder.gui.GuiBuilder;
import me.cocos.gui.builder.item.impl.ItemBuilder;
import me.cocos.gui.gui.Gui;
import me.cocos.gui.helper.ChatHelper;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import pl.qlnus.services.InventoryService;
import pl.qlnus.utils.RandomUtil;

import java.util.Arrays;
import java.util.stream.IntStream;

public final class VerifyMenu {

    private final InventoryService inventoryService;
    private final boolean[] verifyStatus = new boolean[2];
    private static final Material[] materials = new Material[]{Material.LIME_DYE, Material.LIME_CONCRETE_POWDER, Material.LIME_STAINED_GLASS_PANE, Material.LIME_WOOL, Material.LIME_BANNER, Material.SLIME_BALL, Material.SLIME_BLOCK};

    public VerifyMenu(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void openGui(Player player) {
        Gui gui = GuiBuilder.normal("&8x &aWeryfikacja", 6).disposable(true).build();
        gui.setBlockPlayerInventory(true);
        for (int i = 0; i < 54; i++) {
            gui.setItem(gui.getInventory().firstEmpty(), ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).name(" ").asGuiItem());
        }
        IntStream.range(0,2).forEach(i -> gui.setItem(RandomUtil.getRandom(0, 54), ItemBuilder.from(materials[RandomUtil.getRandom(0, materials.length)]).name("&aWeryfikacja").lore(" ", "&8>> &aKliknij aby sie zweryfikowac!", "").asGuiItem().onClick((inventoryClickEvent, p) -> {
            inventoryClickEvent.setCancelled(true);
            verifyStatus[i] = true;
            if (verifyStatus[0] && verifyStatus[1]) {
                inventoryService.removeUser(player.getUniqueId());
                player.sendTitle(ChatHelper.colored("&aWeryfikacja!"), ChatHelper.colored("&aWeryfikacja przebiegla pomyslnie!"), 15, 20, 15);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                player.closeInventory();
            }
            gui.setItem(inventoryClickEvent.getSlot(), ItemBuilder.from(Material.BARRIER).name("&cKliknieto").asGuiItem());
        })));
        gui.setOnClose((event, p) -> {
            if (!inventoryService.containsUser(p.getUniqueId())) return;
            inventoryService.removeUser(p.getUniqueId());
            p.kickPlayer(ChatHelper.colored("&cWystapil blad podczas weryfikacji!"));
        });
        gui.setOnClick((inventoryClickEvent, p) -> {
            if (inventoryClickEvent.getCurrentItem() != null && !Arrays.asList(materials).contains(inventoryClickEvent.getCurrentItem().getType())) {
                inventoryClickEvent.setCancelled(true);
                p.closeInventory();
            }
        });
        gui.open(player);
    }
}
