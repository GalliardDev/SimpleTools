package es.yoshibv.simpletools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.yoshibv.simpletools.Main;
import es.yoshibv.simpletools.config.ConfigGetter;

public class ReloadCommand
implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.ONLY_PLAYER_COMMAND);
            return false;
        }
        if (sender.hasPermission("SimpleTools.simpletools")) {
        	if (command.getName().equalsIgnoreCase("simpletools") && sender instanceof Player) {
                Player player = (Player)sender;
                if (args.length == 0) {
                    player.sendMessage(ConfigGetter.PREFIX + " " + "Desarrollado por YoshiBv");
                } else if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("SimpleTools.simpletools.reload")) {
                    Main.plugin.reloadPluginConfig();
                    player.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.CONFIG_RELOADED);
                } else if (args[0].equalsIgnoreCase("reload") && !(sender.hasPermission("SimpleTools.simpletools.reload"))) {
                	sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.NO_PERMISSION);
                }
            }
        } else {
			sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.NO_PERMISSION);
		}
        
        return true;
    }
}