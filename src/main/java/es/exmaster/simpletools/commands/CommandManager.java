package es.exmaster.simpletools.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import es.exmaster.simpletools.Main;
import es.exmaster.simpletools.tasks.LocationTracker;
import es.exmaster.simpletools.utils.ConfigManager;
import es.exmaster.simpletools.utils.Utils;

public class CommandManager {
	private static Argument<?> players = new PlayerArgument(Main.plugin.getConfig().getString("language.player"))
			.replaceSuggestions(ArgumentSuggestions.strings(info -> Bukkit.getOnlinePlayers().stream()
					.map(x -> x.getName()).toList().toArray(new String[Bukkit.getOnlinePlayers().size()])));
	
	private static Argument<?> playersOptional = new PlayerArgument(Main.plugin.getConfig().getString("language.player"))
			.replaceSuggestions(ArgumentSuggestions.strings(info -> Bukkit.getOnlinePlayers().stream()
					.map(x -> x.getName()).toList().toArray(new String[Bukkit.getOnlinePlayers().size()])));

	private static Argument<?> levels = new IntegerArgument(Main.plugin.getConfig().getString("language.levels"));
	
	private static Argument<?> times = new IntegerArgument(Main.plugin.getConfig().getString("language.times"), 0, 10);
	
	private static Argument<?> worlds = new StringArgument(Main.plugin.getConfig().getString("language.world"))
			.replaceSuggestions(ArgumentSuggestions.strings(info -> Bukkit.getWorlds().stream().map(x -> x.getName())
					.toList().toArray(new String[Bukkit.getWorlds().size()])));
	
	public static void registerCommands() {
		// ASTICK COMMAND
		new CommandAPICommand("astick")
		.withFullDescription(Main.plugin.getConfig().getString("language.astickDescription"))
		.withPermission("simpletools.astick")
		.withShortDescription(Main.plugin.getConfig().getString("language.astickDescription"))
		.executesPlayer((sender, args) -> {
			if (args.count() > 0) {
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.tooManyArguments")));
			}
			if(sender.hasPermission("simpletools.adminstick")) {
				sender.getInventory().addItem(Utils.getPalo());
				} else {
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
			}
		})
		.register();
		
		// DISCORD COMMAND
		new CommandAPICommand("discord")
		.withFullDescription(Main.plugin.getConfig().getString("language.discordDescription"))
		.withPermission("simpletools.discord")
		.withShortDescription(Main.plugin.getConfig().getString("language.discordDescription"))
		.executesPlayer((sender, args) -> {
			if (args.count() > 0) {
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.tooManyArguments")));
			}
			Player player = (Player) sender;
			if(player.hasPermission("simpletools.discord")) {
				sender.sendMessage(Main.PREFIX + " " + Utils.placeholderParser(
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.discordMsg")),
						List.of("%sender%"),
						List.of(sender.getName())));
				} else {
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
			}
		})
		.register();
		
		// PAYXP COMMAND
		new CommandAPICommand("payxp")
		.withArguments(players, levels)
		.withFullDescription(Main.plugin.getConfig().getString("language.payxpDescription"))
		.withPermission("simpletools.payxp")
		.withShortDescription(Main.plugin.getConfig().getString("language.payxpDescription"))
		.executesPlayer((sender, args) -> {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.onlyPlayerCommand")));
			}

			Player player = (Player) sender;
			Player victim = null;
			Integer cantidad = Integer.valueOf(args.getRaw(1));
			
			if (player.hasPermission("simpletools.payxp")) {
				if(args.count() > 2) {
					sender.sendMessage(Main.PREFIX + " " + 
							Utils.colorCodeParser(Main.plugin.getConfig().getString("language.tooManyArguments")));
				}
				if(player.getLevel()>0) {
					try {
						victim = Bukkit.getPlayer(args.getRaw(0));
					} catch(Exception e) {
						sender.sendMessage(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.prefix")) + " " + 
								Utils.colorCodeParser(Main.plugin.getConfig().getString("language.playerRequired")));
					}
					player.setLevel(player.getLevel()-cantidad);
					victim.setLevel(victim.getLevel()+cantidad);
					victim.sendMessage(Utils.placeholderParser(Main.PREFIX + " " + 
								Utils.colorCodeParser(Main.plugin.getConfig().getString("language.youGotPaidXP")),
								List.of("%player%","%amount%"),
								List.of(player.getName(),cantidad.toString())));
					player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					victim.playSound(victim, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
				} else {
					sender.sendMessage(Main.PREFIX + " " + 
							Utils.colorCodeParser(Main.plugin.getConfig().getString("language.notEnoughLevels")));
				}
			} else {
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
			}
		})
		.register();
		
		//FREEFALL COMMAND
		new CommandAPICommand("freefall")
		.withArguments(players)
		.withFullDescription(Main.plugin.getConfig().getString("language.freefallDescription"))
		.withPermission("simpletools.freefall")
		.withShortDescription(Main.plugin.getConfig().getString("language.freefallDescription"))
		.executesPlayer((sender, args) -> {
			if (args.count() > 1) {
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.tooManyArguments")));
			}
			if (sender.hasPermission("simpletools.freefall")) {
				if (args.count() == 0) {
					sender.sendMessage(Main.PREFIX + " " +
							Utils.colorCodeParser(Main.plugin.getConfig().getString("language.playerRequired")));
				}
				Player player = Bukkit.getServer().getPlayer(args.getRaw(0));
				double xFreeFall = player.getLocation().getX();
				double yFreeFall = player.getLocation().getY() + 200;
				double zFreeFall = player.getLocation().getZ();
				Location freeFallCoords = new Location(player.getWorld(), xFreeFall, yFreeFall, zFreeFall);
				player.teleport(freeFallCoords);
				sender.sendMessage(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.freefallMsg")));
			} else {
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
			}
		})
		.register();
		
		//GLOBALCHEST COMMAND
		new CommandAPICommand("globalchest")
		.withOptionalArguments(playersOptional.withPermission("simpletools.globalchest.others"))
		.withFullDescription(Main.plugin.getConfig().getString("language.globalchestDescription"))
		.withPermission("simpletools.globalchest")
		.withShortDescription(Main.plugin.getConfig().getString("language.globalchestDescription"))
		.executesPlayer((sender, args) -> {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.onlyPlayerCommand")));
			}
			if (args.count() > 1) {
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.tooManyArguments")));
			}
			if (sender instanceof Player && sender.hasPermission("simpletools.globalchest")) {
				if (args.count() == 0) {
					Player player = (Player) sender;
					player.openInventory(Utils.getInv());
				} else if (args.count() == 1 && sender.hasPermission("simpletools.globalChest.others")) {
					Player player = Bukkit.getServer().getPlayer(args.getRaw(0));
					player.openInventory(Utils.getInv());
				}
			} else {
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
			}
		})
		.register();
		
		//THUNDER COMMAND
		new CommandAPICommand("thunder")
		.withArguments(players, times)
		.withFullDescription(Main.plugin.getConfig().getString("language.thunderDescription"))
		.withPermission("simpletools.thunder")
		.withShortDescription(Main.plugin.getConfig().getString("language.thunderDescription"))
		.executesPlayer((sender, args) -> {
			if (args.count() > 2) {
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.tooManyArguments")));
			}
			if (sender.hasPermission("simpletools.thunder")) {
				if (args.count() == 2) {
					Player victim = Bukkit.getServer().getPlayer(args.getRaw(0));
					Integer lightningTimes = Integer.valueOf(args.getRaw(1));
					for (int i = 0; i < lightningTimes; i++) {
						Location loc = victim.getLocation();
						victim.getPlayer().getWorld().strikeLightning(loc);
						try {
							Thread.sleep(150);
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
						}
					}
				} else if(args.count() == 0){
					sender.sendMessage(Main.PREFIX + " " + 
							Utils.colorCodeParser(Main.plugin.getConfig().getString("language.playerRequired")));
				}
			} else {
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
			}
		})
		.register();
		
		//LOBBY COMMAND
		new CommandAPICommand("lobby")
		.withOptionalArguments(playersOptional.withPermission("simpletools.lobby.others"))
		.withFullDescription(Main.plugin.getConfig().getString("language.lobbyDescription"))
		.withPermission("simpletools.lobby")
		.withShortDescription(Main.plugin.getConfig().getString("language.lobbyDescription"))
		.executesPlayer((sender, args) -> {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.onlyPlayerCommand")));
			}

			Player player = (Player) sender;
			if (player.hasPermission("simpletools.lobby")) {
				if(Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("config.lobby"))!=null) {
					World lobby = Bukkit.getWorld(Main.plugin.getConfig().getString("config.lobby"));
					double xSpawn = lobby.getSpawnLocation().getBlockX() + 0.500;
					double ySpawn = lobby.getSpawnLocation().getBlockY();
					double zSpawn = lobby.getSpawnLocation().getBlockZ() + 0.500;
					if (args.count() == 0) {
						Location spawnCoords = new Location(lobby, xSpawn, ySpawn, zSpawn);
						player.teleport(spawnCoords);
						sender.sendMessage(Main.PREFIX + " " + 
								Utils.colorCodeParser(Main.plugin.getConfig().getString("language.lobbySelf")));
					} else if (args.count() >= 1) {
						if (player.hasPermission("simpletools.spawn.others")) {
							Player victim = Bukkit.getServer().getPlayer(args.getRaw(0));
							Location spawnCoords = new Location(lobby, xSpawn, ySpawn, zSpawn);
							victim.teleport(spawnCoords);
							sender.sendMessage(Main.PREFIX + " " + 
									Utils.placeholderParser(
											Utils.colorCodeParser(Main.plugin.getConfig().getString("language.lobbyYouOthers")),
											List.of("%victim%"),
											List.of(victim.getName())));

							victim.sendMessage(Main.PREFIX + " " + 
									Utils.placeholderParser(
											Utils.colorCodeParser(Main.plugin.getConfig().getString("language.lobbyOthersYou")),
											List.of("%sender%"),
											List.of(sender.getName())));
						} else {
							sender.sendMessage(Main.PREFIX + " " + 
									Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
						}
					}
				} else {
					sender.sendMessage(Main.PREFIX + " " + 
							Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noLobby")));
				}
				 
			} else {
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
			}
		})
		.register();
		
		//SPAWN COMMAND
		new CommandAPICommand("spawn")
		.withOptionalArguments(playersOptional.withPermission("simpletools.spawn.others"))
		.withFullDescription(Main.plugin.getConfig().getString("language.spawnDescription"))
		.withPermission("simpletools.spawn")
		.withShortDescription(Main.plugin.getConfig().getString("language.spawnDescription"))
		.executesPlayer((sender, args) -> {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.onlyPlayerCommand")));
			}

			Player player = (Player) sender;
			double xSpawn = player.getWorld().getSpawnLocation().getBlockX() + 0.500;
			double ySpawn = player.getWorld().getSpawnLocation().getBlockY();
			double zSpawn = player.getWorld().getSpawnLocation().getBlockZ() + 0.500;

			if (player.hasPermission("simpletools.spawn")) {
				if (args.count() == 0) {
					Location spawnCoords = new Location(player.getWorld(), xSpawn, ySpawn, zSpawn);
					player.teleport(spawnCoords);
					sender.sendMessage(Main.PREFIX + " " + 
							Utils.colorCodeParser(Main.plugin.getConfig().getString("language.spawnSelf")));
				} else if (args.count() >= 1) {
					if (player.hasPermission("simpletools.spawn.others")) {
						Player victim = Bukkit.getServer().getPlayer(args.getRaw(0));
						Location spawnCoords = new Location(victim.getWorld(), xSpawn, ySpawn, zSpawn);
						victim.teleport(spawnCoords);
						sender.sendMessage(Main.PREFIX + " " + 
								Utils.placeholderParser(
										Utils.colorCodeParser(Main.plugin.getConfig().getString("language.spawnYouOthers")),
										List.of("%victim%"),
										List.of(victim.getName())));

						victim.sendMessage(Main.PREFIX + " " + 
								Utils.placeholderParser(
										Utils.colorCodeParser(Main.plugin.getConfig().getString("language.spawnOthersYou")),
										List.of("%sender%"),
										List.of(sender.getName())));
					} else {
						sender.sendMessage(Main.PREFIX + " " + 
								Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
					}
				} 
			} else {
				sender.sendMessage(Main.PREFIX + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
			}		
		})
		.register();
		
		//RELOAD COMMAND
		new CommandAPICommand("streload")
		.withFullDescription(Main.plugin.getConfig().getString("language.reloadDescription"))
		.withPermission("simpletools.reload")
		.withShortDescription(Main.plugin.getConfig().getString("language.reloadDescription"))
		.executesPlayer((sender, args) -> {
			if (sender.hasPermission("simpletools.simpletools.reload")) {
            	
            	Main.plugin.saveConfig();
            	
                sender.sendMessage(Main.PREFIX + " " + 
                		Utils.colorCodeParser(Main.plugin.getConfig().getString("language.configReloaded")));
            } else if (!(sender.hasPermission("simpletools.simpletools.reload"))) {
            	sender.sendMessage(Main.PREFIX + " " + 
            			Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
            }
		})
		.register();
		
		//SENDCOORDS COMMAND
		new CommandAPICommand("sendcoords")
		.withArguments(players)
		.withFullDescription(Main.plugin.getConfig().getString("language.sendcoordsDescription"))
		.withPermission("simpletools.sendcoords")
		.withShortDescription(Main.plugin.getConfig().getString("language.sendcoordsDescription"))
		.executesPlayer((sender, args) -> {
			if (args.count() > 1) {
				sender.sendMessage(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.prefix")) + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.tooManyArguments")));
			}
			Player player = null;
			try {
				player = Bukkit.getPlayer(args.getRaw(0));
			} catch(Exception e) {
				sender.sendMessage(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.prefix")) + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.playerRequired")));
			}
			if(player.hasPermission("simpletools.sendcoords")) {
				Location loc = ((Player) sender).getLocation();
				List<String> coords = List.of(String.valueOf(loc.getBlockX()),String.valueOf(loc.getBlockY()),String.valueOf(loc.getBlockZ()));
				player.sendMessage(Utils.colorCodeParser(
						Utils.placeholderParser(
								Main.plugin.getConfig().getString("language.coordsMsg"),
								List.of("%sender%","%x%","%y%","%z%"),
								List.of(sender.getName(),coords.get(0),coords.get(1),coords.get(2)))));
			} else {
				sender.sendMessage(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.prefix")) + " " + 
						Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
			}
		})
		.register();
		
		//WBLOCK COMMAND
		ConfigManager worldBlockerConfigManager = new ConfigManager(Main.plugin,"blockedWorlds.yml");
		List<String> blockedWorlds = worldBlockerConfigManager.getConfig().getStringList("blockedWorlds");
		new CommandAPICommand("wblock")
		.withArguments(worlds)
		.withFullDescription(Main.plugin.getConfig().getString("language.blockworldDescription"))
		.withPermission("simpletools.worldblocker")
		.withShortDescription(Main.plugin.getConfig().getString("language.blockworldDescription"))
		.executesPlayer((sender, args) -> {
			 if (args.count() != 1) {
		            sender.sendMessage(Main.PREFIX + " " +
		                    Utils.colorCodeParser(Main.plugin.getConfig().getString("language.invalidArguments")));
		        }

		        if (sender.hasPermission("simpletools.worldblocker")) {
		            String world = null;
		            
		            try {
		            	world = args.getRaw(0);
		    		} catch(Exception e) {
		    			sender.sendMessage(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.prefix")) + " " + 
		    					Utils.colorCodeParser(Main.plugin.getConfig().getString("language.invalidArgument")));
		    		}
		                       
		            if (blockedWorlds.contains(world)) {
		                blockedWorlds.remove(world);
		                sender.sendMessage(Main.PREFIX + " " +
		                        Utils.colorCodeParser(Main.plugin.getConfig().getString("language.worldUnblocked")));
		            } else {
		                blockedWorlds.add(world);
		                List<Player> playersInWorld = Bukkit.getWorld(world).getPlayers();
		                if(playersInWorld.size()!=0) {
		                	for(Player p:playersInWorld) {
		                		p.teleport(LocationTracker.getPlayerLocation(p));
		                	}
		                }
		                sender.sendMessage(Main.PREFIX + " " +
		                        Utils.colorCodeParser(Main.plugin.getConfig().getString("language.worldBlocked")));
		            }

		            worldBlockerConfigManager.getConfig().set("blockedWorlds", blockedWorlds);
		            worldBlockerConfigManager.saveConfig();
		        } else {
		            sender.sendMessage(Main.PREFIX + " " +
		                    Utils.colorCodeParser(Main.plugin.getConfig().getString("language.noPermission")));
		        }
		})
		.register();
	}
}
