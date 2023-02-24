package es.yoshibv.SimpleTools;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    
    public void onEnable() {
        super.onEnable();
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("thunder").setExecutor(new LightningCommand());
        getCommand("freefall").setExecutor(new FreeFallCommand());
        getCommand("globalchest").setExecutor(new GlobalChestCommand());
        // Crea el archivo de configuración y lo carga
        
        this.getLogger().info("SimpleTools ha sido habilitado!");
    }

    public void onDisable() {
        super.onDisable();
        this.getLogger().info("SimpleTools ha sido deshabilitado!");
    }
}