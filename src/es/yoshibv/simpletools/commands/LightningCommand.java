package es.yoshibv.simpletools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.yoshibv.simpletools.config.ConfigGetter;

public class LightningCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (args.length > 1) {
			sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.TOO_MANY_ARGUMENTS);
			return false;
		}
		if (player.hasPermission("SimpleTools.thunder")) {
			if (args.length == 1) {
				Player victim = Bukkit.getServer().getPlayer(args[0]);
				Integer lightningTimes = Integer.valueOf(args[1]);
				for (int i = 0; i < lightningTimes; i++) {
					victim.getPlayer().getWorld().strikeLightning(victim.getPlayer().getLocation());
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			} else if(args.length == 0){
				sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.PLAYER_REQUIRED);
				return false;
			}
		} else {
			sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.NO_PERMISSION);
		}
		return true;
	}

}
