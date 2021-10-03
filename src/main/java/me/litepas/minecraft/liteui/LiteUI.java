package me.litepas.minecraft.liteui;

import me.litepas.minecraft.liteui.form.Form;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class LiteUI extends JavaPlugin {

    public static HashMap<Player, Form> FORM_MAP = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
