package me.slacktooth.emeraldsword;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class EmeraldSword extends JavaPlugin {

    @Override
    public void onEnable() {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta meta = sword.getItemMeta();

        meta.setDisplayName(ChatColor.GREEN + "Emerald Sword");
        sword.setItemMeta(meta);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 5);

        ShapedRecipe recipe = new ShapedRecipe(sword);
        recipe.shape( " E ", " E ", " S " );
        recipe.setIngredient( 'E', Material.EMERALD);
        recipe.setIngredient( 'S', Material.STICK);
        getServer().addRecipe(recipe);
    }
}
