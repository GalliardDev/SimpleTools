package es.exmaster.simpletools.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import es.exmaster.simpletools.common.MinepacksAccessor;

public class Utils {
	public static String placeholderParser(String message, List<String> placeholders, List<String> values) {
        int i = 0;
        message = message.replace('&', '§');
    	for(String p:placeholders) {
    		if(message.contains(p)) {
        	    message = message.replace(p, values.get(i));
        	    i++;
        	}
    	}
    	return message;
    }
	
	public static String colorCodeParser(String message) {
		message = message.replace('&', '§');
		return message;
	}
	
	public static Material getMaterialWithProb() {
    	double n = Math.random();
    	Material res = null;
    	if(n>0.40) {
    		res = Material.BEEF;
    	} else {
    		res = Material.BONE;
    	}
    	return res;
    }
	
	public static Boolean strToBool(String s) {
		Boolean res = null;
		if(s=="true") {
			res = true;
		} else if(s=="false") {
			res = false;
		}
		return res;
	}
	
	public static String millisToDate(long millis) {
    	Date fecha = new Date(millis);
        Instant instant = fecha.toInstant();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String res = instant.atZone(ZoneId.systemDefault()).format(formato);
        return res;
	}
	
	public static String millisToTimeQuantity(long millis) {
		long segundos = millis / 1000;
        long minutos = segundos / 60;
        long horas = minutos / 60;
        long dias = horas / 24;
        return dias+";"+horas+";"+minutos+";"+segundos;
	}
		
	public static void refillItem(Player player, Material material, EquipmentSlot hand) {
		ItemStack[] items = player.getInventory().getStorageContents();

		for (int i = 0; i < 36; ++i) {
			if (items[i] != null && isValidSlot(i, player) && items[i].getType().equals(material)) {
				if (hand.equals(EquipmentSlot.HAND)) {
					player.getInventory().setItemInMainHand(items[i]);
					player.getInventory().setItem(i, (ItemStack) null);
					break;
				}

				if (hand.equals(EquipmentSlot.OFF_HAND)) {
					player.getInventory().setItemInOffHand(items[i]);
					player.getInventory().setItem(i, (ItemStack) null);
					break;
				}
			}
		}

	}
	
	public static void refillItemFromMinepack(Player player, Material material, EquipmentSlot hand) {
	    Inventory backpack = MinepacksAccessor.getPlayerBackpackInventory(player);
	    
	    if (backpack != null) {
	        ItemStack[] contents = backpack.getContents();
	        for (int i = 0; i < contents.length; i++) {
	            ItemStack itemStack = contents[i];
	            if (itemStack != null && isValidSlot(i, player) && itemStack.getType() == material) {
	                ItemStack itemToMove = itemStack.clone();
	                if (hand == EquipmentSlot.HAND) {
	                    player.getInventory().setItemInMainHand(itemToMove);
	                } else if (hand == EquipmentSlot.OFF_HAND) {
	                    player.getInventory().setItemInOffHand(itemToMove);
	                }
	                contents[i] = null;
	                backpack.setContents(contents);
	                break; 
	            }
	        }
	    }
	}


	public static boolean isValidSlot(int i, Player player) {
		return i != player.getInventory().getHeldItemSlot() && i != 40;
	}
	
	public static int getItemCount(Inventory inventory, Material material) {
        int count = 0;
        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.getType() == material) {
                count += item.getAmount();
            }
        }
        return count;
    }
	
}
