package es.exmaster.simpletools.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.exmaster.simpletools.Main;
import es.exmaster.simpletools.utils.ConfigManager;
import es.exmaster.simpletools.utils.Utils;

public class WorldBlockerCommand implements CommandExecutor {
    private ConfigManager worldBlockerConfigManager = new ConfigManager(Main.plugin,"blockedWorlds.yml");
    private String overworld = Bukkit.getServer().getWorlds().get(0).getName();
    public WorldBlockerCommand() {
        worldBlockerConfigManager.saveDefaultConfig();
    }
    
    private List<String> blockedWorlds = worldBlockerConfigManager.getConfig().getStringList("blockedWorlds");
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(Main.PREFIX + " " +
                    Utils.colorCodeParser(Main.plugin.getConfig().getString("language.invalidArguments")));
            return false;
        }

        if (sender.hasPermission("SimpleTools.worldblocker")) {
            String world = null;
            
            try {
            	world = args[0];
    		} catch(Exception e) {
    			sender.sendMessage(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.prefix")) + " " + 
    					Utils.colorCodeParser(Main.plugin.getConfig().getString("language.invalidArgument")));
    		}
                       
            if (blockedWorlds.contains(world)) {
                blockedWorlds.remove(world);
                sender.sendMessage(Main.PREFIX + " " +
                        Utils.colorCodeParser(Main.plugin.getConfig().getString("language.worldUnblocked")));
            } else {
                blockedWorlds.add(world);
                List<Player> playersInWorld = Bukkit.getWorld(world).getPlayers();
                if(playersInWorld.size()!=0) {
                	for(Player p:playersInWorld) {
                		double x = Bukkit.getWorld(overworld).getSpawnLocation().getX();
                		double y = Bukkit.getWorld(overworld).getSpawnLocation().getY();
                		double z = Bukkit.getWorld(overworld).getSpawnLocation().getZ();
                		p.teleport(new Location(Bukkit.getWorld("world"), x, y, z));
                	}
                }
                sender.sendMessage(Main.PREFIX + " " +
                        Utils.colorCodeParser(Main.plugin.getConfig().getString("language.worldBlocked")));
            }

            worldBlockerConfigManager.getConfig().set("blockedWorlds", blockedWorlds);
            worldBlockerConfigManager.saveConfig();
        } else {
            sender.sendMessage(Main.PREFIX + " " +
                    Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
        }
        return true;
    }
}

