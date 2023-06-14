package es.yoshibv.simpletools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.yoshibv.simpletools.Main;

public class LightningCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (args.length > 2) {
			sender.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
					Main.plugin.getConfig().getString("language.tooManyArguments").replace('&', '§'));
			return false;
		}
		if (player.hasPermission("SimpleTools.thunder")) {
			if (args.length == 2) {
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
				sender.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
						Main.plugin.getConfig().getString("language.playerRequired").replace('&', '§'));
				return false;
			}
		} else {
			sender.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
					Main.plugin.getConfig().getString("language.noPermission").replace('&', '§'));
		}
		return true;
	}

}
