package es.yoshibv.SimpleTools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import es.yoshibv.SimpleTools.utils.GlobalChest;

/* THIS FEATURE IS STILL WIP */

public class GlobalChestCommand extends GlobalChest implements CommandExecutor {
    public static final Inventory inv = null;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GREEN + "Global Chest");
                player.openInventory(inv);
            } else {
                sender.sendMessage("§cEste comando sólo lo puede ejecutar un jugador");
            }
    
        return true;
    }

}