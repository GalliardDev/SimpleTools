package es.yoshibv.simpletools.config;

import org.bukkit.plugin.java.JavaPlugin;

import es.yoshibv.simpletools.Main;

public class ConfigGetter extends JavaPlugin {
	public static String PREFIX = Main.plugin.getConfig().getString("language.prefix").replace('&', '§');
	public static String CONFIG_RELOADED = Main.plugin.getConfig().getString("language.configReloaded").replace('&', '§');
	public static String VERSION = Main.plugin.getConfig().getString("version").replace('&', '§');
	public static String ONLY_PLAYER_COMMAND = Main.plugin.getConfig().getString("language.onlyPlayerCommand").replace('&', '§');
	public static String NO_PERMISSION = Main.plugin.getConfig().getString("language.noPermission").replace('&', '§');
	public static String TOO_MANY_ARGUMENTS = Main.plugin.getConfig().getString("language.tooManyArguments").replace('&', '§');
	public static String PLAYER_REQUIRED = Main.plugin.getConfig().getString("language.playerRequired").replace('&', '§');
	public static String INVALID_ARGUMENT = Main.plugin.getConfig().getString("language.invalidArgument").replace('&', '§');
	public static String GLOBAL_CHEST_TITLE = Main.plugin.getConfig().getString("language.globalChestTitle").replace('&', '§');
	public static String SPAWN_SELF = Main.plugin.getConfig().getString("language.spawnSelf").replace('&', '§');
	public static String SPAWN_YOU_OTHERS = Main.plugin.getConfig().getString("language.spawnYouOthers").replace('&', '§');
	public static String SPAWN_OTHERS_YOU = Main.plugin.getConfig().getString("language.spawnOthersYou").replace('&', '§');
	public static String DISCORD_MSG = Main.plugin.getConfig().getString("language.discordMsg").replace('&', '§');
	public static String FREE_FALL_MSG = Main.plugin.getConfig().getString("language.freefallMsg").replace('&', '§');
}
