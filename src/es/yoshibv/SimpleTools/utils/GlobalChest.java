package es.yoshibv.SimpleTools.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import es.yoshibv.SimpleTools.GlobalChestCommand;

public class GlobalChest {
    public File configFile;
    public FileConfiguration config;
    public Inventory globalChestInventory = GlobalChestCommand.inv;

    // Load the global chest inventory from the configuration file
    @SuppressWarnings("unchecked")
    public void loadGlobalChest() {
        ConfigurationSection inventorySection = config.getConfigurationSection("inventory");
        if (inventorySection != null) {
            globalChestInventory.setContents(((List<ItemStack>) inventorySection.getList("items")).toArray(ItemStack[]::new));
        }
    }

    // Save the global chest inventory to the configuration file
    public void saveGlobalChest() {
        ConfigurationSection inventorySection = config.createSection("inventory");
        inventorySection.set("items", globalChestInventory.getContents());

        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
    // Verifica si el inventario cerrado es el cofre virtual
    if (event.getInventory().equals(globalChestInventory)) {
        // Guarda el contenido del cofre virtual en el archivo de configuración
        saveGlobalChest();
    }
}

    
}
