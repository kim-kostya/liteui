package me.litepas.minecraft.liteui.form;

import me.litepas.minecraft.liteui.LiteUI;
import me.litepas.minecraft.liteui.background.IBackgroundFactory;
import me.litepas.minecraft.liteui.element.Element;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Optional;

public abstract class Form {

    private final Player player;
    private Inventory inventory;
    private IBackgroundFactory backgroundFactory;

    public Form(Player player, Inventory inventory) {
        this.inventory = inventory;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public abstract Optional<Element> get(int index);

    public abstract Inventory render();

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public IBackgroundFactory getBackgroundFactory() {
        return backgroundFactory;
    }

    public void setBackgroundFactory(IBackgroundFactory backgroundFactory) {
        this.backgroundFactory = backgroundFactory;
    }

    public void onUpdate() {}

    public void onOpen() {}

    public void onClose() {}

    public void open() {
        LiteUI.FORM_MAP.replace(player, this);
        onOpen();
        update();
        player.openInventory(inventory);
    }

    public void update() {
        for (int index = 0; index < inventory.getSize(); index++) {
            int x = index % 9;
            int y = index / 9;

            inventory.setItem(index, backgroundFactory.generateCell(x, y, inventory.getSize()));
        }
        onUpdate();
        render();
    }

    public void close() {
        LiteUI.FORM_MAP.replace(player, null);
        onClose();
        inventory.close();
    }
}
