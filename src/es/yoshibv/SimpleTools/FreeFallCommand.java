package es.yoshibv.SimpleTools;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class FreeFallCommand implements CommandExecutor {
	@EventHandler
    
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	    if (!(sender instanceof Player)) {
	      sender.sendMessage(Main.PREFIX + Main.plugin.getConfig().getString("language.onlyPlayerCommand"));
	      return false;
	    }
	    
	    Player player = Bukkit.getServer().getPlayer(args[0]);
	    double x = player.getLocation().getX();
	    double y = player.getLocation().getY() + 200;
	    double z = player.getLocation().getZ();
	    Location freeFallCoords = new Location(player.getWorld(), x, y, z);
	    player.teleport(freeFallCoords);
	    player.sendMessage(Main.PREFIX + Main.plugin.getConfig().getString("language.freefallMsg"));
	    
	    return true;
	}
	// Anular el daño de caída del jugador
	@EventHandler
	public void onFallDamage(EntityDamageEvent event) {
		if (event.getCause() == DamageCause.FALL) {
			event.setCancelled(true);
		}
	}
	
}
