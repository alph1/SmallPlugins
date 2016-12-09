package me.favorcrush.basicelo;

import me.johnzeh.plugins.pr.PlayerRating;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BasicElo extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();

        if (PlayerRating.getRating(player) < 1) {
            return;
        } else if (PlayerRating.getRating(player) > 1) {
            PlayerRating.setRating(player, PlayerRating.getRating(player) - 1);
            player.sendMessage(ChatColor.RED + "You have lost 1 ELO for dying.");
            PlayerRating.setRating(killer, PlayerRating.getRating(killer) + 1);
            killer.sendMessage(ChatColor.GRAY + "You have earned 1 ELO for killing " + player.getName());
        }
    }
}
