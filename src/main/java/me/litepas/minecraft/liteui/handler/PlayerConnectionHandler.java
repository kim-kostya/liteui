package me.litepas.minecraft.liteui.handler;

import com.destroystokyo.paper.event.player.PlayerConnectionCloseEvent;
import me.litepas.minecraft.liteui.LiteUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionHandler implements Listener {

    @EventHandler()
    public void onPlayerJoin(PlayerJoinEvent event) {
        LiteUI.FORM_MAP.put(event.getPlayer(), null);
    }


    @EventHandler()
    public void onPlayerQuit(PlayerQuitEvent event) {
        LiteUI.FORM_MAP.remove(event.getPlayer());
    }
}
