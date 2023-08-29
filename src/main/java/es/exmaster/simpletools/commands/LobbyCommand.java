package es.exmaster.simpletools.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.exmaster.simpletools.Main;
import es.exmaster.simpletools.utils.Utils;

public class LobbyCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.onlyPlayerCommand")));
			return false;
		}

		Player player = (Player) sender;
		if (player.hasPermission("SimpleTools.lobby")) {
			if(Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("config.lobby"))!=null) {
				World lobby = Bukkit.getWorld(Main.plugin.getConfig().getString("config.lobby"));
				double xSpawn = lobby.getSpawnLocation().getBlockX() + 0.500;
				double ySpawn = lobby.getSpawnLocation().getBlockY();
				double zSpawn = lobby.getSpawnLocation().getBlockZ() + 0.500;
				if (args.length == 0) {
					Location spawnCoords = new Location(lobby, xSpawn, ySpawn, zSpawn);
					player.teleport(spawnCoords);
					sender.sendMessage(Main.PREFIX + " " + 
							Utils.colorCodeParser(Main.plugin.getConfig().getString("language.lobbySelf")));
				} else if (args.length >= 1) {
					if (player.hasPermission("SimpleTools.spawn.others")) {
						Player victim = Bukkit.getServer().getPlayer(args[0]);
						Location spawnCoords = new Location(lobby, xSpawn, ySpawn, zSpawn);
						victim.teleport(spawnCoords);
						sender.sendMessage(Main.PREFIX + " " + 
								Utils.placeholderParser(
										Utils.colorCodeParser(Main.plugin.getConfig().getString("language.lobbyYouOthers")),
										List.of("%victim%"),
										List.of(victim.getName())));

						victim.sendMessage(Main.PREFIX + " " + 
								Utils.placeholderParser(
										Utils.colorCodeParser(Main.plugin.getConfig().getString("language.lobbyOthersYou")),
										List.of("%sender%"),
										List.of(sender.getName())));
					} else {
						sender.sendMessage(Main.PREFIX + " " + 
								Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
					}
				}
			} else {
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noLobby")));
			}
			 
		} else {
			sender.sendMessage(Main.PREFIX + " " + 
					Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
		}
		return true;
	}
}
