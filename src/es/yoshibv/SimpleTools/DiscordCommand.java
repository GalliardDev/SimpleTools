package es.yoshibv.SimpleTools;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {
	@Override
	  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	    if (!(sender instanceof Player)) {
	      sender.sendMessage("§cEste comando sólo lo puede ejecutar un jugador");
	      return false;
	    }
	    if (args.length > 0) {
	        sender.sendMessage("§cDemasiados argumentos!");
	        return false;
	    }
	    
	    Player player = (Player) sender;
	    player.sendMessage("§7Aquí tienes nuestro discord, §a"+sender.getName()+"§7:\n§9§nhttps://discord.gg/HHtQ8wU2TK");
	    
	    return true;
	}
}
