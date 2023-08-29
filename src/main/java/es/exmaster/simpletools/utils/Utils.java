package es.exmaster.simpletools.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

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

	public static boolean isValidSlot(int i, Player player) {
		return i != player.getInventory().getHeldItemSlot() && i != 40;
	}
	
}
