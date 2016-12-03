package me.slacktooth.togglefall;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class App extends JavaPlugin implements Listener {

    private List<String> activePlayers;
    private List<String> worlds;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        activePlayers = new ArrayList<String>();
        worlds = this.getConfig().getStringList("worlds");
        saveDefaultConfig();
    }


    @Override
    public void onDisable() {
        activePlayers.clear();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (activePlayers.contains(event.getPlayer().getName())) {
            activePlayers.remove(event.getPlayer().getName());
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (activePlayers.contains(player.getName()) && event.getCause() == EntityDamageEvent.DamageCause.FALL && player.hasPermission("nofall.use")) {
                for (String world2 : worlds) {
                    World world = this.getServer().getWorld(world2);
                    if (player.getWorld() == world) {
                        event.setCancelled(true);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("togglefall.use")) {
            return false;
        }
        if (activePlayers.contains(player.getName())) {
            activePlayers.remove(player.getName());
            player.sendMessage(ChatColor.RED + "You have enabled fall damage.");
        } else {
            activePlayers.add(player.getName());
            player.sendMessage(ChatColor.GREEN + "You have disabled fall damage.");
        }
        return false;
    }
}
