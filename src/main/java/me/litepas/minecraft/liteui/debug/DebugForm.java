package me.litepas.minecraft.liteui.debug;

import me.litepas.minecraft.liteui.element.Icon;
import me.litepas.minecraft.liteui.element.MaterialSlot;
import me.litepas.minecraft.liteui.form.SimpleForm;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class DebugForm extends SimpleForm {
    public DebugForm(Player player) {
        super(player, Component.text("<Debug Menu>"), 1);
        add(new MaterialSlot(0, 0, Material.TNT));
    }
}
