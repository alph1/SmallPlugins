package me.slacktooth.invtest;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class InvTestListener implements Listener {

    private HashMap<Player, ItemStack[]> inventory = new HashMap<Player, ItemStack[]>();

    @EventHandler
    public void onPlayerGamemodeChange(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) {
            inventory.put(player, player.getInventory().getContents());
            player.getInventory().clear();
        }
        if (player.getGameMode() == GameMode.SURVIVAL) {
            if(inventory.containsKey(player)) {
                player.getInventory().setContents(inventory.get(player));
            }
        }
    }
}