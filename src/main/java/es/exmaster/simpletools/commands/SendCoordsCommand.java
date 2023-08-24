package es.exmaster.simpletools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.exmaster.simpletools.Main;
import es.exmaster.utils.Utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SendCoordsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length > 1) {
			sender.sendMessage(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.prefix")) + " " + 
					Utils.colorCodeParser(Main.plugin.getConfig().getString("language.tooManyArguments")));
			return false;
		}
		Player player = Bukkit.getPlayer(args[0]);
		if(player.hasPermission("SimpleTools.sendcoords")) {
			Location loc = ((Player) sender).getLocation();
			List<String> coords = List.of(String.valueOf(loc.getBlockX()),String.valueOf(loc.getBlockY()),String.valueOf(loc.getBlockZ()));
			player.sendMessage(Utils.colorCodeParser(
					Utils.placeholderParser(
							Main.plugin.getConfig().getString("language.coordsMsg"),
							List.of("%sender%","%x%","%y%","%z%"),
							List.of(sender.getName(),coords.get(0),coords.get(1),coords.get(2)))));
		} else {
			sender.sendMessage(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.prefix")) + " " + 
					Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
		}
		
		return true;
	}

}
