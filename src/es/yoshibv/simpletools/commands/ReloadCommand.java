package es.yoshibv.simpletools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.yoshibv.simpletools.Main;
import es.yoshibv.simpletools.config.ConfigGetter;

public class ReloadCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.hasPermission("SimpleTools.simpletools")) {
			if (command.getName().equalsIgnoreCase("simpletools")) {
				Player player = (Player) sender;
				if (args.length == 0) {
					sender.sendMessage(ConfigGetter.PREFIX + " " + "Desarrollado por YoshiBv");
				} else if(args.length == 1 && args[0].equalsIgnoreCase("reload") && player.hasPermission("SimpleTools.simpletools.reload")){
					Main.plugin.reloadPluginConfig();
					sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.CONFIG_RELOADED);
				} else if(args.length == 1 && !(args[0].equalsIgnoreCase("reload"))) {
					sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.INVALID_ARGUMENT);
				} else if(args.length == 1 && args[0].equalsIgnoreCase("reload") && !(player.hasPermission("SimpleTools.simpletools.reload"))){
					sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.NO_PERMISSION);
				} else {
					sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.TOO_MANY_ARGUMENTS);
				}
			}
		} else {
			sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.NO_PERMISSION);
		}
		
		return true;
	}
}