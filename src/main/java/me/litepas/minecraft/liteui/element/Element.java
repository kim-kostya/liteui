package me.litepas.minecraft.liteui.element;

import me.litepas.minecraft.liteui.form.Form;
import me.litepas.minecraft.liteui.handler.ItemPickHandler;
import me.litepas.minecraft.liteui.handler.ItemPutHandler;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public abstract class Element {
    private int x;
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    private int y;
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    private boolean canPick;
    public boolean canPick() {
        return canPick;
    }
    public void setCanPick(boolean canPick) {
        this.canPick = canPick;
    }

    private boolean canCallUpdate;
    public boolean canCallUpdate() {
        return canCallUpdate;
    }
    public void setCanCallUpdate(boolean canCallUpdate) {
        this.canCallUpdate = canCallUpdate;
    }

    private Form parent;
    public Form getParent() {
        return parent;
    }
    public void setParent(Form parent) {
        this.parent = parent;
    }

    private Set<ItemPickHandler> itemPickHandlers = new HashSet<>();
    private Set<ItemPutHandler> itemPutHandlers = new HashSet<>();

    public Element(int x, int y, boolean canPick, boolean canCallUpdate) {
        this.x = x;
        this.y = y;
        this.canPick = canPick;
        this.canCallUpdate = canCallUpdate;
    }

    public void addItemPutHandler(ItemPutHandler handler) {
        itemPutHandlers.add(handler);
    }

    public void addItemPickHandler(ItemPickHandler handler) {
        itemPickHandlers.add(handler);
    }

    public void putItem(ItemStack item) {
        itemPutHandlers.forEach(handler -> handler.on(item));
        if (canCallUpdate) {
            parent.update();
        }
    }

    public void pickItem() {
        itemPickHandlers.forEach(ItemPickHandler::on);
        if (canCallUpdate) {
            parent.update();
        }
    }
    
    public abstract ItemStack render();
}
