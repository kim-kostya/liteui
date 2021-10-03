package me.litepas.minecraft.liteui.form;

import me.litepas.minecraft.liteui.element.Element;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Optional;

public abstract class Form {

    private final Player player;

    public Form(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public abstract Optional<Element> get(int index);

    public abstract Inventory render();

    public void open() {

    }

    public void update() {

    }
}
