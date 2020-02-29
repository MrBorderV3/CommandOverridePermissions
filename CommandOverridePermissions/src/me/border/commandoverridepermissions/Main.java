package me.border.commandoverridepermissions;

import me.border.commandoverridepermissions.commands.Delperm;
import me.border.commandoverridepermissions.commands.Listcommands;
import me.border.commandoverridepermissions.commands.Newperm;
import me.border.commandoverridepermissions.listeners.CommandEvents;
import me.border.commandoverridepermissions.utils.CommandsFile;
import me.border.commandoverridepermissions.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    CommandsFile cf = CommandsFile.getInstance();

    @Override
    public void onEnable(){
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        cf.setup(this);
        new Listcommands(this);
        new Delperm(this);
        new Utils(this);
        new Newperm(this);
        getServer().getPluginManager().registerEvents(new CommandEvents(this), this);
    }
}
