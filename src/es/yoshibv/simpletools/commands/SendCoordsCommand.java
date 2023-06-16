package es.yoshibv.simpletools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import es.yoshibv.simpletools.Main;

public class SendCoordsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length > 1) {
			sender.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
					Main.plugin.getConfig().getString("language.tooManyArguments").replace('&', '§'));
			return false;
		}
		Player player = Bukkit.getPlayer(args[0]);
		if(player.hasPermission("SimpleTools.sendcoords")) {
			Location loc = ((Player) sender).getLocation();
			List<String> coords = List.of(String.valueOf(loc.getX()),String.valueOf(loc.getY()),String.valueOf(loc.getZ()));
			player.sendMessage(Main.senderParser(Main.coordsParser(
									Main.plugin.getConfig().getString("language.coordsMsg").replace('&', '§'),
									coords), player));
		} else {
			sender.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
					Main.plugin.getConfig().getString("language.noPermission").replace('&', '§'));
		}
		
		return true;
	}

}
