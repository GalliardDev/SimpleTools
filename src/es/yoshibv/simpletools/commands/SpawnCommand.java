package es.yoshibv.simpletools.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.yoshibv.simpletools.Main;
import es.yoshibv.simpletools.config.ConfigGetter;

public class SpawnCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ConfigGetter.ONLY_PLAYER_COMMAND);
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
				sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.SPAWN_SELF);
			} else if (args.length >= 1) {
				if (player.hasPermission("SimpleTools.spawn.others")) {
					Player victim = Bukkit.getServer().getPlayer(args[0]);
					Location spawnCoords = new Location(victim.getWorld(), xSpawn, ySpawn, zSpawn);
					victim.teleport(spawnCoords);
					sender.sendMessage(ConfigGetter.PREFIX + " " + Main.victimParser(ConfigGetter.SPAWN_YOU_OTHERS,
							Bukkit.getServer().getPlayer(victim.getName())));
					victim.sendMessage(ConfigGetter.PREFIX + " " + Main.senderParser(ConfigGetter.SPAWN_OTHERS_YOU,
							Bukkit.getServer().getPlayer(sender.getName())));
				} else {
					sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.NO_PERMISSION);
				}
			} 
		} else {
			sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.NO_PERMISSION);
		}
		return true;
	}

}
