package es.exmaster.simpletools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.exmaster.simpletools.Main;
import es.exmaster.utils.Utils;

public class ReloadCommand
implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.PREFIX + " " + 
            		Utils.colorCodeParser(Main.plugin.getConfig().getString("language.onlyPlayerCommand")));
            return false;
        }
        if (sender.hasPermission("SimpleTools.simpletools")) {
        	if (command.getName().equalsIgnoreCase("simpletools") && sender instanceof Player) {
                Player player = (Player)sender;
                if (args.length == 0) {
                    player.sendMessage(Main.PREFIX + " " + 
                    		"Desarrollado por YoshiBv");
                } else if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("SimpleTools.simpletools.reload")) {
                    Main.plugin.reloadPluginConfig();
                    player.sendMessage(Main.PREFIX + " " + 
                    		Utils.colorCodeParser(Main.plugin.getConfig().getString("language.configReloaded")));
                } else if (args[0].equalsIgnoreCase("reload") && !(sender.hasPermission("SimpleTools.simpletools.reload"))) {
                	sender.sendMessage(Main.PREFIX + " " + 
                			Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
                }
            }
        } else {
			sender.sendMessage(Main.PREFIX + " " + 
					Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
		}
        
        return true;
    }
}