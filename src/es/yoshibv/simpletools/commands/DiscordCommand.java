package es.yoshibv.simpletools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.yoshibv.simpletools.Main;

public class DiscordCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length > 0) {
			sender.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
					Main.plugin.getConfig().getString("language.tooManyArguments").replace('&', '§'));
			return false;
		}
		Player player = (Player) sender;
		if(player.hasPermission("SimpleTools.discord")) {
			sender.sendMessage(Main.senderParser(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
					Main.plugin.getConfig().getString("language.discordMsg").replace('&', '§'),
					Bukkit.getServer().getPlayer(player.getName())));
		} else {
			sender.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
					Main.plugin.getConfig().getString("language.noPermission").replace('&', '§'));
		}
		

		return true;
	}
}
