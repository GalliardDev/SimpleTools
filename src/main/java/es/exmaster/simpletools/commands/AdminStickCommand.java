package es.exmaster.simpletools.commands;

import java.util.Collections;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import es.exmaster.simpletools.Main;
import es.exmaster.simpletools.utils.Utils;

public class AdminStickCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length > 0) {
			sender.sendMessage(Main.PREFIX + " " + 
					Utils.colorCodeParser(Main.plugin.getConfig().getString("language.tooManyArguments")));
			return false;
		}
		Player player = (Player) sender;
		if(player.hasPermission("SimpleTools.adminstick")) {
			player.getInventory().addItem(getPalo());
			} else {
			sender.sendMessage(Main.PREFIX + " " + 
					Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
		}
		

		return true;
	}
	
	private ItemStack getPalo() {
    	ItemStack palo = new ItemStack(Material.STICK);
        ItemMeta meta = palo.getItemMeta();

        // Configurar el nombre y el lore
        meta.setDisplayName(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.adminStickName")));
        meta.setLore(Collections.singletonList(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.adminStickLore"))));

        // Agregar un falso encantamiento para el efecto de brillo
        meta.addEnchant(Enchantment.DAMAGE_ALL, 18000, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                
        // Aplicar los cambios al ItemStack
        palo.setItemMeta(meta);

        return palo;
    }
}
