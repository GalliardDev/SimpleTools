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
      sender.sendMessage("§cEste comando sólo lo puede ejecutar un jugador");
      return false;
    }
    
    Player player = (Player) sender;
    double xSpawn = player.getWorld().getSpawnLocation().getBlockX() + 0.500;
    double ySpawn = player.getWorld().getSpawnLocation().getBlockY();
    double zSpawn = player.getWorld().getSpawnLocation().getBlockZ() + 0.500;
    
    if (args.length == 0) {
    	Location spawnCoords = new Location(player.getWorld(), xSpawn, ySpawn, zSpawn);
    	player.teleport(spawnCoords);
	    player.sendMessage("§7Has sido teletransportado al spawn");
    } else if (args.length >= 1) {
    	if (player.hasPermission("SimpleTools.spawn.others")) {
    		Player p = Bukkit.getServer().getPlayer(args[0]);
        	Location spawnCoords = new Location(p.getWorld(), xSpawn, ySpawn, zSpawn);
        	p.teleport(spawnCoords);
        	player.sendMessage("§7Has teletransportado a §a"+args[0]+" §7al spawn");
        	p.sendMessage("§7Has sido teletransportado al spawn por §a"+player.getName());
    	} else {
    		player.sendMessage("§cNo tienes permisos para esto, put!");
    	}
    	
    }
    return true;
  }

}

