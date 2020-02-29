package me.border.commandoverridepermissions.listeners;

import me.border.commandoverridepermissions.Main;
import me.border.commandoverridepermissions.utils.CommandsFile;
import me.border.commandoverridepermissions.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.security.Permission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandEvents implements Listener {

    private Main plugin;

    public CommandEvents(Main plugin) {
        this.plugin = plugin;
    }

    CommandsFile cf = CommandsFile.getInstance();

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        List<String> commands = cf.getData().getStringList("Commands");
        Player p = e.getPlayer();
        String[] commandargs = e.getMessage().toLowerCase().replaceAll("/", "").split(" ");
        if (commands.contains(commandargs[0].toLowerCase())) {
            String command = commandargs[0].replaceAll("/", "");
            String permission = cf.getData().getString(command);
            if (permission == null) {
                return;
            }
            if (p.hasPermission(permission)) {
                return;
            }
            e.setCancelled(true);
            p.sendMessage(Utils.ucs("noPermission"));
        } else {
            return;
        }
    }
}
