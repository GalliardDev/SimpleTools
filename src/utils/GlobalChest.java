package utils;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;

public interface GlobalChest {
    
    public File configFile;
    public FileConfiguration config;
    public Inventory globalChestInventory;

    public void loadGlobalChest();
    public void saveGlobalChest();
}
