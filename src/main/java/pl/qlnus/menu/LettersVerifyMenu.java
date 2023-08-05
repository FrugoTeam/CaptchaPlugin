package pl.qlnus.menu;

import me.cocos.gui.builder.gui.GuiBuilder;
import me.cocos.gui.builder.item.impl.ItemBuilder;
import me.cocos.gui.builder.item.impl.SkullBuilder;
import me.cocos.gui.data.GuiItem;
import me.cocos.gui.gui.Gui;
import me.cocos.gui.helper.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.qlnus.Main;
import pl.qlnus.language.LanguageContainer;
import pl.qlnus.services.InventoryService;
import pl.qlnus.utils.RandomUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class LettersVerifyMenu {


    private final InventoryService inventoryService;
    private final Map<Character, ItemStack> letters;
    private final String LETTERS;
    private final int[] slots = {9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};

    public LettersVerifyMenu(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.letters = new HashMap<>();
        this.LETTERS = "ABCDEFGHI";
        letters.put('A', SkullBuilder.fromTexture("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGYxMGMzMTUxMGFlYTYwODQ3ODBkMDhjODg2NmE4MjA4YWQyYjBjOGE4NzU3MTAyMzAwM2Y4YTNhNGI2MDRhNyJ9fX0=").name("&2A").build()); //a
        letters.put('B', SkullBuilder.fromTexture("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2UzODcxMjVhZjMzOWUxM2QwZTg0NWU0YWUxNjhhZDUzYWU2NTBhYTliOWZlYjcwOGExZjMwMTZjNmYwMzBkZSJ9fX0=").name("&2B").build()); //b
        letters.put('C', SkullBuilder.fromTexture("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2YyMjVjYjhkZWQ4ZmQ2MDNhODk4NThlN2NlMmYwMzlkMzZlZjM3ODU5NmQxMmYwOGI2YTgxNjQwYmY1In19fQ==").name("&2C").build()); //c
        letters.put('D', SkullBuilder.fromTexture("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDc4NjM4MTZlODI0MTllZjE2NmY3Mzc1Y2NiOTM1YzViMzJiYTM3MDhjM2EzODg3OGNlMTEwM2I5NTI4YTE2MCJ9fX0=").name("&2D").build()); //d
        letters.put('E', SkullBuilder.fromTexture("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmZhNWUyOGJiODZiZjliMTQ4MDIyZWYzZmVhZDY5Mjg4YWUzZjFhOTJhMzhhNWRiNjI3YTdiMjQ2M2UyYWNjNSJ9fX0=").name("&2E").build()); //e
        letters.put('F', SkullBuilder.fromTexture("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDBlNzQ3ODExNTU0YmIzNmEzMjY0MDc4NGFjMTNkM2NiNTM1ODMyYmYwYjI3ZDhkMmQwZjYwMWEyNzQ4N2Q2MyJ9fX0=").name("&2F").build()); //f
        letters.put('G', SkullBuilder.fromTexture("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzZkNGU0YTg2MTMxMjM4OWNhNGVlNzVlNjQwMDQ1ZjgyMDE0OTRhMGI1MTdmMTM4NWIyOTc3MWJiOWY1MTI5In19fQ==").name("&2G").build()); //g
        letters.put('H', SkullBuilder.fromTexture("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWVkZTM1Njg2MTAwYWIxN2FkYTIzMjY5YWU3MTNiMWMwYzRhMWQzMjQ5ZjZiOTVjOTBlMjI1ZmVlMTc0MGY1OSJ9fX0=").name("&2H").build()); //h
        letters.put('I', SkullBuilder.fromTexture("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjNhYWY2ZjI3MGQxMmU3NmVjZTM2YjM3OTZlN2NlNzUyOTA0NWRhOWU1NzQzNjhiYmFjYWE5ZWI3OWM1MmIwYyJ9fX0=").name("&2I").build()); //i
    }

    public void openGui(Player player) {
        char index = LETTERS.charAt(RandomUtil.getRandom(LETTERS.length()));
        Gui gui = GuiBuilder.normal(LanguageContainer.translate("letters-gui-name", String.class).replaceAll("%letter%", String.valueOf(index)), 4).disposable(true).build();
        gui.setBlockPlayerInventory(true);
        ItemStack itemStack = LanguageContainer.translate("letters-gui", ItemStack.class).clone();
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(Objects.requireNonNull(meta.getLore()).stream()
                .map(line -> line.replace("%letter%", String.valueOf(index)))
                .toList());
        itemStack.setItemMeta(meta);
        gui.setItem(22, GuiItem.of(itemStack));
        for (int slot : slots) {
            gui.setItem(slot, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).name(" ").asGuiItem());
        }
        for (Map.Entry<Character, ItemStack> entry : letters.entrySet()) {
            gui.setItem(gui.getInventory().firstEmpty(), GuiItem.of(entry.getValue()).onClick((event, p) -> {
                event.setCancelled(true);
                if (index != entry.getKey()) {
                    p.kickPlayer(ChatHelper.colored(LanguageContainer.translate("kick", String.class)));
                } else {
                    inventoryService.removeUser(p.getUniqueId());
                    p.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                    gui.setItem(event.getSlot(), GuiItem.of(LanguageContainer.translate("barrier", ItemStack.class)));
                    Bukkit.getScheduler().runTaskLater(Main.getInstance(), player::closeInventory, 15);
                }
            }));
        }
        gui.setOnClick((inventoryClickEvent, p) -> inventoryClickEvent.setCancelled(true));
        gui.setOnClose((event, p) -> {
            if (!inventoryService.containsUser(p.getUniqueId())) return;
            inventoryService.removeUser(p.getUniqueId());
            p.kickPlayer(ChatHelper.colored(LanguageContainer.translate("kick", String.class)));
        });
        gui.open(player);
    }
}
