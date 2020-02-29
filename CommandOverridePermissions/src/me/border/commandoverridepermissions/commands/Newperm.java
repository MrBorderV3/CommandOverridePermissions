package me.border.commandoverridepermissions.commands;

import me.border.commandoverridepermissions.Main;
import me.border.commandoverridepermissions.utils.CommandsFile;
import me.border.commandoverridepermissions.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Newperm implements CommandExecutor {

    private Main plugin;
    CommandsFile cf = CommandsFile.getInstance();

    public Newperm(Main plugin){
        this.plugin = plugin;

        plugin.getCommand("newperm").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender,final Command cmd,final String label,final String[] args) {

        if (sender.hasPermission("mysticempire.newperm")) {
            if (args.length == 2) {
                if (cf.getData().getStringList("Commands") == null) {
                    cf.getData().set("Commands", "nullstring1");
                } else {
                    List<String> commands = cf.getData().getStringList("Commands");
                    if (commands.contains(args[0].toLowerCase())){
                        sender.sendMessage(Utils.ucs("Newperm.newnull"));
                        return true;
                    }
                    commands.add(args[0].toLowerCase());
                    cf.getData().set("Commands", commands);
                    cf.getData().set(args[0], args[1].toLowerCase());
                    cf.saveData();
                    sender.sendMessage(Utils.ucs("Newperm.success").replaceAll("%command%", args[0]).replaceAll("%permission%", args[1]));
                }
            } else {
                sender.sendMessage(Utils.ucs("Newperm.correct_usage"));
            }
        } else {
            sender.sendMessage(Utils.ucs("noPermission"));
        }
            return false;
        }
}
