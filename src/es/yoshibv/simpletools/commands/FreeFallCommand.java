package es.yoshibv.simpletools.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import es.yoshibv.simpletools.config.ConfigGetter;

public class FreeFallCommand implements CommandExecutor {

	@EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length > 1) {
			sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.TOO_MANY_ARGUMENTS);
			return false;
		}
		if (sender.hasPermission("SimpleTools.freefall")) {
			if (args.length == 0) {
				sender.sendMessage(ConfigGetter.PLAYER_REQUIRED);
			}
			Player player = Bukkit.getServer().getPlayer(args[0]);
			double xFreeFall = player.getLocation().getX();
			double yFreeFall = player.getLocation().getY() + 200;
			double zFreeFall = player.getLocation().getZ();
			Location freeFallCoords = new Location(player.getWorld(), xFreeFall, yFreeFall, zFreeFall);
			player.teleport(freeFallCoords);
			sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.FREE_FALL_MSG);
		} else {
			sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.NO_PERMISSION);
		}

		return true;
	}
}
