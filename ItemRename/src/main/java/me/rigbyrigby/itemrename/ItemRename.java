package me.rigbyrigby.itemrename;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemRename extends JavaPlugin {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("rename")) {
            if (!(sender instanceof Player)) {
                return false;
            }
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Please use /rename (name)");
                return true;
            }

            if (player.getItemInHand().getType() == Material.MOB_SPAWNER) {
                player.sendMessage(ChatColor.RED + "Renaming mob spawners is not supported.");
                return true;
            }
            if (player.getItemInHand().getType() == Material.TRIPWIRE_HOOK) {
                player.sendMessage(ChatColor.RED + "Renaming crate keys is not supported.");
                return true;
            }
            if(player.getItemInHand() != null && player.getItemInHand().getType() != Material.AIR) {
                ItemStack item = player.getItemInHand();
                ItemMeta meta = item.getItemMeta();
                String name = args[0];

                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                item.setItemMeta(meta);
                player.sendMessage(ChatColor.GREEN + "Item has been renamed to " + ChatColor.YELLOW + item.getItemMeta().getDisplayName());
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "Please hold an item to rename.");
                return true;
            }
        }
        return false;
    }
}