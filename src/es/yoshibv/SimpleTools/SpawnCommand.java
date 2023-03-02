package es.yoshibv.SimpleTools;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(Main.plugin.getConfig().getString("language.onlyPlayerCommand"));
      return false;
    }
    
    Player player = (Player) sender;
    double xSpawn = player.getWorld().getSpawnLocation().getBlockX() + 0.500;
    double ySpawn = player.getWorld().getSpawnLocation().getBlockY();
    double zSpawn = player.getWorld().getSpawnLocation().getBlockZ() + 0.500;
    
    if (args.length == 0) {
    	Location spawnCoords = new Location(player.getWorld(), xSpawn, ySpawn, zSpawn);
    	player.teleport(spawnCoords);
	    player.sendMessage(Main.PREFIX + Main.plugin.getConfig().getString("language.spawnSelf"));
    } else if (args.length >= 1) {
    	if (player.hasPermission("SimpleTools.spawn.others")) {
    		Player victim = Bukkit.getServer().getPlayer(args[0]);
        	Location spawnCoords = new Location(victim.getWorld(), xSpawn, ySpawn, zSpawn);
        	victim.teleport(spawnCoords);
        	player.sendMessage(Main.PREFIX + Main.victimParser(Main.plugin.getConfig().getString("language.spawnYouOthers"), Bukkit.getServer().getPlayer(victim.getName())));
        	victim.sendMessage(Main.PREFIX + Main.senderParser(Main.plugin.getConfig().getString("language.spawnOthersYou"), Bukkit.getServer().getPlayer(sender.getName())));
    	} else {
    		player.sendMessage(Main.PREFIX + Main.plugin.getConfig().getString("language.noPermission"));
    	}
    	
    }
    return true;
  }

  

}

