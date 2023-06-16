package es.yoshibv.simpletools.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.yoshibv.simpletools.Main;

public class SpawnCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Main.plugin.getConfig().getString("language.onlyPlayerCommand").replace('&', '§'));
			return false;
		}

		Player player = (Player) sender;
		double xSpawn = player.getWorld().getSpawnLocation().getBlockX() + 0.500;
		double ySpawn = player.getWorld().getSpawnLocation().getBlockY();
		double zSpawn = player.getWorld().getSpawnLocation().getBlockZ() + 0.500;

		if (player.hasPermission("SimpleTools.spawn")) {
			if (args.length == 0) {
				Location spawnCoords = new Location(player.getWorld(), xSpawn, ySpawn, zSpawn);
				player.teleport(spawnCoords);
				sender.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
						Main.plugin.getConfig().getString("language.spawnSelf").replace('&', '§'));
			} else if (args.length >= 1) {
				if (player.hasPermission("SimpleTools.spawn.others")) {
					Player victim = Bukkit.getServer().getPlayer(args[0]);
					Location spawnCoords = new Location(victim.getWorld(), xSpawn, ySpawn, zSpawn);
					victim.teleport(spawnCoords);
					sender.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
							Main.victimParser(Main.plugin.getConfig().getString("language.spawnYouOthers").replace('&', '§'),
							Bukkit.getServer().getPlayer(victim.getName())));
					victim.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
							Main.senderParser(Main.plugin.getConfig().getString("language.spawnOthersYou").replace('&', '§'),
							Bukkit.getServer().getPlayer(sender.getName())));
				} else {
					sender.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
							Main.plugin.getConfig().getString("language.noPermission").replace('&', '§'));
				}
			} 
		} else {
			sender.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
					Main.plugin.getConfig().getString("language.noPermission").replace('&', '§'));
		}
		return true;
	}

}
