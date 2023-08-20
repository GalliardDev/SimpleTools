package es.exmaster.simpletools.commands;

import java.io.File;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import es.exmaster.simpletools.Main;
import es.exmaster.utils.Utils;
import es.exmaster.utils.YAMLFileManager;

public class WorldBlockerCommand implements CommandExecutor {
	private Boolean BLOCK = false;
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// TODO Auto-generated method stub
		if (args.length > 1) {
			sender.sendMessage(Main.PREFIX + " " + 
					Utils.colorCodeParser(Main.plugin.getConfig().getString("language.tooManyArguments")));
			return false;
		}
		if (sender.hasPermission("SimpleTools.worldblocker")) {
			String world = args[0];
			File aux = new File(Main.plugin.getDataFolder(), "blockedWorlds.yml");
			Map<String,Boolean> blockedWorlds = YAMLFileManager.readYAML(aux.getPath());
			if(blockedWorlds.keySet().contains(world)) {
				Boolean wasBlocked = blockedWorlds.get(world);
				Boolean newVal = !wasBlocked;
				blockedWorlds.put(world,newVal);
				YAMLFileManager.writeYAML(blockedWorlds, aux.getPath());
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.worldBlockSwitched")));
			} else {
				String worldToBlock = world;
				BLOCK = !BLOCK;
				blockedWorlds.put(worldToBlock, BLOCK);
				YAMLFileManager.writeYAML(blockedWorlds, aux.getPath());
			}
			
		} else {
			sender.sendMessage(Main.PREFIX + " " + 
					Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
		}
		return true;
	}

}
