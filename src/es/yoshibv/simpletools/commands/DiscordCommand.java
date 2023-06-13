package es.yoshibv.simpletools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.yoshibv.simpletools.Main;
import es.yoshibv.simpletools.config.ConfigGetter;

public class DiscordCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length > 0) {
			sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.TOO_MANY_ARGUMENTS);
			return false;
		}
		Player player = (Player) sender;
		if(player.hasPermission("SimpleTools.discord")) {
			sender.sendMessage(Main.senderParser(ConfigGetter.PREFIX + " " + ConfigGetter.DISCORD_MSG,
					Bukkit.getServer().getPlayer(player.getName())));
		} else {
			sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.NO_PERMISSION);
		}
		

		return true;
	}
}
