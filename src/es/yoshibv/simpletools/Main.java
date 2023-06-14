package es.yoshibv.simpletools;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import es.yoshibv.simpletools.commands.DiscordCommand;
import es.yoshibv.simpletools.commands.FreeFallCommand;
import es.yoshibv.simpletools.commands.GlobalChestCommand;
import es.yoshibv.simpletools.commands.LightningCommand;
import es.yoshibv.simpletools.commands.ReloadCommand;
import es.yoshibv.simpletools.commands.SpawnCommand;
import es.yoshibv.utils.UpdateChecker;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

public class Main extends JavaPlugin implements Listener {
    private File itemsFile;
    private FileConfiguration items;
    private Inventory globalChestInventory;
    private File config;
    private FileConfiguration configF;
    public static Main plugin;
    private static final Integer ID = 108067;
    private static final String SPIGOT_LINK = "https://www.spigotmc.org/resources/simpletools.108067/";
    
    public void onEnable() {
        super.onEnable();
        plugin = this;
        loadConfig();
        loadGlobalChestConfig();
        registerEvents();
        registerCommands();
        loadGlobalChest();
        new UpdateChecker(this, ID).getLatestVersion(version -> {
            String currentVersion = plugin.getConfig().getString("version");
            if (version.equals(currentVersion)) {
                this.getLogger().info("SimpleTools is up to date!");
            } else {
                this.getLogger().severe("SimpleTools is not up to date! You can download the last version from " + SPIGOT_LINK);
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
            
            @EventHandler
            public void onPlayerDeath(PlayerDeathEvent event) {
            	Player player = event.getEntity();
            	Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
            	for(Player p:players) {
            		p.playSound(player, Sound.ENTITY_WITHER_DEATH, 3, 1);
            		p.sendTitle(playerParser(Main.plugin.getConfig().getString("language.deathTitleMsg").replace('&', '§'), player), 
            				event.getDeathMessage().replace(player.getName(), ""), 
            				30, 30, 30);
            	}
            }
            
            @EventHandler
            public void onPlayerJoin(PlayerJoinEvent event) {
            	Player player = event.getPlayer();
            	Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
            	for(Player p:players) {
            		p.sendTitle(Main.plugin.getConfig().getString("language.joinLeaveNameFormat").replace('&', '§') + 
            				player.getName(), 
            				Main.plugin.getConfig().getString("language.joinTitleMsg").replace('&', '§'), 
            				30, 30, 30);
            	}
            }
            
            @EventHandler
            public void onPlayerLeave(PlayerQuitEvent event) {
            	Player player = event.getPlayer();
            	Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
            	for(Player p:players) {
            		p.sendTitle(Main.plugin.getConfig().getString("language.joinLeaveNameFormat").replace('&', '§') + 
            				player.getName(), 
            				Main.plugin.getConfig().getString("language.leaveTitleMsg").replace('&', '§'), 
            				30, 30, 30);
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
    
    public static String playerParser(String message, Player player) {
    	message = message.replace("%player%", player.getName());
    	return message;
    }

    public void reloadPluginConfig() {
        // Let JavaPlugin do its stuff before
        super.reloadConfig();
        // Save default config into plugins/<your-plugin>/config.yml if not exists
        saveDefaultConfig();
        // Get config from config file
        configF = getConfig();
        // Put default values into it (from your jar's config.yml file)
        configF.options().copyDefaults(true);
        // Add missing / new parameters into plugins/<your-plugin>/config.yml
        saveConfig();
    }
}
