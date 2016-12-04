package me.slacktooth.cmdblocker;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CmdBlocker extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("cmdblocker.override")) {
            return;
        }
        String command = event.getMessage().toLowerCase();
        for (String blacklist : this.getConfig().getStringList("blocked")) {
            if (command.contains(blacklist)) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.DARK_RED + "You do not have permission to perform this command.");
            }
        }
    }
}
