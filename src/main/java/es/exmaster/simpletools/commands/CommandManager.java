package es.exmaster.simpletools.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;

import dev.dejvokep.boostedyaml.block.implementation.Section;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import es.exmaster.simpletools.SimpleTools;
import es.exmaster.simpletools.tasks.LocationTracker;
import es.exmaster.simpletools.utils.ConfigWrapper;
import es.exmaster.simpletools.utils.CustomConfigManager;
import es.exmaster.simpletools.utils.GlobalChest;
import es.exmaster.simpletools.utils.Utils;
import net.md_5.bungee.api.ChatColor;

public class CommandManager {
	private static ConfigWrapper config = SimpleTools.getConf();
	
	public static String PREFIX = Utils.colorCodeParser(config.getString("language.prefix"));
	
	private static Argument<?> players = new PlayerArgument(config.getString("language.player"))
			.replaceSuggestions(ArgumentSuggestions.strings(info -> Bukkit.getOnlinePlayers().stream()
					.map(x -> x.getName()).toList().toArray(new String[Bukkit.getOnlinePlayers().size()])));
	
	private static Argument<?> playersOptional = new PlayerArgument(config.getString("language.player"))
			.replaceSuggestions(ArgumentSuggestions.strings(info -> Bukkit.getOnlinePlayers().stream()
					.map(x -> x.getName()).toList().toArray(new String[Bukkit.getOnlinePlayers().size()])));

	private static Argument<?> levels = new IntegerArgument(config.getString("language.levels"));
	
	private static Argument<?> times = new IntegerArgument(config.getString("language.times"), 0, 10);
	
	private static Argument<?> worlds = new StringArgument(config.getString("language.world"))
			.replaceSuggestions(ArgumentSuggestions.strings(info -> Bukkit.getWorlds().stream().map(x -> x.getName())
					.toList().toArray(new String[Bukkit.getWorlds().size()])));
				
	public static void registerCommands() {
		// SIMPLETOOLS COMMAND
		new CommandAPICommand("simpletools")
		.withAliases("st")
		.withFullDescription("Base/Info command")
		.withShortDescription("Base/Info command")
		.executesPlayer((sender, args) -> {
					sender.sendMessage(Utils.colorCodeParser(config.getString("language.prefix")) + " "
							+ "§7Developed with §x§e§2§0§0§3§f❤ §7by §x§f§f§3§f§1§fExceptionMaster");
		})
		.register();
		
		// ASTICK COMMAND
		new CommandAPICommand("astick")
		.withFullDescription(config.getString("language.astickDescription"))
		.withPermission("simpletools.astick")
		.withShortDescription(config.getString("language.astickDescription"))
		.executesPlayer((sender, args) -> {
			if (args.count() > 0) {
				sender.sendMessage(PREFIX + " " + 
						Utils.colorCodeParser(config.getString("language.tooManyArguments")));
			}
			if(sender.hasPermission("simpletools.adminstick")) {
				sender.getInventory().addItem(Utils.getPalo());
				} else {
				sender.sendMessage(PREFIX + " " + 
						Utils.colorCodeParser(config.getString("language.noPermission")));
			}
		})
		.register();
		
		// DISCORD COMMAND
		new CommandAPICommand("discord")
		.withFullDescription(config.getString("language.discordDescription"))
		.withPermission("simpletools.discord")
		.withShortDescription(config.getString("language.discordDescription"))
		.executesPlayer((sender, args) -> {
			if (args.count() > 0) {
				sender.sendMessage(PREFIX + " " + 
						Utils.colorCodeParser(config.getString("language.tooManyArguments")));
			}
			Player player = (Player) sender;
			if(player.hasPermission("simpletools.discord")) {
				sender.sendMessage(PREFIX + " " + Utils.placeholderParser(
						Utils.colorCodeParser(config.getString("language.discordMsg")),
						List.of("%sender%"),
						List.of(sender.getName())));
				} else {
				sender.sendMessage(PREFIX + " " + 
						Utils.colorCodeParser(config.getString("language.noPermission")));
			}
		})
		.register();
		
		// PAYXP COMMAND
		new CommandAPICommand("payxp")
		.withArguments(players, levels)
		.withFullDescription(config.getString("language.payxpDescription"))
		.withPermission("simpletools.payxp")
		.withShortDescription(config.getString("language.payxpDescription"))
		.executesPlayer((sender, args) -> {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Utils.colorCodeParser(config.getString("language.onlyPlayerCommand")));
			}

			Player player = (Player) sender;
			Player victim = null;
			Integer cantidad = Integer.valueOf(args.getRaw(1));
			
			if (player.hasPermission("simpletools.payxp")) {
				if(args.count() > 2) {
					sender.sendMessage(PREFIX + " " + 
							Utils.colorCodeParser(config.getString("language.tooManyArguments")));
				}
				if(player.getLevel()>0) {
					try {
						victim = Bukkit.getPlayer(args.getRaw(0));
					} catch(Exception e) {
						sender.sendMessage(Utils.colorCodeParser(config.getString("language.prefix")) + " " + 
								Utils.colorCodeParser(config.getString("language.playerRequired")));
					}
					player.setLevel(player.getLevel()-cantidad);
					victim.setLevel(victim.getLevel()+cantidad);
					victim.sendMessage(Utils.placeholderParser(PREFIX + " " + 
								Utils.colorCodeParser(config.getString("language.youGotPaidXP")),
								List.of("%player%","%amount%"),
								List.of(player.getName(),cantidad.toString())));
					player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					victim.playSound(victim, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
				} else {
					sender.sendMessage(PREFIX + " " + 
							Utils.colorCodeParser(config.getString("language.notEnoughLevels")));
				}
			} else {
				sender.sendMessage(PREFIX + " " + 
						Utils.colorCodeParser(config.getString("language.noPermission")));
			}
		})
		.register();
		
		//FREEFALL COMMAND
		new CommandAPICommand("freefall")
		.withArguments(players)
		.withFullDescription(config.getString("language.freefallDescription"))
		.withPermission("simpletools.freefall")
		.withShortDescription(config.getString("language.freefallDescription"))
		.executesPlayer((sender, args) -> {
			if (args.count() > 1) {
				sender.sendMessage(PREFIX + " " + 
						Utils.colorCodeParser(config.getString("language.tooManyArguments")));
			}
			if (sender.hasPermission("simpletools.freefall")) {
				if (args.count() == 0) {
					sender.sendMessage(PREFIX + " " +
							Utils.colorCodeParser(config.getString("language.playerRequired")));
				}
				Player player = Bukkit.getServer().getPlayer(args.getRaw(0));
				double xFreeFall = player.getLocation().getX();
				double yFreeFall = player.getLocation().getY() + 200;
				double zFreeFall = player.getLocation().getZ();
				Location freeFallCoords = new Location(player.getWorld(), xFreeFall, yFreeFall, zFreeFall);
				player.teleport(freeFallCoords);
				sender.sendMessage(Utils.colorCodeParser(config.getString("language.freefallMsg")));
			} else {
				sender.sendMessage(PREFIX + " " + 
						Utils.colorCodeParser(config.getString("language.noPermission")));
			}
		})
		.register();
		
		//GLOBALCHEST COMMAND
		new CommandAPICommand("globalchest")
		.withOptionalArguments(playersOptional.withPermission("simpletools.globalchest.others"))
		.withFullDescription(config.getString("language.globalchestDescription"))
		.withPermission("simpletools.globalchest")
		.withShortDescription(config.getString("language.globalchestDescription"))
		.executesPlayer((sender, args) -> {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Utils.colorCodeParser(config.getString("language.onlyPlayerCommand")));
			}
			if (args.count() > 1) {
				sender.sendMessage(PREFIX + " " + 
						Utils.colorCodeParser(config.getString("language.tooManyArguments")));
			}
			if (sender instanceof Player && sender.hasPermission("simpletools.globalchest")) {
				if (args.count() == 0) {
					Player player = (Player) sender;
					player.openInventory(GlobalChest.getInv());
				} else if (args.count() == 1 && sender.hasPermission("simpletools.globalChest.others")) {
					Player player = Bukkit.getServer().getPlayer(args.getRaw(0));
					player.openInventory(GlobalChest.getInv());
				}
			} else {
				sender.sendMessage(PREFIX + " " + 
						Utils.colorCodeParser(config.getString("language.noPermission")));
			}
		})
		.register();
		
		//THUNDER COMMAND
		new CommandAPICommand("thunder")
		.withArguments(players, times)
		.withFullDescription(config.getString("language.thunderDescription"))
		.withPermission("simpletools.thunder")
		.withShortDescription(config.getString("language.thunderDescription"))
		.executesPlayer((sender, args) -> {
			if (args.count() > 2) {
				sender.sendMessage(PREFIX + " " + 
						Utils.colorCodeParser(config.getString("language.tooManyArguments")));
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
					sender.sendMessage(PREFIX + " " + 
							Utils.colorCodeParser(config.getString("language.playerRequired")));
				}
			} else {
				sender.sendMessage(PREFIX + " " + 
						Utils.colorCodeParser(config.getString("language.noPermission")));
			}
		})
		.register();
		
		//LOBBY COMMAND
		new CommandAPICommand("lobby")
		.withOptionalArguments(playersOptional.withPermission("simpletools.lobby.others"))
		.withFullDescription(config.getString("language.lobbyDescription"))
		.withPermission("simpletools.lobby")
		.withShortDescription(config.getString("language.lobbyDescription"))
		.executesPlayer((sender, args) -> {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Utils.colorCodeParser(config.getString("language.onlyPlayerCommand")));
			}

			Player player = (Player) sender;
			if (player.hasPermission("simpletools.lobby")) {
				if(Bukkit.getServer().getWorld(config.getString("config.lobby"))!=null) {
					World lobby = Bukkit.getWorld(config.getString("config.lobby"));
					double xSpawn = lobby.getSpawnLocation().getBlockX() + 0.500;
					double ySpawn = lobby.getSpawnLocation().getBlockY();
					double zSpawn = lobby.getSpawnLocation().getBlockZ() + 0.500;
					if (args.count() == 0) {
						Location spawnCoords = new Location(lobby, xSpawn, ySpawn, zSpawn);
						player.teleport(spawnCoords);
						sender.sendMessage(PREFIX + " " + 
								Utils.colorCodeParser(config.getString("language.lobbySelf")));
					} else if (args.count() >= 1) {
						if (player.hasPermission("simpletools.spawn.others")) {
							Player victim = Bukkit.getServer().getPlayer(args.getRaw(0));
							Location spawnCoords = new Location(lobby, xSpawn, ySpawn, zSpawn);
							victim.teleport(spawnCoords);
							sender.sendMessage(PREFIX + " " + 
									Utils.placeholderParser(
											Utils.colorCodeParser(config.getString("language.lobbyYouOthers")),
											List.of("%victim%"),
											List.of(victim.getName())));

							victim.sendMessage(PREFIX + " " + 
									Utils.placeholderParser(
											Utils.colorCodeParser(config.getString("language.lobbyOthersYou")),
											List.of("%sender%"),
											List.of(sender.getName())));
						} else {
							sender.sendMessage(PREFIX + " " + 
									Utils.colorCodeParser(config.getString("language.noPermission")));
						}
					}
				} else {
					sender.sendMessage(PREFIX + " " + 
							Utils.colorCodeParser(config.getString("language.noLobby")));
				}
				 
			} else {
				sender.sendMessage(PREFIX + " " + 
						Utils.colorCodeParser(config.getString("language.noPermission")));
			}
		})
		.register();
		
		//SPAWN COMMAND
		new CommandAPICommand("spawn")
		.withOptionalArguments(playersOptional.withPermission("simpletools.spawn.others"))
		.withFullDescription(config.getString("language.spawnDescription"))
		.withPermission("simpletools.spawn")
		.withShortDescription(config.getString("language.spawnDescription"))
		.executesPlayer((sender, args) -> {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Utils.colorCodeParser(config.getString("language.onlyPlayerCommand")));
			}

			Player player = (Player) sender;
			double xSpawn = player.getWorld().getSpawnLocation().getBlockX() + 0.500;
			double ySpawn = player.getWorld().getSpawnLocation().getBlockY();
			double zSpawn = player.getWorld().getSpawnLocation().getBlockZ() + 0.500;

			if (player.hasPermission("simpletools.spawn")) {
				if (args.count() == 0) {
					Location spawnCoords = new Location(player.getWorld(), xSpawn, ySpawn, zSpawn);
					player.teleport(spawnCoords);
					sender.sendMessage(PREFIX + " " + 
							Utils.colorCodeParser(config.getString("language.spawnSelf")));
				} else if (args.count() >= 1) {
					if (player.hasPermission("simpletools.spawn.others")) {
						Player victim = Bukkit.getServer().getPlayer(args.getRaw(0));
						Location spawnCoords = new Location(victim.getWorld(), xSpawn, ySpawn, zSpawn);
						victim.teleport(spawnCoords);
						sender.sendMessage(PREFIX + " " + 
								Utils.placeholderParser(
										Utils.colorCodeParser(config.getString("language.spawnYouOthers")),
										List.of("%victim%"),
										List.of(victim.getName())));

						victim.sendMessage(PREFIX + " " + 
								Utils.placeholderParser(
										Utils.colorCodeParser(config.getString("language.spawnOthersYou")),
										List.of("%sender%"),
										List.of(sender.getName())));
					} else {
						sender.sendMessage(PREFIX + " " + 
								Utils.colorCodeParser(config.getString("language.noPermission")));
					}
				} 
			} else {
				sender.sendMessage(PREFIX + " " + 
						Utils.colorCodeParser(config.getString("language.noPermission")));
			}		
		})
		.register();
		
		//RELOAD COMMAND
		new CommandAPICommand("streload")
		.withAliases("str")
		.withFullDescription(config.getString("language.reloadDescription"))
		.withPermission("simpletools.reload")
		.withShortDescription(config.getString("language.reloadDescription"))
		.executesPlayer((sender, args) -> {
			if (sender.hasPermission("simpletools.simpletools.reload")) {
            	
				config.reload();
				PREFIX = Utils.colorCodeParser(config.getString("language.prefix"));
            	
                sender.sendMessage(PREFIX + " " + 
                		Utils.colorCodeParser(config.getString("language.configReloaded")));
            } else if (!(sender.hasPermission("simpletools.simpletools.reload"))) {
            	sender.sendMessage(PREFIX + " " + 
            			Utils.colorCodeParser(config.getString("language.noPermission")));
            }
		})
		.register();
		
		//SENDCOORDS COMMAND
		new CommandAPICommand("sendcoords")
		.withArguments(players)
		.withFullDescription(config.getString("language.sendcoordsDescription"))
		.withPermission("simpletools.sendcoords")
		.withShortDescription(config.getString("language.sendcoordsDescription"))
		.executesPlayer((sender, args) -> {
			if (args.count() > 1) {
				sender.sendMessage(Utils.colorCodeParser(config.getString("language.prefix")) + " " + 
						Utils.colorCodeParser(config.getString("language.tooManyArguments")));
			}
			Player player = null;
			try {
				player = Bukkit.getPlayer(args.getRaw(0));
			} catch(Exception e) {
				sender.sendMessage(Utils.colorCodeParser(config.getString("language.prefix")) + " " + 
						Utils.colorCodeParser(config.getString("language.playerRequired")));
			}
			if(player.hasPermission("simpletools.sendcoords")) {
				Location loc = ((Player) sender).getLocation();
				List<String> coords = List.of(String.valueOf(loc.getBlockX()),String.valueOf(loc.getBlockY()),String.valueOf(loc.getBlockZ()));
				player.sendMessage(Utils.colorCodeParser(
						Utils.placeholderParser(
								config.getString("language.coordsMsg"),
								List.of("%sender%","%x%","%y%","%z%"),
								List.of(sender.getName(),coords.get(0),coords.get(1),coords.get(2)))));
			} else {
				sender.sendMessage(Utils.colorCodeParser(config.getString("language.prefix")) + " " + 
						Utils.colorCodeParser(config.getString("language.noPermission")));
			}
		})
		.register();
		
		//WBLOCK COMMAND
		CustomConfigManager worldBlockerConfigManager = new CustomConfigManager(SimpleTools.plugin,"blockedWorlds.yml");
		List<String> blockedWorlds = worldBlockerConfigManager.getConfig().getStringList("blockedWorlds");
		new CommandAPICommand("wblock")
		.withArguments(worlds)
		.withFullDescription(config.getString("language.blockworldDescription"))
		.withPermission("simpletools.worldblocker")
		.withShortDescription(config.getString("language.blockworldDescription"))
		.executesPlayer((sender, args) -> {
			 if (args.count() != 1) {
		            sender.sendMessage(PREFIX + " " +
		                    Utils.colorCodeParser(config.getString("language.invalidArguments")));
		        }

		        if (sender.hasPermission("simpletools.worldblocker")) {
		            String world = null;
		            
		            try {
		            	world = args.getRaw(0);
		    		} catch(Exception e) {
		    			sender.sendMessage(Utils.colorCodeParser(config.getString("language.prefix")) + " " + 
		    					Utils.colorCodeParser(config.getString("language.invalidArgument")));
		    		}
		                       
		            if (blockedWorlds.contains(world)) {
		                blockedWorlds.remove(world);
		                sender.sendMessage(PREFIX + " " +
		                        Utils.colorCodeParser(config.getString("language.worldUnblocked")));
		            } else {
		                blockedWorlds.add(world);
		                List<Player> playersInWorld = Bukkit.getWorld(world).getPlayers();
		                if(playersInWorld.size()!=0) {
		                	for(Player p:playersInWorld) {
		                		p.teleport(LocationTracker.getPlayerLocation(p));
		                	}
		                }
		                sender.sendMessage(PREFIX + " " +
		                        Utils.colorCodeParser(config.getString("language.worldBlocked")));
		            }

		            worldBlockerConfigManager.getConfig().set("blockedWorlds", blockedWorlds);
		            worldBlockerConfigManager.saveConfig();
		        } else {
		            sender.sendMessage(PREFIX + " " +
		                    Utils.colorCodeParser(config.getString("language.noPermission")));
		        }
		})
		.register();
		
		//CONFIG COMMAND
		new CommandAPICommand("stconfig")
		.withAliases("stc")
		.withPermission("simpletools.config")
		.withFullDescription(config.getString("language.stconfigDescription"))
		.withShortDescription(config.getString("language.stconfigDescription"))
		.executesPlayer((sender, args) -> {
			
			Section confSec = config.getConfig().getSection("config");
			
			Map<String,Object> values = confSec.getStringRouteMappedValues(false);
			
			int booleans = (int) values.entrySet().stream()
					.map(x->x.getValue())
					.map(x->x.toString())
					.filter(x->x.equals("true") || x.equals("false"))
					.count();
			
			int numberOfRows = (booleans / 9) + (booleans % 9 > 0 ? 1 : 0);
			
			ChestGui gui = new ChestGui(booleans >= 9 ? numberOfRows : 1, "Config");
			
			OutlinePane pane = new OutlinePane(0, 0, booleans, numberOfRows);
			
			List<String> configItemsDisplayNames = values.entrySet().stream()
					.filter(x->x.getValue().toString().equals("true") ||
							x.getValue().toString().equals("false"))
					.map(x->Utils.colorCodeParser(config.getString("language.configMenuValueName"))
							+x.getKey()).toList();
			
			List<String> configItemsLores = values.entrySet().stream()
					.filter(x->x.getValue().toString().equals("true") ||
							x.getValue().toString().equals("false"))
					.map(x->Utils.colorCodeParser(config.getString("language.configMenuValueLore")) + x.getValue().toString()).toList();
			
			List<ItemStack> configItems = new ArrayList<>();
			
			for(int x = 0; x < booleans; x++) {
				ItemStack item = new ItemStack(Material.PAPER,1);
				ItemMeta itemMeta = item.getItemMeta();
				itemMeta.setDisplayName(configItemsDisplayNames.get(x));
				itemMeta.setLore(List.of(configItemsLores.get(x)));
				item.setItemMeta(itemMeta);
				configItems.add(item);
			}
			
			List<GuiItem> guiItems = configItems.stream().map(x->new GuiItem(x,event -> {
				event.setCancelled(true);
				reloadConfigItem(event);
			})).toList();

			guiItems.stream().forEach(x->pane.addItem(x));
			gui.addPane(pane);
			gui.show(sender);
			
		})
		.register();
		
	}
	
	private static void reloadConfigItem(InventoryClickEvent event) {
	    ItemStack clickedItem = event.getCurrentItem();
	    if (clickedItem == null || clickedItem.getType() != Material.PAPER) {
	        return; 
	    }
	    
	    ItemMeta itemMeta = clickedItem.getItemMeta();
	    if (itemMeta == null || !itemMeta.hasDisplayName()) {
	        return;
	    }
	    
	    String displayName = itemMeta.getDisplayName();
	    String configKey = "config." + ChatColor.stripColor(displayName);
	    
	    boolean currentValue = config.getBoolean(configKey);
	    boolean newValue = !currentValue;
	    
	    config.getConfig().set(configKey, newValue);
	    config.save();
	    
	    itemMeta.setLore(List.of(Utils.colorCodeParser(config.getString("language.configMenuValueLore")) + newValue));
	    clickedItem.setItemMeta(itemMeta);
	    
	    if (event.getWhoClicked() instanceof Player) {
	        Player player = (Player) event.getWhoClicked();
	        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
	    }
	    
	    event.setCancelled(true);
	    event.getInventory().setItem(event.getSlot(), clickedItem);
	}
}
