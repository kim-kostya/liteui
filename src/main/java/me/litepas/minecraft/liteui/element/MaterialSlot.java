package me.litepas.minecraft.liteui.element;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MaterialSlot extends Element{
    private Material material;

    public MaterialSlot(int x, int y, Material defaultMaterial) {
        super(x, y, false, true);
        material = defaultMaterial;

        addItemPickHandler(() -> material = Material.AIR);
        addItemPutHandler((item -> material = item.getType()));
    }

    public MaterialSlot(int x, int y) {
        this(x, y, Material.AIR);
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public ItemStack render() {
        return new ItemStack(material, 1);
    }
}
