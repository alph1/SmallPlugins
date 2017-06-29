package me.slacktooth.dramabox;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Random;

public class DramaBox extends JavaPlugin {

    private List<String> drama = this.getConfig().getStringList("drama");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Random random = new Random();
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.GREEN + drama.get(random.nextInt(drama.size())));
        } else {
            sender.sendMessage(drama.get(random.nextInt(drama.size())));
        }
        return false;
    }
}
