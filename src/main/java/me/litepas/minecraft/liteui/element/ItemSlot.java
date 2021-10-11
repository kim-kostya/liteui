package me.litepas.minecraft.liteui.element;

import org.bukkit.inventory.ItemStack;

public class ItemSlot extends Element {
    private ItemStack item;

    public ItemSlot(int x, int y, ItemStack defaultItem) {
        super(x, y, true, false);
        item = defaultItem;

        addItemPickHandler(() -> item = null);
        addItemPutHandler((item) -> this.item = item);
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
        this.getParent().update();
    }

    @Override
    public ItemStack render() {
        return item;
    }
}
