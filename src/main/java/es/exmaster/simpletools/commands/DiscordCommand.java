package es.exmaster.simpletools.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.exmaster.simpletools.Main;
import es.exmaster.simpletools.utils.Utils;

public class DiscordCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length > 0) {
			sender.sendMessage(Main.PREFIX + " " + 
					Utils.colorCodeParser(Main.plugin.getConfig().getString("language.tooManyArguments")));
			return false;
		}
		Player player = (Player) sender;
		if(player.hasPermission("SimpleTools.discord")) {
			sender.sendMessage(Main.PREFIX + " " + Utils.placeholderParser(
					Utils.colorCodeParser(Main.plugin.getConfig().getString("language.discordMsg")),
					List.of("%sender%"),
					List.of(sender.getName())));
			} else {
			sender.sendMessage(Main.PREFIX + " " + 
					Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
		}
		

		return true;
	}
}
