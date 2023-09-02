package es.exmaster.simpletools.recipes;

import java.util.Collections;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import es.exmaster.simpletools.SimpleTools;
import es.exmaster.simpletools.utils.Utils;

public class AdminStickRecipe {
	private static ItemStack crear() {
        ItemStack palo = new ItemStack(Material.STICK);
        ItemMeta meta = palo.getItemMeta();

        // Configurar el nombre y el lore
        meta.setDisplayName(Utils.colorCodeParser(SimpleTools.plugin.getConfig().getString("language.adminStickName")));
        meta.setLore(Collections.singletonList(Utils.colorCodeParser(SimpleTools.plugin.getConfig().getString("language.adminStickLore"))));

        // Agregar un falso encantamiento para el efecto de brillo
        meta.addEnchant(Enchantment.DAMAGE_ALL, 18000, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                
        // Aplicar los cambios al ItemStack
        palo.setItemMeta(meta);

        return palo;
    }
    
    public static ShapedRecipe get() {
    	ItemStack palo = crear();
        NamespacedKey paloRecipeKey = new NamespacedKey(SimpleTools.plugin, "admin_stick_recipe");
        ShapedRecipe paloRecipe = new ShapedRecipe(paloRecipeKey, palo);
        paloRecipe.shape(
        		"DDD", 
        		"DSD", 
        		"DDD");
        paloRecipe.setIngredient('D', Material.BEDROCK);
        paloRecipe.setIngredient('S', Material.STICK);
        return paloRecipe;
    }
    
    
}

