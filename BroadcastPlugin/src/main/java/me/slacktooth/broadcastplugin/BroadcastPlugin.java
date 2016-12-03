package me.slacktooth.broadcastplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BroadcastPlugin extends JavaPlugin {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && !sender.hasPermission("broadcastplugin.use")) {
            return false;
        }
        String message = args[0];
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
        this.getServer().getLogger().info("Message: " + message);
        this.getServer().getLogger().info("Broadcasted by: " + sender.getName());
        return false;
    }
}
