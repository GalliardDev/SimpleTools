package es.exmaster.simpletools;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import es.exmaster.simpletools.commands.CommandManager;
import es.exmaster.simpletools.events.EventListener;
import es.exmaster.simpletools.recipes.RecipeManager;
import es.exmaster.simpletools.tasks.LocationTracker;
import es.exmaster.simpletools.utils.ConfigWrapper;
import es.exmaster.simpletools.utils.GlobalChest;
import es.exmaster.simpletools.utils.UpdateChecker;
import es.exmaster.simpletools.utils.Utils;

public class SimpleTools extends JavaPlugin implements Listener {
    
    public static SimpleTools plugin;
    private final Integer ID = 108067;
    private final String SPIGOT_LINK = "https://www.spigotmc.org/resources/simpletools.108067/";
    private static ConfigWrapper config = new ConfigWrapper();
    
    public static ConfigWrapper getConf() {
    	return config;
    }

	public void onEnable() {
        super.onEnable();
        plugin = this;
        config.onEnable();
        Utils.createLangs();
        CommandAPI.onEnable();
        CommandManager.registerCommands();
        EventListener.registerEvents();
        RecipeManager.registerRecipes();
        GlobalChest.loadConfig();
        GlobalChest.loadChest();
        LocationTracker.startLocationTrackingTask();
        new UpdateChecker(this, ID).getLatestVersion(version -> {
            String currentVersion = plugin.getDescription().getVersion();
            if (version.equals(currentVersion)) {
                this.getLogger().info("I'm up to date!");
            } else {
                this.getLogger().severe("I'm not up to date! You can download my last version from " + SPIGOT_LINK);
            }
        });
        this.getLogger().info("I've been enabled! :)");
    }
    
	@Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).verboseOutput(true)); // Load with verbose output
        
    }
	
    public void onDisable() {
        super.onDisable();
        GlobalChest.saveChest();
        this.getLogger().info("I've been disabled! :(");
    }
}