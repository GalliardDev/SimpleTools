package es.yoshibv.simpletools.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import es.yoshibv.simpletools.Main;

public class FreeFallCommand implements CommandExecutor {

	@EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length > 1) {
			sender.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
					Main.plugin.getConfig().getString("language.tooManyArguments").replace('&', '§'));
			return false;
		}
		if (sender.hasPermission("SimpleTools.freefall")) {
			if (args.length == 0) {
				sender.sendMessage(Main.plugin.getConfig().getString("language.playerRequired").replace('&', '§'));
			}
			Player player = Bukkit.getServer().getPlayer(args[0]);
			double xFreeFall = player.getLocation().getX();
			double yFreeFall = player.getLocation().getY() + 200;
			double zFreeFall = player.getLocation().getZ();
			Location freeFallCoords = new Location(player.getWorld(), xFreeFall, yFreeFall, zFreeFall);
			player.teleport(freeFallCoords);
			sender.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
					Main.plugin.getConfig().getString("language.freefallMsg").replace('&', '§'));
		} else {
			sender.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
					Main.plugin.getConfig().getString("language.noPermission").replace('&', '§'));
		}

		return true;
	}
}
