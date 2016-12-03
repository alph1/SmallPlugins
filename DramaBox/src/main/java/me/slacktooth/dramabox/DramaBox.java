package me.slacktooth.dramabox;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class DramaBox extends JavaPlugin {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        Random random = new Random();
        String index = random.next
        player.sendMessage(ChatColor.GREEN + this.getConfig().getStringList("")).;
        return false;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
    }
}
