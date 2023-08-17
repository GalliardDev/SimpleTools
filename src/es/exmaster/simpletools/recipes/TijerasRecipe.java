package es.exmaster.simpletools.recipes;

import java.util.Collections;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import es.exmaster.simpletools.Main;
import es.exmaster.utils.Utils;

public class TijerasRecipe {
	private static ItemStack crear() {
        ItemStack tijeras = new ItemStack(Material.SHEARS);
        ItemMeta meta = tijeras.getItemMeta();

        // Configurar el nombre y el lore
        meta.setDisplayName(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.scissorsName")));
        meta.setLore(Collections.singletonList(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.scissorsLore"))));

        // Agregar un falso encantamiento para el efecto de brillo
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                
        // Aplicar los cambios al ItemStack
        tijeras.setItemMeta(meta);

        return tijeras;
    }
    
    public static ShapedRecipe get() {
    	ItemStack tijeras = crear();
        NamespacedKey tijerasRecipeKey = new NamespacedKey(Main.plugin, "tijeras_recipe");
        ShapedRecipe tijerasRecipe = new ShapedRecipe(tijerasRecipeKey, tijeras);
        tijerasRecipe.shape(
        		" D ", 
        		"DSD", 
        		" D ");
        tijerasRecipe.setIngredient('D', Material.DIAMOND);
        tijerasRecipe.setIngredient('S', Material.SHEARS);
        return tijerasRecipe;
    }
}
