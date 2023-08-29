package es.exmaster.simpletools.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import es.exmaster.simpletools.Main;
import es.exmaster.simpletools.utils.ConfigManager;
import es.exmaster.simpletools.utils.Utils;

public class FreeFallCommand implements CommandExecutor {
	private ConfigManager configManager = new ConfigManager(Main.plugin,"config.yml");
	@EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length > 1) {
			sender.sendMessage(Main.PREFIX + " " + 
					Utils.colorCodeParser(configManager.getConfig().getString("language.tooManyArguments")));
			return false;
		}
		if (sender.hasPermission("SimpleTools.freefall")) {
			if (args.length == 0) {
				sender.sendMessage(Main.PREFIX + " " +
						Utils.colorCodeParser(configManager.getConfig().getString("language.playerRequired")));
			}
			Player player = Bukkit.getServer().getPlayer(args[0]);
			double xFreeFall = player.getLocation().getX();
			double yFreeFall = player.getLocation().getY() + 200;
			double zFreeFall = player.getLocation().getZ();
			Location freeFallCoords = new Location(player.getWorld(), xFreeFall, yFreeFall, zFreeFall);
			player.teleport(freeFallCoords);
			sender.sendMessage(Utils.colorCodeParser(configManager.getConfig().getString("language.freefallMsg")));
		} else {
			sender.sendMessage(Main.PREFIX + " " + 
					Utils.colorCodeParser(configManager.getConfig().getString("language.noPermission")));
		}

		return true;
	}
}
