package es.exmaster.simpletools.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.exmaster.simpletools.Main;
import es.exmaster.simpletools.utils.Utils;

public class ExperiencePayCommand implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.onlyPlayerCommand")));
			return false;
		}

		Player player = (Player) sender;
		Player victim = Bukkit.getPlayer(args[0]);
		Integer cantidad = Integer.valueOf(args[1]);

		if (player.hasPermission("SimpleTools.payxp")) {
			if(args.length > 2) {
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.tooManyArguments")));
			}
			player.setLevel(player.getLevel()-cantidad);
			victim.setLevel(victim.getLevel()+cantidad);
			victim.sendMessage(Utils.placeholderParser(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.youGotPaidXP")),
						List.of("%player%","%amount%"),
						List.of(player.getName(),cantidad.toString())));
			player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			victim.playSound(victim, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
			
		} else {
			sender.sendMessage(Main.PREFIX + " " + 
					Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
		}
		return true;
	}
}
