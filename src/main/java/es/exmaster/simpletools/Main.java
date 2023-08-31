package es.exmaster.simpletools;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import es.exmaster.simpletools.commands.CommandManager;
import es.exmaster.simpletools.common.GlobalChest;
import es.exmaster.simpletools.events.EventListener;
import es.exmaster.simpletools.recipes.RecipeManager;
import es.exmaster.simpletools.tasks.LocationTracker;
import es.exmaster.simpletools.utils.UpdateChecker;
import es.exmaster.simpletools.utils.Utils;

public class Main extends JavaPlugin implements Listener {
    
    public static Main plugin;
    private final Integer ID = 108067;
    private final String SPIGOT_LINK = "https://www.spigotmc.org/resources/simpletools.108067/";
    public static String PREFIX;

	public void onEnable() {
        super.onEnable();
        plugin = this;
        Main.plugin.saveDefaultConfig();       
        PREFIX = Utils.colorCodeParser(Main.plugin.getConfig().getString("language.prefix"));
        CommandAPI.onEnable();
        CommandManager.registerCommands();
        EventListener.registerEvents();
        RecipeManager.registerRecipes();
        GlobalChest.loadConfig();
        GlobalChest.loadChest();
        LocationTracker.startLocationTrackingTask();
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
    
	@Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).verboseOutput(true)); // Load with verbose output
        
    }
	
    public void onDisable() {
        super.onDisable();
        GlobalChest.saveChest();
        this.getLogger().info("SimpleTools has been disabled!");
    }
}