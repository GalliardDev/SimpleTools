package es.exmaster.simpletools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.exmaster.simpletools.Main;
import es.exmaster.simpletools.utils.ConfigManager;
import es.exmaster.simpletools.utils.Utils;

public class LightningCommand implements CommandExecutor {
	private ConfigManager configManager = new ConfigManager(Main.plugin,"config.yml");
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (args.length > 2) {
			sender.sendMessage(Main.PREFIX + " " + 
					Utils.colorCodeParser(configManager.getConfig().getString("language.tooManyArguments")));
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
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(configManager.getConfig().getString("language.playerRequired")));
				return false;
			}
		} else {
			sender.sendMessage(Main.PREFIX + " " + 
					Utils.colorCodeParser(configManager.getConfig().getString("language.noPermission")));
		}
		return true;
	}

}
