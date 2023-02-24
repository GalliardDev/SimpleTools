package es.yoshibv.SimpleTools;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
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
        configFile = new File(getDataFolder(), "items.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        globalChestInventory = GlobalChestCommand.inv;

        loadGlobalChest();

        /*File langFile = new File(getDataFolder(), "lang.yml");
        if (!langFile.exists()) {
            langFile.getParentFile().mkdirs();
            saveResource("lang.yml", false);
        }
        YamlConfiguration lang = YamlConfiguration.loadConfiguration(langFile);*/

        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("thunder").setExecutor(new LightningCommand());
        getCommand("freefall").setExecutor(new FreeFallCommand());
        getCommand("globalchest").setExecutor(new GlobalChestCommand());

        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onInventoryOpen(InventoryOpenEvent event) {
                // Verifica si el inventario abierto es el cofre virtual
                if (event.getInventory().equals(globalChestInventory)) {
                    // Realiza alguna acción al abrir el inventario, si es necesario
                    loadGlobalChest();
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
        }, this);

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
}