package es.exmaster.simpletools;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import es.exmaster.simpletools.commands.AdminStickCommand;
import es.exmaster.simpletools.commands.DiscordCommand;
import es.exmaster.simpletools.commands.FreeFallCommand;
import es.exmaster.simpletools.commands.GlobalChestCommand;
import es.exmaster.simpletools.commands.LightningCommand;
import es.exmaster.simpletools.commands.ReloadCommand;
import es.exmaster.simpletools.commands.SendCoordsCommand;
import es.exmaster.simpletools.commands.SpawnCommand;
import es.exmaster.simpletools.commands.WorldBlockerCommand;
import es.exmaster.simpletools.common.GlobalChest;
import es.exmaster.simpletools.common.WorldBlocker;
import es.exmaster.simpletools.events.EventListener;
import es.exmaster.simpletools.recipes.AdminStickRecipe;
import es.exmaster.simpletools.recipes.RottenFleshCampfireRecipe;
import es.exmaster.simpletools.recipes.TijerasRecipe;
import es.exmaster.utils.UpdateChecker;
import es.exmaster.utils.Utils;

public class Main extends JavaPlugin implements Listener {
    
    private File config;
    private FileConfiguration configFile;
    public static Main plugin;
    private final Integer ID = 108067;
    private final String SPIGOT_LINK = "https://www.spigotmc.org/resources/simpletools.108067/";
    public static String PREFIX;

    public FileConfiguration getConfigFile() {
		return configFile;
	}

	public static void setConfigFile(FileConfiguration configFile) {
		plugin.configFile = configFile;
	}

	public void onEnable() {
        super.onEnable();
        plugin = this;
        loadConfig();
        PREFIX = Utils.colorCodeParser(Main.plugin.getConfig().getString("language.prefix"));
        EventListener.registerEvents();
        registerCommands();
        registerRecipes();
        GlobalChest.loadConfig();
        GlobalChest.loadChest();
        WorldBlocker.loadFile();
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
        GlobalChest.saveChest();
        this.getLogger().info("SimpleTools has been disabled!");
    }

    private void registerCommands() {
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("thunder").setExecutor(new LightningCommand());
        getCommand("freefall").setExecutor(new FreeFallCommand());
        getCommand("globalchest").setExecutor(new GlobalChestCommand());
        getCommand("simpletools").setExecutor(new ReloadCommand());
        getCommand("sendcoords").setExecutor(new SendCoordsCommand());
        getCommand("astick").setExecutor(new AdminStickCommand());
        getCommand("worldblock").setExecutor(new WorldBlockerCommand());
    }
    
    private void registerRecipes() {
    	getServer().addRecipe(TijerasRecipe.get());
    	getServer().addRecipe(AdminStickRecipe.get());
    	getServer().addRecipe(RottenFleshCampfireRecipe.get());
    }

    private void loadConfig() {
        config = new File(getDataFolder(), "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

}