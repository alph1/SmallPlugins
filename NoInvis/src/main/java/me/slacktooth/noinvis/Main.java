package me.slacktooth.noinvis;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;


public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            ItemStack item = player.getItemInHand();
            if (item != null && item.getType() != Material.AIR) {
                if (item.getType() == Material.POTION && item.getDurability() != 0) {
                    Potion potion = Potion.fromItemStack(item);
                    PotionEffectType effect = potion.getType().getEffectType();
                    if (effect == PotionEffectType.INVISIBILITY) {
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.RED + "You are not allowed to drink invisibility potions");
                        player.setItemInHand(new ItemStack(Material.CAKE, 1));
                    }
                }
            }
        }
    }
}
