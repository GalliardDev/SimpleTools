package es.yoshibv.SimpleTools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GlobalChestCommand implements CommandExecutor {
    private static final Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GREEN + "Global Chest");
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.openInventory(inv);
            } else {
                sender.sendMessage(Main.plugin.getConfig().getString("language.onlyPlayerCommand"));
            }
    
        return true;
    }

    public static Inventory getInv() {
        return inv;
    }
}