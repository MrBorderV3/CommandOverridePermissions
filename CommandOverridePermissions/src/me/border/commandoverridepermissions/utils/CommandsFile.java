package me.border.commandoverridepermissions.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class CommandsFile {
    static CommandsFile instance = new CommandsFile();
    Plugin plugin;
    FileConfiguration data;
    public static File cfile;

    public static CommandsFile getInstance(){
        return instance;
    }

    public void setup(Plugin plugin){
        if (!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }
        File path = new File(plugin.getDataFolder() + File.separator + "/data");
        cfile = new File(path, File.separator + "commands.yml");
        if (!cfile.exists()){
            try{
                path.mkdirs();
                cfile.createNewFile();
            }catch (IOException e){
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create commands.yml");
            }
        }
        this.data = YamlConfiguration.loadConfiguration(cfile);
    }

    public FileConfiguration getData(){
        return this.data;
    }

    public void saveData(){
        try{
            this.data.save(cfile);
        } catch(IOException e){
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save commands.yml");
        }
    }

    public void reloadData(){
        this.data = YamlConfiguration.loadConfiguration(cfile);
    }
}

