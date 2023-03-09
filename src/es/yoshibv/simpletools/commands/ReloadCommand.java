package es.yoshibv.simpletools.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {

    @Override
	  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	    if (!(sender instanceof Player)) {
	      sender.sendMessage(Main.plugin.getConfig().getString("language.prefix") +" "+ Main.plugin.getConfig().getString("language.onlyPlayerCommand"));
	      return false;
	    }

        if (command.getName().equalsIgnoreCase("simpletools") && sender instanceof Player) {
           
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage(Main.plugin.getConfig().getString("language.prefix") +" "+ ChatColor.GRAY + "desarrollado por YoshiBv");
               
            } else {
                if (args[0].equalsIgnoreCase("reload")) {
                    Main.plugin.reloadPluginConfig();
                    player.sendMessage(Main.plugin.getConfig().getString("language.prefix") +" "+ ChatColor.GREEN + "Config reloaded");
                }
            }
        }

        return true;
    }
}

    