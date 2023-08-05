package pl.qlnus.menu;

import me.cocos.gui.builder.gui.GuiBuilder;
import me.cocos.gui.builder.item.impl.ItemBuilder;
import me.cocos.gui.builder.item.impl.SkullBuilder;
import me.cocos.gui.gui.Gui;
import me.cocos.gui.helper.ChatHelper;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import pl.qlnus.services.InventoryService;
import pl.qlnus.utils.RandomUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public final class LettersVerifyMenu {


    private final InventoryService inventoryService;
    private final Map<Character, String> letters;
    private final String LETTERS;
    int[] slots = {9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};

    public LettersVerifyMenu(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.letters = new HashMap<>();
        this.LETTERS = "ABCDEFGHI";
        letters.put('A', "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGYxMGMzMTUxMGFlYTYwODQ3ODBkMDhjODg2NmE4MjA4YWQyYjBjOGE4NzU3MTAyMzAwM2Y4YTNhNGI2MDRhNyJ9fX0="); //a
        letters.put('B', "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2UzODcxMjVhZjMzOWUxM2QwZTg0NWU0YWUxNjhhZDUzYWU2NTBhYTliOWZlYjcwOGExZjMwMTZjNmYwMzBkZSJ9fX0="); //b
        letters.put('C', "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2YyMjVjYjhkZWQ4ZmQ2MDNhODk4NThlN2NlMmYwMzlkMzZlZjM3ODU5NmQxMmYwOGI2YTgxNjQwYmY1In19fQ=="); //c
        letters.put('D', "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDc4NjM4MTZlODI0MTllZjE2NmY3Mzc1Y2NiOTM1YzViMzJiYTM3MDhjM2EzODg3OGNlMTEwM2I5NTI4YTE2MCJ9fX0="); //d
        letters.put('E', "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmZhNWUyOGJiODZiZjliMTQ4MDIyZWYzZmVhZDY5Mjg4YWUzZjFhOTJhMzhhNWRiNjI3YTdiMjQ2M2UyYWNjNSJ9fX0="); //e
        letters.put('F', "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDBlNzQ3ODExNTU0YmIzNmEzMjY0MDc4NGFjMTNkM2NiNTM1ODMyYmYwYjI3ZDhkMmQwZjYwMWEyNzQ4N2Q2MyJ9fX0="); //f
        letters.put('G', "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzZkNGU0YTg2MTMxMjM4OWNhNGVlNzVlNjQwMDQ1ZjgyMDE0OTRhMGI1MTdmMTM4NWIyOTc3MWJiOWY1MTI5In19fQ=="); //g
        letters.put('H', "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWVkZTM1Njg2MTAwYWIxN2FkYTIzMjY5YWU3MTNiMWMwYzRhMWQzMjQ5ZjZiOTVjOTBlMjI1ZmVlMTc0MGY1OSJ9fX0="); //h
        letters.put('I', "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjNhYWY2ZjI3MGQxMmU3NmVjZTM2YjM3OTZlN2NlNzUyOTA0NWRhOWU1NzQzNjhiYmFjYWE5ZWI3OWM1MmIwYyJ9fX0="); //i
    }

    public void openGui(Player player) {
        char index = LETTERS.charAt(RandomUtil.getRandom(LETTERS.length()));
        Gui gui = GuiBuilder.normal("&8x &aWeryfikacja: " + index, 4).disposable(true).build();
        gui.setBlockPlayerInventory(true);
        gui.setItem(22, ItemBuilder.from(Material.PAPER).name("&7Twoja literka to: &a" + index).lore(" ", "&7Kliknij literke &a" + index + " &7wyzej aby &aprzejsc &2weryfikacje", " ").asGuiItem());
        int i = 0;
        for (int slot : slots) {
            gui.setItem(slot, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).name(" ").asGuiItem());
        }
        for (Map.Entry<Character, String> entry : letters.entrySet()) {
            gui.setItem(i, SkullBuilder.fromTexture(entry.getValue()).name("&2" + entry.getKey().toString()).asGuiItem().onClick((event, p) -> {
                event.setCancelled(true);
                if (index != entry.getKey()) {
                    p.kickPlayer(ChatHelper.colored("&cWystapil blad podczas weryfikacji!"));
                } else {
                    inventoryService.removeUser(p.getUniqueId());
                    p.sendTitle(ChatHelper.colored("&aWeryfikacja!"), ChatHelper.colored("&aWeryfikacja przebiegla pomyslnie!"), 15, 20, 15);
                    p.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                    p.closeInventory();
                }
            }));
            i++;
        }
        gui.setOnClick((inventoryClickEvent, p) -> inventoryClickEvent.setCancelled(true));
        gui.setOnClose((event, p) -> {
            if (!inventoryService.containsUser(p.getUniqueId())) return;
            inventoryService.removeUser(p.getUniqueId());
            p.kickPlayer(ChatHelper.colored("&cWystapil blad podczas weryfikacji!"));
        });
        gui.open(player);
    }
}
