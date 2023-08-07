package pl.qlnus.menu;

import me.cocos.gui.builder.gui.GuiBuilder;
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

public final class ColorVerifyMenu {

    private final InventoryService inventoryService;
    private final Map<String, ItemStack> letters;
    private final String[] COLORS = {"red", "green", "blue", "yellow"};
    private final int[] slots = {10, 16, 28, 34};
    private int slotsIndex = 0;

    public ColorVerifyMenu(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.letters = new HashMap<>();
        letters.put("red", SkullBuilder.fromTexture("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTlkYThjMDY3NzkyMzZjZDFmMWFmMjA2N2ZhZTRhNDI3ZjY2ZjAwOTc1NzAyMDc3NjM2N2I4M2VmMGRlMTY4YyJ9fX0=").name(" ").build());
        letters.put("green", SkullBuilder.fromTexture("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODZjMmVhMDA2ZmU2ODUyM2Q2YWRmNWJmY2U2ZWQwODZjZjgxNGM1YjA5MjYzMGU5NDI0YWFiNDUxYzJiMDNiYSJ9fX0=").name(" ").build());
        letters.put("yellow", SkullBuilder.fromTexture("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODhlMzMwNDM3NTViZjgxM2E2YjVkNDNkMmJkYzYwYzZiOTBmODBkYjE0OWM3OWFiZmQwODQwYWFkZDNjNTMxIn19fQ==").name(" ").build());
        letters.put("blue", SkullBuilder.fromTexture("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTAxMTA3Njk1ODRjNjI0OWEyZGU3ZGI3MWE1OGE5Nzg5YWFiODU1M2QyYzVjYjBkYjEwMzM4YzUzZDZlNjg5In19fQ==").name(" ").build());
    }

    public void openGui(Player player) {
        int index = RandomUtil.getRandom(COLORS.length);
        Gui gui = GuiBuilder.normal(LanguageContainer.translate("color-gui-name", String.class), 5).disposable(true).build();
        gui.setBlockPlayerInventory(true);
        ItemStack itemStack = LanguageContainer.translate("color-gui", ItemStack.class).clone();
        itemStack.setType(Material.valueOf(COLORS[index].toUpperCase() + "_WOOL"));
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(Objects.requireNonNull(meta.getLore()).stream()
                .map(line -> ChatHelper.colored(line.replace("%color%", LanguageContainer.translate(COLORS[index], String.class))))
                .toList());
        itemStack.setItemMeta(meta);
        gui.setItem(22, GuiItem.of(itemStack));
        for (Map.Entry<String, ItemStack> entry : letters.entrySet()) {
            if (slotsIndex < slots.length) {
                int slot = slots[slotsIndex];
                gui.setItem(slot, GuiItem.of(entry.getValue()).onClick((event, p) -> {
                    if (!entry.getKey().equalsIgnoreCase(COLORS[index])) {
                        p.kickPlayer(ChatHelper.colored(LanguageContainer.translate("kick", String.class)));
                    } else {
                        inventoryService.removeUser(p.getUniqueId());
                        p.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                        gui.setItem(event.getSlot(), GuiItem.of(LanguageContainer.translate("barrier", ItemStack.class)));
                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), player::closeInventory, 15);
                    }
                }));
                slotsIndex++;
            }
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
