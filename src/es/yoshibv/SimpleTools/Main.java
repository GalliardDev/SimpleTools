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
    private File itemsFile;
    private FileConfiguration items;
    private Inventory globalChestInventory;
    private File config;
    public static Main plugin;
   
    public void onEnable() {
        super.onEnable();
        loadConfig();
        loadGlobalChestConfig();
        loadGlobalChest();
        registerEvents();
        registerCommands();
        plugin = this;        
        this.getLogger().info(getConfig().getString("language.onEnable"));
    }

    public void onDisable() {
        super.onDisable();
        saveGlobalChest();
        this.getLogger().info(getConfig().getString("language.onDisable"));
    }

    /* =================================== */
    /* ZONA DE MÉTODOS AUXILIARES DEL MAIN */
    /* =================================== */

    @SuppressWarnings("unchecked")
    public void loadGlobalChest() {
        ConfigurationSection inventorySection = items.getConfigurationSection("inventory");
        if (inventorySection != null) {
            globalChestInventory.setContents(((List<ItemStack>) inventorySection.getList("items")).toArray(ItemStack[]::new));
        }
    }

    public void saveGlobalChest() {
        ConfigurationSection inventorySection = items.createSection("inventory");
        inventorySection.set("items", globalChestInventory.getContents());

        try {
            items.save(itemsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerCommands() {
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("thunder").setExecutor(new LightningCommand());
        getCommand("freefall").setExecutor(new FreeFallCommand());
        getCommand("globalchest").setExecutor(new GlobalChestCommand());
    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onInventoryOpen(InventoryOpenEvent event) {
                if (event.getInventory().equals(globalChestInventory)) {
                    loadGlobalChest();
                }
            }
    
            @EventHandler
            public void onInventoryClose(InventoryCloseEvent event) {
                if (event.getInventory().equals(globalChestInventory)) {
                    saveGlobalChest();
                }
            }
        }, this);
    }

    public void loadConfig() {
        config = new File(getDataFolder(), "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    public void loadGlobalChestConfig() {
        itemsFile = new File(getDataFolder(), "items.yml");
        items = YamlConfiguration.loadConfiguration(itemsFile);
        globalChestInventory = GlobalChestCommand.getInv();
    }
}