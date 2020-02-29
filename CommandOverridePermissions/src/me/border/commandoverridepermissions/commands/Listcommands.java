package me.border.commandoverridepermissions.commands;

import me.border.commandoverridepermissions.Main;
import me.border.commandoverridepermissions.utils.CommandsFile;
import me.border.commandoverridepermissions.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Listcommands implements CommandExecutor {

    private Main plugin;
    CommandsFile cf = CommandsFile.getInstance();

    public Listcommands(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("listcommands").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {

        if (sender.hasPermission("mysticempire.listcommands")) {
            if (args.length == 0) {
                sender.sendMessage(Utils.chat("&c" + cf.getData().getStringList("Commands").toString().replaceAll("," , "&6,&b").replaceAll("\\[", "&c[&b").replaceAll("]", "&c]")));
                return true;
            } else {
                sender.sendMessage(Utils.ucs("Listpermissions.correct_usage"));
            }
        }
        return false;
    }
}