package es.exmaster.simpletools.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import es.exmaster.simpletools.Main;
import es.exmaster.utils.Utils;
import es.exmaster.utils.YAMLFileManager;

public class WorldBlockerCommand implements CommandExecutor {
	private Boolean BLOCK = false;
	private Map<String,Boolean> blockedWorlds = new HashMap<>();
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
			try {
				if(Files.readAllLines(Path.of(aux.getPath())).size()!=0) {
					blockedWorlds = YAMLFileManager.readYAML(aux.getPath());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(blockedWorlds.keySet().contains(world)) {
				Boolean wasBlocked = blockedWorlds.get(world);
				Boolean newVal = !wasBlocked;
				blockedWorlds.put(world,newVal);
				YAMLFileManager.writeYAML(blockedWorlds, aux.getPath());
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.worldBlockSwitched")));
			} else {
				Bukkit.getLogger().info("EL MUNDO NO EXISTE");
				String worldToBlock = world;
				Bukkit.getLogger().info(worldToBlock);
				BLOCK = !BLOCK;
				Bukkit.getLogger().info(BLOCK.toString());
				blockedWorlds.put(worldToBlock, BLOCK);
				Bukkit.getLogger().info("AÑADIDA PAREJA" + worldToBlock +"="+ BLOCK.toString());
				YAMLFileManager.writeYAML(blockedWorlds, aux.getPath());
				Bukkit.getLogger().info("YAML ESCRITO");
			}
			
		} else {
			sender.sendMessage(Main.PREFIX + " " + 
					Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
		}
		return true;
	}

}
