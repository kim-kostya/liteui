package me.litepas.minecraft.liteui;

import me.litepas.minecraft.liteui.commands.OpenCommand;
import me.litepas.minecraft.liteui.debug.DebugForm;
import me.litepas.minecraft.liteui.form.Form;
import me.litepas.minecraft.liteui.handler.InventoryHandler;
import me.litepas.minecraft.liteui.handler.PlayerConnectionHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class LiteUI extends JavaPlugin {

    public static HashMap<Player, Form> FORM_MAP = new HashMap<>();

    public static Logger LOGGER;

    public static final boolean DEBUG_MODE = true;

    @Override
    public void onEnable() {
        LOGGER = getLogger();

        if (DEBUG_MODE) {
            LOGGER.setLevel(Level.ALL);
            getCommand("debug-menu").setExecutor(new OpenCommand(DebugForm.class));
            LOGGER.config(">>DEBUG MODE<<");
        }

        getServer().getPluginManager().registerEvents(new InventoryHandler(), this);
        getServer().getPluginManager().registerEvents(new PlayerConnectionHandler(), this);
    }

    @Override
    public void onDisable() {}
}
