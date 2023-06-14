package es.yoshibv.simpletools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.yoshibv.simpletools.Main;

public class ReloadCommand
implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
            		Main.plugin.getConfig().getString("language.onlyPlayerCommand").replace('&', '§'));
            return false;
        }
        if (sender.hasPermission("SimpleTools.simpletools")) {
        	if (command.getName().equalsIgnoreCase("simpletools") && sender instanceof Player) {
                Player player = (Player)sender;
                if (args.length == 0) {
                    player.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
                    		"Desarrollado por YoshiBv");
                } else if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("SimpleTools.simpletools.reload")) {
                    Main.plugin.reloadPluginConfig();
                    player.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
                    		Main.plugin.getConfig().getString("language.configReloaded").replace('&', '§'));
                } else if (args[0].equalsIgnoreCase("reload") && !(sender.hasPermission("SimpleTools.simpletools.reload"))) {
                	sender.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
                			Main.plugin.getConfig().getString("language.noPermission").replace('&', '§'));
                }
            }
        } else {
			sender.sendMessage(Main.plugin.getConfig().getString("language.prefix").replace('&', '§') + " " + 
					Main.plugin.getConfig().getString("language.noPermission").replace('&', '§'));
		}
        
        return true;
    }
}