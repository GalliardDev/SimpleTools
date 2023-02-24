package es.yoshibv.SimpleTools;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

public class Main extends JavaPlugin implements Listener {
    public File configFile;
    public FileConfiguration config;
    public Inventory globalChestInventory;
    
    public void onEnable() {
        super.onEnable();
        configFile = new File(getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        globalChestInventory = Bukkit.createInventory(null, 54, ChatColor.DARK_GREEN + "Global Chest");
        
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("thunder").setExecutor(new LightningCommand());
        getCommand("freefall").setExecutor(new FreeFallCommand());
        getCommand("globalchest").setExecutor(new GlobalChestCommand());

        Bukkit.getPluginManager().registerEvent(InventoryCloseEvent.class, this, null, null, this);
        loadGlobalChest();

        this.getLogger().info("SimpleTools ha sido habilitado!");
    }

    public void onDisable() {
        super.onDisable();
        this.getLogger().info("SimpleTools ha sido deshabilitado!");

        saveGlobalChest();
    }

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