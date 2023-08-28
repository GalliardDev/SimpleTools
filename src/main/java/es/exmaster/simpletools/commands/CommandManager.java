package es.exmaster.simpletools.commands;

import es.exmaster.simpletools.Main;

public class CommandManager {
	public static void registerCommands() {
		Main.plugin.getCommand("spawn").setExecutor(new SpawnCommand());
		Main.plugin.getCommand("discord").setExecutor(new DiscordCommand());
		Main.plugin.getCommand("thunder").setExecutor(new LightningCommand());
		Main.plugin.getCommand("freefall").setExecutor(new FreeFallCommand());
		Main.plugin.getCommand("globalchest").setExecutor(new GlobalChestCommand());
		Main.plugin.getCommand("simpletools").setExecutor(new ReloadCommand());
		Main.plugin.getCommand("sendcoords").setExecutor(new SendCoordsCommand());
		Main.plugin.getCommand("astick").setExecutor(new AdminStickCommand());
		Main.plugin.getCommand("worldblock").setExecutor(new WorldBlockerCommand());
		Main.plugin.getCommand("lobby").setExecutor(new LobbyCommand());
		Main.plugin.getCommand("payxp").setExecutor(new ExperiencePayCommand());
	}
}
