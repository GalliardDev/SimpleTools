package utils;

import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GlobalChestImpl implements GlobalChest {
    // Load the global chest inventory from the configuration file
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
}
