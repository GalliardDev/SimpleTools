package es.yoshibv.SimpleTools;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import es.yoshibv.SimpleTools.utils.GlobalChest;

/* THIS FEATURE IS STILL WIP */

public class GlobalChestCommand extends GlobalChest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("globalchest")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                    Inventory inv = globalChestInventory;
                    player.openInventory(inv);
            } else {
                sender.sendMessage("§cEste comando sólo lo puede ejecutar un jugador");
            }
        }
    
        return true;
    }

}