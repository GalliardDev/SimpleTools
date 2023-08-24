package es.exmaster.simpletools.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import es.exmaster.simpletools.Main;
import es.exmaster.utils.ConfigManager;
import es.exmaster.utils.Utils;

public class WorldBlockerCommand implements CommandExecutor {
    private ConfigManager worldBlockerConfigManager = new ConfigManager(Main.plugin,"blockedWorlds.yml");
    
    public WorldBlockerCommand() {
        worldBlockerConfigManager.saveDefaultConfig();
    }
    
    private List<String> blockedWorlds = worldBlockerConfigManager.getConfig().getStringList("blockedWorlds");
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(Main.PREFIX + " " +
                    Utils.colorCodeParser(Main.plugin.getConfig().getString("language.invalidArguments")));
            return false;
        }

        if (sender.hasPermission("SimpleTools.worldblocker")) {
            String world = args[0];
                       
            if (blockedWorlds.contains(world)) {
                blockedWorlds.remove(world);
                sender.sendMessage(Main.PREFIX + " " +
                        Utils.colorCodeParser(Main.plugin.getConfig().getString("language.worldUnblocked")));
            } else {
                blockedWorlds.add(world);
                sender.sendMessage(Main.PREFIX + " " +
                        Utils.colorCodeParser(Main.plugin.getConfig().getString("language.worldBlocked")));
            }

            worldBlockerConfigManager.getConfig().set("blockedWorlds", blockedWorlds);
            worldBlockerConfigManager.saveConfig();
        } else {
            sender.sendMessage(Main.PREFIX + " " +
                    Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
        }
        return true;
    }
}

