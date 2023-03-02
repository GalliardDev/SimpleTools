package es.yoshibv.SimpleTools;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {
	@Override
	  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	    if (!(sender instanceof Player)) {
	      sender.sendMessage(Main.PREFIX + Main.plugin.getConfig().getString("language.onlyPlayerCommand"));
	      return false;
	    }
	    if (args.length > 0) {
	        sender.sendMessage(Main.PREFIX + Main.plugin.getConfig().getString("language.tooManyArguments"));
	        return false;
	    }
	    
	    Player player = (Player) sender;
	    player.sendMessage(Main.senderParser(Main.PREFIX + Main.plugin.getConfig().getString("language.discordMsg"), Bukkit.getServer().getPlayer(player.getName())));
	    
	    return true;
	}
}
