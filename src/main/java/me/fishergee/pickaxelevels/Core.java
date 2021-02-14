package me.fishergee.pickaxelevels;

import me.fishergee.pickaxelevels.cmds.PickaxeLevelsCmd;
import me.fishergee.pickaxelevels.listeners.BlockBreakListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    @Override
    public void onEnable(){
        registerListeners();
        registerCommands();
    }

    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
    }

    public void registerCommands(){
        getCommand("pickaxelevels").setExecutor(new PickaxeLevelsCmd());
    }
}
