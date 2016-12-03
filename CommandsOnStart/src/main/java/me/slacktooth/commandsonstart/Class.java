package me.slacktooth.commandsonstart;

import org.bukkit.plugin.java.JavaPlugin;

public class Class extends JavaPlugin {

    @Override
    public void onEnable() {
        for (String commands : this.getConfig().getStringList("commands")) {
            this.getServer().dispatchCommand(this.getServer().getConsoleSender(), commands);
        }
    }
}
