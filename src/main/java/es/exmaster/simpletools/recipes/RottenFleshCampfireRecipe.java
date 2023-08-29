package es.exmaster.simpletools.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.CampfireRecipe;
import org.bukkit.inventory.ItemStack;

import es.exmaster.simpletools.Main;

public class RottenFleshCampfireRecipe {
    public static CampfireRecipe get() {
    	
        NamespacedKey rottenRecipeKey = new NamespacedKey(Main.plugin, "rotten_campfire_recipe");
        CampfireRecipe rottenRecipe = new CampfireRecipe(
        		rottenRecipeKey, 
        		new ItemStack(Material.BEEF), 
        		Material.ROTTEN_FLESH, 
        		0, 12000);
        return rottenRecipe;
    }

}
