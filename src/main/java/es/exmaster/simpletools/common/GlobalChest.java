package es.exmaster.simpletools.common;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import es.exmaster.simpletools.Main;
import es.exmaster.simpletools.commands.GlobalChestCommand;

public class GlobalChest {
	private static File itemsFile;
    private static FileConfiguration items;
    private static Inventory globalChestInventory;

	public static File getItemsFile() {
		return itemsFile;
	}

	public static FileConfiguration getItems() {
		return items;
	}

	public static Inventory getGlobalChestInventory() {
		return globalChestInventory;
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
        itemsFile = new File(Main.plugin.getDataFolder(), "items.yml");
        items = YamlConfiguration.loadConfiguration(itemsFile);
        globalChestInventory = GlobalChestCommand.getInv();
    }
}
