package me.litepas.minecraft.liteui.background;

import org.bukkit.inventory.ItemStack;

public interface IBackgroundFactory {

    ItemStack generateCell(int x, int y, int size);

}
