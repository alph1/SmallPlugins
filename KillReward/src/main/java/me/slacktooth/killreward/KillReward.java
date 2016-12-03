package me.slacktooth.killreward;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class KillReward extends JavaPlugin implements Listener {

    private Material material = Material.getMaterial(this.getConfig().getString("reward"));

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (material == null) {
            return;
        }
        Player killer = event.getEntity().getKiller();
        if (killer == null) {
            return;
        }
        killer.getInventory().addItem(new ItemStack(material, this.getConfig().getInt("count")));
    }
}
