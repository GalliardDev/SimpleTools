package es.yoshibv.SimpleTools;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/* THIS FEATURE IS STILL WIP */
/* THIS FEATURE IS STILL WIP */
/* THIS FEATURE IS STILL WIP */
/* THIS FEATURE IS STILL WIP */
/* THIS FEATURE IS STILL WIP */

public class GlobalChestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("globalchest")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("simpletools.globalchest")) {
                    Inventory inv = Bukkit.createInventory(null, 54, "Global Chest");
                    player.openInventory(inv);
                } else {
                    player.sendMessage("You don't have permission to use this command!");
                }
            } else {
                sender.sendMessage("You must be a player to use this command!");
            }
        }
    
        return false;
    }

}