package es.exmaster.simpletools.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import es.exmaster.simpletools.SimpleTools;

public class GlobalChest {
	private static File itemsFile;
	private static FileConfiguration items;
	private static ConfigManager simpleConfig = new ConfigManager(SimpleTools.plugin, "config.yml");
	private static Inventory globalChestInventory;
	private static Inventory inv = Bukkit.createInventory(null, 54,
			Utils.colorCodeParser(simpleConfig.getConfig().getString("language.globalChestTitle")));

	public static File getItemsFile() {
		return itemsFile;
	}

	public static FileConfiguration getItems() {
		return items;
	}

	public static Inventory getGlobalChestInventory() {
		return globalChestInventory;
	}
	
	public static Inventory getInv() {
		return inv;
	}

	@SuppressWarnings("unchecked")
	public static void loadChest() {
		ConfigurationSection inventorySection = items.getConfigurationSection("inventory");
		if (inventorySection != null) {
			globalChestInventory.setContents(((List<ItemStack>) inventorySection.getList("items")).toArray(ItemStack[]::new));
		}
	}

	public static void saveChest() {
		ConfigurationSection inventorySection = items.createSection("inventory");
		inventorySection.set("items", globalChestInventory.getContents());

		try {
			items.save(itemsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadConfig() {
		itemsFile = new File(SimpleTools.plugin.getDataFolder(), "items.yml");
		items = YamlConfiguration.loadConfiguration(itemsFile);
		globalChestInventory = inv;
	}
}
