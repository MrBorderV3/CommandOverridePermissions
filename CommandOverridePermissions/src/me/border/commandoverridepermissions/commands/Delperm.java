package me.border.commandoverridepermissions.commands;

import me.border.commandoverridepermissions.Main;
import me.border.commandoverridepermissions.utils.CommandsFile;
import me.border.commandoverridepermissions.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Delperm implements CommandExecutor {

    private Main plugin;
    CommandsFile cf = CommandsFile.getInstance();

    public Delperm(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("delperm").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {

        if (sender.hasPermission("mysticempire.delperm")) {
            if (args.length == 1) {
                    if (cf.getData().getStringList("Commands").contains(args[0].toLowerCase())) {
                        List<String> commands = cf.getData().getStringList("Commands");
                        commands.remove(args[0].toLowerCase());
                        cf.getData().set("Commands", null);
                        cf.getData().set("Commands", commands);
                        cf.getData().set(args[0].toLowerCase(), null);
                        cf.saveData();
                        sender.sendMessage(Utils.ucs("Delperm.success").replaceAll("%command%", args[0].toLowerCase()));
                        return true;
                    } else {
                        sender.sendMessage(Utils.ucs("Delperm.delnull"));
                    }
            } else {
                sender.sendMessage(Utils.ucs("Delperm.correct_usage"));
            }
        } else {
            sender.sendMessage(Utils.ucs("noPermission"));
        }
        return false;
    }
}