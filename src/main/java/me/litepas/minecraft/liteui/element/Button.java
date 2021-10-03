package me.litepas.minecraft.liteui.element;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Button extends Element{
    private ItemStack icon;
    private Runnable action;

    public Button(int x, int y, ItemStack icon, Runnable action) {
        super(x, y, false, true);
        this.icon = icon;
        this.action = action;

        addItemPickHandler(action::run);
    }

    public Button(int x, int y, Material material, String name, Runnable action) {
        super(x, y, false, true);

        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name));
        item.setItemMeta(meta);
        this.icon = item;

        this.action = action;
        addItemPickHandler(action::run);
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