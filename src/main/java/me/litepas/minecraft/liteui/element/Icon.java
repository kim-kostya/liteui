package me.litepas.minecraft.liteui.element;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Icon extends Element{
    private ItemStack icon;

    public Icon(int x, int y, ItemStack icon) {
        super(x, y, false, false);
        this.icon = icon;
    }

    public Icon(int x, int y, Material material, String name) {
        super(x, y, false, false);
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name));
        item.setItemMeta(meta);
        this.icon = item;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }

    @Override
    public ItemStack render() {
        return icon;
    }
}
