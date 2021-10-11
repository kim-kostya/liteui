package me.litepas.minecraft.liteui.background;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SimpleBackgroundFactory implements IBackgroundFactory {
    @Override
    public ItemStack generateCell(int x, int y, int size) {
        ItemStack icon = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta meta = icon.getItemMeta();
        meta.displayName(Component.text(""));
        icon.setItemMeta(meta);
        return icon;
    }
}
