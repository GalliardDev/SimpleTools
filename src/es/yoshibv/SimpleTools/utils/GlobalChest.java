package es.yoshibv.SimpleTools.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.configuration.file.YamlConfiguration;

public class GlobalChest {
    public File configFile;
    public FileConfiguration config;
    public Inventory globalChestInventory = Bukkit.createInventory(null, 54, ChatColor.DARK_GREEN + "Global Chest");

    // Load the global chest inventory from the configuration file
    @SuppressWarnings("unchecked")
    public void loadGlobalChest() {
        ConfigurationSection inventorySection = config.getConfigurationSection("inventory");
        if (inventorySection != null) {
            Inventory globalChestInventory;
            globalChestInventory.setContents(((List<ItemStack>) inventorySection.getList("items")).toArray(ItemStack[]::new));
        }
    }

    // Save the global chest inventory to the configuration file
    public void saveGlobalChest() {
        ConfigurationSection inventorySection = config.createSection("inventory");
        Inventory globalChestInventory;
        inventorySection.set("items", globalChestInventory.getContents());

        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onEnable() {
        configFile = new File(getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        loadGlobalChest();
    }

    private String getDataFolder() {
        return "plugins/SimpleTools";
    }

    public void onDisable() {
        saveGlobalChest();
    }
}
