package me.litepas.minecraft.liteui.handler;

import me.litepas.minecraft.liteui.LiteUI;
import me.litepas.minecraft.liteui.element.Element;
import me.litepas.minecraft.liteui.form.Form;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;

public class InventoryHandler implements Listener {

    private static final InventoryAction[] CANCELABLE_ACTIONS = {
            InventoryAction.MOVE_TO_OTHER_INVENTORY,
            InventoryAction.CLONE_STACK,
            InventoryAction.COLLECT_TO_CURSOR,
            InventoryAction.DROP_ALL_CURSOR,
            InventoryAction.DROP_ALL_SLOT,
            InventoryAction.DROP_ALL_CURSOR,
            InventoryAction.DROP_ONE_CURSOR,
            InventoryAction.HOTBAR_SWAP,
            InventoryAction.HOTBAR_MOVE_AND_READD,
            InventoryAction.PICKUP_ALL,
            InventoryAction.PICKUP_HALF,
            InventoryAction.PICKUP_ONE,
            InventoryAction.PICKUP_ONE,
            InventoryAction.PLACE_ALL,
            InventoryAction.PLACE_ONE,
            InventoryAction.PLACE_SOME
    };

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryAction action = event.getAction();
        Player player = (Player) event.getWhoClicked();
        int index = event.getSlot();
        if(LiteUI.FORM_MAP.containsKey(player)) {
            Form form = LiteUI.FORM_MAP.get(player);
            
            if (event.getClickedInventory() != player.getInventory()) {
                form.get(index).ifPresentOrElse(
                    element -> {
                        if (element.canPick() && Arrays.stream(CANCELABLE_ACTIONS).toList().contains(action)) {
                            event.setCancelled(true);
                        }
                    },
                    () -> event.setCancelled(true)
                );
                
            }
        }
    }

}
