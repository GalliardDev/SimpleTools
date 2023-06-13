package es.yoshibv.simpletools.config;

import org.bukkit.plugin.java.JavaPlugin;

import es.yoshibv.simpletools.Main;

public class ConfigGetter extends JavaPlugin {
	private static Main p = Main.plugin;
	public static String PREFIX = p.getConfig().getString("language.prefix").replace('§', '&');
	public static String CONFIG_RELOADED = p.getConfig().getString("language.configReloaded").replace('§', '&');
	public static String VERSION = p.getConfig().getString("version").replace('§', '&');
	public static String ONLY_PLAYER_COMMAND = p.getConfig().getString("language.onlyPlayerCommand").replace('§', '&');
	public static String NO_PERMISSION = p.getConfig().getString("language.noPermission").replace('§', '&');
	public static String TOO_MANY_ARGUMENTS = p.getConfig().getString("language.tooManyArguments").replace('§', '&');
	public static String PLAYER_REQUIRED = p.getConfig().getString("language.playerRequired").replace('§', '&');
	public static String INVALID_ARGUMENT = p.getConfig().getString("language.invalidArgument").replace('§', '&');
	public static String GLOBAL_CHEST_TITLE = p.getConfig().getString("language.globalChestTitle").replace('§', '&');
	public static String SPAWN_SELF = p.getConfig().getString("language.spawnSelf").replace('§', '&');
	public static String SPAWN_YOU_OTHERS = p.getConfig().getString("language.spawnYouOthers").replace('§', '&');
	public static String SPAWN_OTHERS_YOU = p.getConfig().getString("language.spawnOthersYou").replace('§', '&');
	public static String DISCORD_MSG = p.getConfig().getString("language.discordMsg").replace('§', '&');
	public static String FREE_FALL_MSG = p.getConfig().getString("language.freefallMsg").replace('§', '&');
}
