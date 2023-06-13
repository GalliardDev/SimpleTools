package es.yoshibv.simpletools;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import es.yoshibv.simpletools.commands.DiscordCommand;
import es.yoshibv.simpletools.commands.FreeFallCommand;
import es.yoshibv.simpletools.commands.GlobalChestCommand;
import es.yoshibv.simpletools.commands.LightningCommand;
import es.yoshibv.simpletools.commands.ReloadCommand;
import es.yoshibv.simpletools.commands.SpawnCommand;
import es.yoshibv.simpletools.config.ConfigGetter;
import es.yoshibv.utils.UpdateChecker;

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
    private static final Integer ID = 108067;
    
    public void onEnable() {
        super.onEnable();
        plugin = this;
        loadConfig();
        loadGlobalChestConfig();
        registerEvents();
        registerCommands();
        loadGlobalChest();
        new UpdateChecker(this, ID).getLatestVersion(version -> {
            String currentVersion = ConfigGetter.VERSION;
            if (version.equals(currentVersion)) {
                this.getLogger().info("SimpleTools is up to date!");
            } else {
                this.getLogger().severe("SimpleTools is not up to date! You can download the last version from SPIGOT_LINK");
            }
        });
        this.getLogger().info("SimpleTools has been enabled!");
    }
    
    public void onDisable() {
        super.onDisable();
        saveGlobalChest();
        this.getLogger().info("SimpleTools has been disabled!");
    }

    /* =================================== */
    /* ZONA DE MÉTODOS AUXILIARES DEL MAIN */
    /* =================================== */

    @SuppressWarnings("unchecked")
	private void loadGlobalChest() {
        ConfigurationSection inventorySection = items.getConfigurationSection("inventory");
        if (inventorySection != null) {
            globalChestInventory.setContents(((List<ItemStack>) inventorySection.getList("items")).toArray(ItemStack[]::new));
        }
    }

    private void saveGlobalChest() {
        ConfigurationSection inventorySection = items.createSection("inventory");
        inventorySection.set("items", globalChestInventory.getContents());

        try {
            items.save(itemsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerCommands() {
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("thunder").setExecutor(new LightningCommand());
        getCommand("freefall").setExecutor(new FreeFallCommand());
        getCommand("globalchest").setExecutor(new GlobalChestCommand());
        getCommand("simpletools").setExecutor(new ReloadCommand());
    }

    private void registerEvents() {
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

    private void loadConfig() {
        config = new File(getDataFolder(), "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    private void loadGlobalChestConfig() {
        itemsFile = new File(getDataFolder(), "items.yml");
        items = YamlConfiguration.loadConfiguration(itemsFile);
        globalChestInventory = GlobalChestCommand.getInv();
    }

    public static String victimParser(String message, Player victim) {
        message = message.replace("%victim%", victim.getName()); 
        return message;
    }
    
    public static String senderParser(String message, Player sender) {
        message = message.replace("%sender%", sender.getName());
        return message;
    }

    public void reloadPluginConfig() {
        reloadConfig();
    }
}