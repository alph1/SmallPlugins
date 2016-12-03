package me.slacktooth.nomaxslots;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NoMaxSlots extends JavaPlugin implements Listener {

    private int maxPlayers = this.getConfig().getInt("max-players");

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if (event.getResult() == PlayerLoginEvent.Result.KICK_FULL && !(this.getServer().getOnlinePlayers().size() > maxPlayers && player.hasPermission("nomaxslots.login"))) {
            event.setResult(PlayerLoginEvent.Result.ALLOWED);
        }
    }
}
