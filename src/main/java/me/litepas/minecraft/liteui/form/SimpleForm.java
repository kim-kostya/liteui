package me.litepas.minecraft.liteui.form;

import me.litepas.minecraft.liteui.background.SimpleBackgroundFactory;
import me.litepas.minecraft.liteui.element.Element;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SimpleForm extends Form {
    private final Component title;
    private final int size;
    private final Set<Element> elementList = new HashSet<>();

    public SimpleForm(Player player, Component title, int size) {
        super(player, Bukkit.createInventory(player, size * 9, title));
        this.title = title;
        this.size = size;
        setBackgroundFactory(new SimpleBackgroundFactory());
    }

    public void add(Element element) {
        int x = element.getX();
        int y = element.getY();

        if (x >= 9) throw new IndexOutOfBoundsException("X coordinate is too big");
        if (x < 0) throw new IndexOutOfBoundsException("X coordinate is too low");
        if (y >= size) throw new IndexOutOfBoundsException("Y coordinate is too big");
        if (y < 0) throw new IndexOutOfBoundsException("Y coordinate is too low");

        element.setParent(this);

        elementList.add(element);
    }

    public void remove(Element element) {
        elementList.remove(element);
    }

    public Optional<Element> get(int index) {
        int x = index % 9;
        int y = index / 9;

        return elementList.stream().filter(element -> element.getX() == x && element.getY() == y).findFirst();
    }

    public Component getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Inventory render() {
        for (Element element: elementList) {
            int x = element.getX();
            int y = element.getY();

            getInventory().setItem(x + (y * 9), element.render());
        }

        return getInventory();
    }
}
