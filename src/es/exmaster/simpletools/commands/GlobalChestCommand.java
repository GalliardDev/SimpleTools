package es.exmaster.simpletools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import es.exmaster.simpletools.Main;
import es.exmaster.utils.Utils;

public class GlobalChestCommand implements CommandExecutor {
	private static final Inventory inv = Bukkit.createInventory(null, 54,
			Utils.colorCodeParser(Main.plugin.getConfig().getString("language.globalChestTitle")));

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.onlyPlayerCommand")));
			return false;
		}
		if (args.length > 1) {
			sender.sendMessage(Main.PREFIX + " " + 
					Utils.colorCodeParser(Main.plugin.getConfig().getString("language.tooManyArguments")));
			return false;
		}
		if (sender instanceof Player && sender.hasPermission("SimpleTools.globalchest")) {
			if (args.length == 0) {
				Player player = (Player) sender;
				player.openInventory(inv);
			} else if (args.length == 1 && sender.hasPermission("SimpleTools.globalChest.others")) {
				Player player = Bukkit.getServer().getPlayer(args[0]);
				player.openInventory(inv);
			}
		} else {
			sender.sendMessage(Main.PREFIX + " " + 
					Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
		}
		return true;
	}

	public static Inventory getInv() {
		return inv;
	}
}