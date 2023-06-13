package es.yoshibv.simpletools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import es.yoshibv.simpletools.config.ConfigGetter;

public class GlobalChestCommand implements CommandExecutor {
	private static final Inventory inv = Bukkit.createInventory(null, 54,
			ConfigGetter.GLOBAL_CHEST_TITLE);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ConfigGetter.ONLY_PLAYER_COMMAND);
			return false;
		}
		if (args.length > 1) {
			sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.TOO_MANY_ARGUMENTS);
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
			sender.sendMessage(ConfigGetter.PREFIX + " " + ConfigGetter.NO_PERMISSION);
		}
		return true;
	}

	public static Inventory getInv() {
		return inv;
	}
}