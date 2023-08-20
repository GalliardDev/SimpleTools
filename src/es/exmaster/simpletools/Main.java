package es.exmaster.simpletools;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Pillager;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import es.exmaster.simpletools.commands.AdminStickCommand;
import es.exmaster.simpletools.commands.DiscordCommand;
import es.exmaster.simpletools.commands.FreeFallCommand;
import es.exmaster.simpletools.commands.GlobalChestCommand;
import es.exmaster.simpletools.commands.LightningCommand;
import es.exmaster.simpletools.commands.ReloadCommand;
import es.exmaster.simpletools.commands.SendCoordsCommand;
import es.exmaster.simpletools.commands.SpawnCommand;
import es.exmaster.simpletools.commands.WorldBlockerCommand;
import es.exmaster.simpletools.recipes.AdminStickRecipe;
import es.exmaster.simpletools.recipes.RottenFleshCampfireRecipe;
import es.exmaster.simpletools.recipes.TijerasRecipe;
import es.exmaster.utils.UpdateChecker;
import es.exmaster.utils.Utils;
import es.exmaster.utils.YAMLFileManager;

public class Main extends JavaPlugin implements Listener {
    private File itemsFile;
    private FileConfiguration items;
    private Inventory globalChestInventory;
    private File config;
    private FileConfiguration configF;
    public static Main plugin;
    private static final Integer ID = 108067;
    private static final String SPIGOT_LINK = "https://www.spigotmc.org/resources/simpletools.108067/";
    public static String PREFIX;
    
    public void onEnable() {
        super.onEnable();
        plugin = this;
        loadConfig();
        PREFIX = Utils.colorCodeParser(Main.plugin.getConfig().getString("language.prefix"));
        loadGlobalChestConfig();
        loadBlockedWorlds();
        registerEvents();
        registerCommands();
        loadGlobalChest();
        registerRecipes();
        new UpdateChecker(this, ID).getLatestVersion(version -> {
            String currentVersion = plugin.getConfig().getString("version");
            if (version.equals(currentVersion)) {
                this.getLogger().info("SimpleTools is up to date!");
            } else {
                this.getLogger().severe("SimpleTools is not up to date! You can download the last version from " + SPIGOT_LINK);
            }
        });
        this.getLogger().info("SimpleTools has been enabled!");
    }
    
    public void onDisable() {
        super.onDisable();
        saveGlobalChest();
        this.getLogger().info("SimpleTools has been disabled!");
    }

    /* =================================== */
    /* ZONA DE MÉTODOS AUXILIARES DEL MAIN */
    /* =================================== */

    @SuppressWarnings("unchecked")
	private void loadGlobalChest() {
        ConfigurationSection inventorySection = items.getConfigurationSection("inventory");
        if (inventorySection != null) {
            globalChestInventory.setContents(((List<ItemStack>) inventorySection.getList("items")).toArray(ItemStack[]::new));
        }
    }

    private void saveGlobalChest() {
        ConfigurationSection inventorySection = items.createSection("inventory");
        inventorySection.set("items", globalChestInventory.getContents());

        try {
            items.save(itemsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerCommands() {
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("thunder").setExecutor(new LightningCommand());
        getCommand("freefall").setExecutor(new FreeFallCommand());
        getCommand("globalchest").setExecutor(new GlobalChestCommand());
        getCommand("simpletools").setExecutor(new ReloadCommand());
        getCommand("sendcoords").setExecutor(new SendCoordsCommand());
        getCommand("astick").setExecutor(new AdminStickCommand());
        getCommand("worldblock").setExecutor(new WorldBlockerCommand());
    }
    
    private void registerRecipes() {
    	getServer().addRecipe(TijerasRecipe.get());
    	getServer().addRecipe(AdminStickRecipe.get());
    	getServer().addRecipe(RottenFleshCampfireRecipe.get());
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onInventoryOpen(InventoryOpenEvent event) {
                if (event.getInventory().equals(globalChestInventory)) {
                    loadGlobalChest();
                }
            }
    
            @EventHandler
            public void onInventoryClose(InventoryCloseEvent event) {
                if (event.getInventory().equals(globalChestInventory)) {
                    saveGlobalChest();
                }
            }
            
            @EventHandler
            public void onPlayerDeath(PlayerDeathEvent event) {
            	if(Main.plugin.getConfig().getBoolean("config.deathTitle")  == true) {
            		Player player = event.getEntity();
                	Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
                	for(Player p:players) {
                		p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
                		p.sendTitle(Utils.colorCodeParser(
                				Utils.placeholderParser(
                						Main.plugin.getConfig().getString("language.deathTitleMsg"),
                						List.of("%player%"),
                						List.of(player.getName()))), 
                				"", 
                				30, 30, 30);
                	}
            	}
            }
            
            @EventHandler
            public void onPlayerJoin(PlayerJoinEvent event) {
            	if(Main.plugin.getConfig().getBoolean("config.joinTitle") == true) {
            		Player player = event.getPlayer();
                	Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
                	for(Player p:players) {
                		p.sendTitle(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.joinLeaveNameFormat")) + 
                				player.getName(), 
                				Utils.colorCodeParser(Main.plugin.getConfig().getString("language.joinTitleMsg")), 
                				30, 30, 30);
                	}
            	}            	
            }
                        
            @EventHandler
            public void onPlayerLeave(PlayerQuitEvent event) {
            	if(Main.plugin.getConfig().getBoolean("config.leaveTitle") == true) {
            		Player player = event.getPlayer();
                	Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
                	for(Player p:players) {
                		p.sendTitle(Utils.colorCodeParser(Main.plugin.getConfig().getString("language.joinLeaveNameFormat")) + 
                				player.getName(), 
                				Utils.colorCodeParser(Main.plugin.getConfig().getString("language.leaveTitleMsg")), 
                				30, 30, 30);
                	}
            	}
            }
            
			@EventHandler
            public void onRightClick(PlayerInteractEvent event) {
            	if(Main.plugin.getConfig().getBoolean("config.harvestOnRightClick") == true) {
            		Block b = event.getClickedBlock();
            		Player p = event.getPlayer();                   
            		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && b.getType().equals(Material.WHEAT)) {
            			if(b.getBlockData().getAsString().contains("age=7")) {
            				int n = (int)((Math.random()+1)*2.25);
            				b.setBlockData(Bukkit.createBlockData("minecraft:wheat[age=0]"));
            				p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.WHEAT, n));            				
            			}            			            			
            		}
            		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && b.getType().equals(Material.POTATOES)) {
            			if(b.getBlockData().getAsString().contains("age=7")) {
            				int n = (int)((Math.random()+1)*2.25);
            				b.setBlockData(Bukkit.createBlockData("minecraft:potatoes[age=0]"));
            				p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.POTATO, n));
            			}            			            			
            		}
            		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && b.getType().equals(Material.CARROTS)) {
            			if(b.getBlockData().getAsString().contains("age=7")) {
            				int n = (int)((Math.random()+1)*2.25);
            				b.setBlockData(Bukkit.createBlockData("minecraft:carrots[age=0]"));
            				p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.CARROT, n));            				
            			}            			            			
            		}
            		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && b.getType().equals(Material.BEETROOTS)) {
            			if(b.getBlockData().getAsString().contains("age=3")) {
            				int n = (int)((Math.random()+1)*2.75);
            				b.setBlockData(Bukkit.createBlockData("minecraft:beetroots[age=0]"));
            				p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.BEETROOT, n));            				
            			}            			            			
            		}
            		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && b.getType().equals(Material.COCOA)) {
            			if(b.getBlockData().getAsString().contains("age=2")) {
            				int n = (int)((Math.random()+1)*2.25);
            				b.setBlockData(Bukkit.createBlockData("minecraft:cocoa[age=0]"));
            				p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.COCOA_BEANS, n));            				
            			}            			            			
            		}
            		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && b.getType().equals(Material.TORCHFLOWER_CROP)) {
            			if(b.getBlockData().getAsString().contains("age=1")) {
            				b.setType(Material.AIR);
            				p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.TORCHFLOWER, 1));            				
            			}            			            			
            		}
            		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && b.getType().equals(Material.PITCHER_CROP)) {
            			if(b.getBlockData().getAsString().contains("age=4")) {
            				b.setType(Material.AIR);
            				p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.PITCHER_PLANT, 1));            				
            			}            			            			
            		}
                }
            }      
			
			@SuppressWarnings("deprecation")
			@EventHandler
			public void onEntityRightClick(PlayerInteractEntityEvent event) {
				Player p = event.getPlayer();
				Entity e = event.getRightClicked();
				ItemStack i = p.getItemInHand();
				ItemMeta iMeta = i.getItemMeta();
				int damage = 0;
				org.bukkit.inventory.meta.Damageable dMeta = null;
				if(iMeta instanceof org.bukkit.inventory.meta.Damageable){
					dMeta = (org.bukkit.inventory.meta.Damageable) iMeta; 
					damage = dMeta.getDamage(); 
				}
				int maxdamage = i.getType().getMaxDurability(); 
				int amount = i.getAmount();
				
				 
				if (e instanceof Pig && event.getHand().equals(EquipmentSlot.HAND)
						&& event.getPlayer().getItemInHand().getType().equals(new ItemStack(Material.SHEARS).getType())
						&& iMeta.toString().contains("ItemFlags=[HIDE_ENCHANTS]")) {
					if (((Ageable) e).isAdult()) {
						int n = (int) ((Math.random() + 1) * 1.25);
						p.playSound(p.getLocation(), Sound.ENTITY_SHEEP_SHEAR, 1, 1);
						((Ageable) e).setBaby();
						p.getWorld().dropItemNaturally(e.getLocation(), new ItemStack(Material.PORKCHOP, n));
			            if(damage+2 <= maxdamage) {
			                dMeta.setDamage(damage + 2); 
			                i.setItemMeta(dMeta); 
			            }
					}
				}
				if (event.getRightClicked() instanceof Cow && event.getHand().equals(EquipmentSlot.HAND)
						&& event.getPlayer().getItemInHand().getType().equals(new ItemStack(Material.SHEARS).getType())
						&& iMeta.toString().contains("ItemFlags=[HIDE_ENCHANTS]")) {
					if (((Ageable) e).isAdult()) {
						int n = (int) ((Math.random() + 1) * 1.25);
						p.playSound(p.getLocation(), Sound.ENTITY_SHEEP_SHEAR, 1, 1);
						((Ageable) e).setBaby();
						p.getWorld().dropItemNaturally(e.getLocation(), new ItemStack(Material.BEEF, n));
						if(damage+2 <= maxdamage) {
			                dMeta.setDamage(damage + 2); 
			                i.setItemMeta(dMeta); 
			            }
					}
				}
				if (event.getRightClicked() instanceof Creeper && event.getHand().equals(EquipmentSlot.HAND)
						&& event.getPlayer().getItemInHand().getType().equals(new ItemStack(Material.SHEARS).getType())
						&& iMeta.toString().contains("ItemFlags=[HIDE_ENCHANTS]")) {
					double r = Math.random();
					int n = (int) ((Math.random() + 1) * 1.25);
					System.out.println("Número random: " + r);
					if(r<0.10) {
						e.remove();
						p.playSound(p.getLocation(), Sound.ENTITY_SHEEP_SHEAR, 1, 1);
						p.playEffect(EntityEffect.FIREWORK_EXPLODE);
						p.getWorld().dropItemNaturally(e.getLocation(), new ItemStack(Material.VINE, n));
						p.getWorld().dropItemNaturally(e.getLocation(), new ItemStack(Material.GUNPOWDER, n));
						double r2 = Math.random();
						System.out.println("Número random 2: " + r2);
						if(r2<0.30) {
							p.getWorld().dropItemNaturally(e.getLocation(), new ItemStack(Material.CREEPER_HEAD, 1));
						}
						if(damage+2 <= maxdamage) {
			                dMeta.setDamage(damage + 2); 
			                i.setItemMeta(dMeta); 
			            }
					} else {
						if(damage+1 <= maxdamage) {
			                dMeta.setDamage(damage + 1); 
			                i.setItemMeta(dMeta);
			            }
					}
					
				}
				if (event.getRightClicked() instanceof Zombie && event.getHand().equals(EquipmentSlot.HAND)
						&& event.getPlayer().getItemInHand().getType().equals(new ItemStack(Material.SHEARS).getType())
						&& iMeta.toString().contains("ItemFlags=[HIDE_ENCHANTS]")) {
					if (((Ageable) e).isAdult()) {
						int n = (int) ((Math.random() + 1) * 1.25);
						p.playSound(p.getLocation(), Sound.ENTITY_SHEEP_SHEAR, 1, 1);
						((Ageable) e).remove();
						Skeleton skeleton = (Skeleton) e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.SKELETON);
						EntityEquipment equipment = skeleton.getEquipment();
				        equipment.setItemInMainHand(null);
						p.getWorld().dropItemNaturally(e.getLocation(), new ItemStack(Material.ROTTEN_FLESH, n));
						if(damage+2 <= maxdamage) {
			                dMeta.setDamage(damage + 2);
			                i.setItemMeta(dMeta);
			            }
						
					}
				}
				if (event.getRightClicked() instanceof Skeleton && event.getHand().equals(EquipmentSlot.HAND)
						&& event.getPlayer().getItemInHand().getType().equals(new ItemStack(Material.ROTTEN_FLESH).getType())
						&& amount >= 15) {
					e.remove();
					i.setAmount(amount-15);
					Zombie zombie = (Zombie) e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.ZOMBIE);
					EntityEquipment equipment = zombie.getEquipment();
			        equipment.setItemInMainHand(new ItemStack(Material.BOW));
				}
				if (event.getRightClicked() instanceof Pillager && event.getHand().equals(EquipmentSlot.HAND)
						&& event.getPlayer().getItemInHand().getType().equals(new ItemStack(Material.TOTEM_OF_UNDYING).getType())) {
					i.setAmount(0);
					double n = Math.random();
					System.out.println("Número random: " + n);
					if(n<0.15) {
						e.remove();
						Villager villager = (Villager) e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.VILLAGER);
						villager.setBaby();
						p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 1, 1);
						p.playEffect(EntityEffect.TOTEM_RESURRECT);
					} else {
						
					}
					
				}	
			}
			
			@SuppressWarnings("deprecation")
			@EventHandler
			public void onEntityLeftClick(EntityDamageByEntityEvent  event) {
				if(event.getDamager().getType() == EntityType.PLAYER) {
					Player player = (Player) event.getDamager();
					if(player.getItemInHand().getType().equals(Material.STICK)
							&& player.getItemInHand().getItemMeta().toString().contains("enchants={DAMAGE_ALL=18000}")) {
						if (event.getEntity() instanceof LivingEntity) {
							((LivingEntity)event.getEntity()).setHealth(0);
						}
					}
				}
			}
			
			@EventHandler
			public void onCampfireCook(BlockCookEvent event) {
			    if (event.getBlock().getType() == Material.CAMPFIRE) {
			    	System.out.println("Es hoguera");
			    	if(event.getSource().getType() == Material.ROTTEN_FLESH) {
			    		System.out.println("Es rotten flesh");
			    		event.setResult(new ItemStack(Utils.getMaterialWithProb()));
			    		System.out.println("Se ha escogido el item random");
			    	}
			    }
			}
			
			@EventHandler
			public void onBlockedWorldEnter(PlayerChangedWorldEvent event) {
				Player player = event.getPlayer();
			    Location spawnLoc = Bukkit.getServer().getWorld("world").getSpawnLocation();
				double xSpawn = spawnLoc.getBlockX() + 0.500;
				double ySpawn = spawnLoc.getBlockY();
				double zSpawn = spawnLoc.getBlockZ() + 0.500;
				String world = player.getWorld().getName();
				File aux = new File(getDataFolder(), "blockedWorlds.yml");
				Boolean isBlocked = YAMLFileManager.readYAML(aux.getPath()).entrySet().stream()
						.filter(x->x.getKey().equals(world))
						.findFirst().get().getValue();
				if(isBlocked) {
					Location loc = new Location(player.getServer().getWorld("world"), xSpawn, ySpawn, zSpawn);
					player.teleport(loc);
					player.sendMessage(Utils.colorCodeParser(Main.PREFIX + " " +
							Main.plugin.getConfig().getString("language.worldBlocked")));
				}
			}
			            
        }, this);
    }

    private void loadConfig() {
        config = new File(getDataFolder(), "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }
    
    private void loadBlockedWorlds() {
        File blockedWorlds = new File(getDataFolder(), "blockedWorlds.yml");
        try {
			blockedWorlds.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void loadGlobalChestConfig() {
        itemsFile = new File(getDataFolder(), "items.yml");
        items = YamlConfiguration.loadConfiguration(itemsFile);
        globalChestInventory = GlobalChestCommand.getInv();
    }

    public void reloadPluginConfig() {
        // Let JavaPlugin do its stuff before
        super.reloadConfig();
        // Save default config into plugins/<your-plugin>/config.yml if not exists
        saveDefaultConfig();
        // Get config from config file
        configF = getConfig();
        // Put default values into it (from your jar's config.yml file)
        configF.options().copyDefaults(true);
        // Add missing / new parameters into plugins/<your-plugin>/config.yml
        saveConfig();
    }
}