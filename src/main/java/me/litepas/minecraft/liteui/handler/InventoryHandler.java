package me.litepas.minecraft.liteui.handler;

import me.litepas.minecraft.liteui.LiteUI;
import me.litepas.minecraft.liteui.form.Form;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class InventoryHandler implements Listener {

    private static final InventoryAction[] CANCELABLE_ACTIONS = {
            InventoryAction.MOVE_TO_OTHER_INVENTORY,
            InventoryAction.CLONE_STACK,
            InventoryAction.COLLECT_TO_CURSOR,
            InventoryAction.HOTBAR_SWAP,
            InventoryAction.HOTBAR_MOVE_AND_READD
    };

    private static final InventoryAction[] PICKUP_ACTIONS = {
            InventoryAction.PICKUP_ONE,
            InventoryAction.PICKUP_HALF,
            InventoryAction.PICKUP_ALL,
            InventoryAction.PICKUP_SOME
    };

    private static final InventoryAction[] PUT_ACTIONS = {
            InventoryAction.PLACE_SOME,
            InventoryAction.PLACE_ONE,
            InventoryAction.PLACE_ALL,
            InventoryAction.SWAP_WITH_CURSOR
    };

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryAction action = event.getAction();
        Player player = (Player) event.getWhoClicked();
        int index = event.getSlot();
        if(LiteUI.FORM_MAP.containsKey(player)) {
            Form form = LiteUI.FORM_MAP.get(player);

            if (Arrays.stream(CANCELABLE_ACTIONS).toList().contains(action)) {
                event.setCancelled(true);
                return;
            }

            if (event.getClickedInventory() == form.getInventory()) {
                form.get(index).ifPresentOrElse(
                    element -> {
                        if (!element.canPick()) {
                            event.setCancelled(true);
                        }

                        ItemStack item = event.getCursor();

                        if (Arrays.stream(PICKUP_ACTIONS).toList().contains(action)) {
                            element.pickItem();
                        } else if (Arrays.stream(PUT_ACTIONS).toList().contains(action)) {
                            element.putItem(item);
                        }
                    },
                    () -> event.setCancelled(true)
                );
                
            }
        }
    }

}
