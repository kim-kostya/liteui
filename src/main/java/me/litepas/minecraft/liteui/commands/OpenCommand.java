package me.litepas.minecraft.liteui.commands;

import me.litepas.minecraft.liteui.LiteUI;
import me.litepas.minecraft.liteui.debug.DebugForm;
import me.litepas.minecraft.liteui.form.Form;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class OpenCommand implements CommandExecutor {

    private final Class<? extends Form> formClass;

    public OpenCommand(Class<? extends Form> formClass) {
        this.formClass = formClass;
    }

    public Class<? extends Form> getFormClass() {
        return formClass;
    }

    public String constructorToString(Class clazz, Class... args) {
        StringBuilder builder = new StringBuilder();

        builder.append(clazz.getName()).append("(");
        for (int i = 0; i < args.length; i++) {
            Class argClazz = args[i];
            builder.append(argClazz.getName());

            if (i < args.length - 1) {
                builder.append(", ");
            }
        }
        builder.append(")");

        return builder.toString();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            try {
                Form form = formClass.getConstructor(Player.class).newInstance(sender);
                form.open();
            }
            /*
            * Print error message in console and chat(if debug mode is on)
            */
            catch (InstantiationException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                String errorMessage = constructorToString(formClass, Player.class) + " is private.";

                if (LiteUI.DEBUG_MODE) {
                    sender.sendMessage(errorMessage);
                }

                LiteUI.LOGGER.severe(errorMessage);
                e.printStackTrace();
            }
            catch (InvocationTargetException e)
            {
                LiteUI.LOGGER.severe("Invalid argument.");

                if (LiteUI.DEBUG_MODE) {
                    sender.sendMessage("Invalid argument.");
                }

                e.printStackTrace();
            }
            catch (NoSuchMethodException e)
            {
                String errorMessage = constructorToString(formClass, Player.class) + " is not found.";

                if (LiteUI.DEBUG_MODE) {
                    sender.sendMessage(errorMessage);
                }

                LiteUI.LOGGER.severe(errorMessage);
                e.printStackTrace();
            }
        }

        return false;
    }
}
