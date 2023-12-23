package dev.galliard.simpletools.recipes;

import org.bukkit.Bukkit;

public class RecipeManager {
	public static void registerRecipes() {
		Bukkit.getServer().addRecipe(TijerasRecipe.get());
		Bukkit.getServer().addRecipe(AdminStickRecipe.get());
		Bukkit.getServer().addRecipe(RottenFleshCampfireRecipe.get());
	}
}
