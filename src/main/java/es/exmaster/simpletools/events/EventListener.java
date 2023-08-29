package es.exmaster.simpletools.events;

import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
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
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import es.exmaster.simpletools.Main;
import es.exmaster.simpletools.common.EmojiMap;
import es.exmaster.simpletools.common.GlobalChest;
import es.exmaster.simpletools.utils.ConfigManager;
import es.exmaster.simpletools.utils.Utils;

public class EventListener {
	private static String overworld = Bukkit.getServer().getWorlds().get(0).getName();

	public static void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new Listener() {
			@EventHandler
			public void onInventoryOpen(InventoryOpenEvent event) {
				if (event.getInventory().equals(GlobalChest.getGlobalChestInventory())) {
					GlobalChest.loadChest();
				}
			}

			@EventHandler
			public void onInventoryClose(InventoryCloseEvent event) {
				if (event.getInventory().equals(GlobalChest.getGlobalChestInventory())) {
					GlobalChest.saveChest();
				}
			}

			@EventHandler
			public void onPlayerDeath(PlayerDeathEvent event) {
				if (Main.plugin.getConfig().getBoolean("config.deathTitle") == true) {
					Player player = event.getEntity();
					Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
					for (Player p : players) {
						p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
						p.sendTitle(Utils.colorCodeParser(
								Utils.placeholderParser(Main.plugin.getConfig().getString("language.deathTitleMsg"),
										List.of("%player%"), List.of(player.getName()))),
								"", 30, 30, 30);
					}
				}
			}

			@EventHandler
			public void onPlayerJoin(PlayerJoinEvent event) {
				if (Main.plugin.getConfig().getBoolean("config.joinTitle") == true) {
					Player player = event.getPlayer();
					Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
					for (Player p : players) {
						p.sendTitle(
								Utils.colorCodeParser(Main.plugin.getConfig().getString("language.joinLeaveNameFormat"))
								+ player.getName(),
								Utils.colorCodeParser(Main.plugin.getConfig().getString("language.joinTitleMsg")), 30,
								30, 30);
					}
				}
			}

			@EventHandler
			public void onPlayerLeave(PlayerQuitEvent event) {
				if (Main.plugin.getConfig().getBoolean("config.leaveTitle") == true) {
					Player player = event.getPlayer();
					Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
					for (Player p : players) {
						p.sendTitle(
								Utils.colorCodeParser(Main.plugin.getConfig().getString("language.joinLeaveNameFormat"))
								+ player.getName(),
								Utils.colorCodeParser(Main.plugin.getConfig().getString("language.leaveTitleMsg")), 30,
								30, 30);
					}
				}
			}

			@EventHandler
			public void onRightClick(PlayerInteractEvent event) {
				if (Main.plugin.getConfig().getBoolean("config.harvestOnRightClick") == true) {
					Block b = event.getClickedBlock();
					Player p = event.getPlayer();
					if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && b.getType().equals(Material.WHEAT)) {
						if (b.getBlockData().getAsString().contains("age=7")) {
							int n = (int) ((Math.random() + 1) * 2.25);
							b.setBlockData(Bukkit.createBlockData("minecraft:wheat[age=0]"));
							p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.WHEAT, n));
						}
					}
					if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && b.getType().equals(Material.POTATOES)) {
						if (b.getBlockData().getAsString().contains("age=7")) {
							int n = (int) ((Math.random() + 1) * 2.25);
							b.setBlockData(Bukkit.createBlockData("minecraft:potatoes[age=0]"));
							p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.POTATO, n));
						}
					}
					if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && b.getType().equals(Material.CARROTS)) {
						if (b.getBlockData().getAsString().contains("age=7")) {
							int n = (int) ((Math.random() + 1) * 2.25);
							b.setBlockData(Bukkit.createBlockData("minecraft:carrots[age=0]"));
							p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.CARROT, n));
						}
					}
					if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && b.getType().equals(Material.BEETROOTS)) {
						if (b.getBlockData().getAsString().contains("age=3")) {
							int n = (int) ((Math.random() + 1) * 2.75);
							b.setBlockData(Bukkit.createBlockData("minecraft:beetroots[age=0]"));
							p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.BEETROOT, n));
						}
					}
					if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && b.getType().equals(Material.COCOA)) {
						if (b.getBlockData().getAsString().contains("age=2")) {
							int n = (int) ((Math.random() + 1) * 2.25);
							b.setBlockData(Bukkit.createBlockData("minecraft:cocoa[age=0]"));
							p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.COCOA_BEANS, n));
						}
					}
					if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
							&& b.getType().equals(Material.TORCHFLOWER_CROP)) {
						if (b.getBlockData().getAsString().contains("age=1")) {
							b.setType(Material.AIR);
							p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.TORCHFLOWER, 1));
						}
					}
					if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
							&& b.getType().equals(Material.PITCHER_CROP)) {
						if (b.getBlockData().getAsString().contains("age=4")) {
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
				if (iMeta instanceof org.bukkit.inventory.meta.Damageable) {
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
						if (damage + 2 <= maxdamage) {
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
						if (damage + 2 <= maxdamage) {
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
					if (r < 0.10) {
						e.remove();
						p.playSound(p.getLocation(), Sound.ENTITY_SHEEP_SHEAR, 1, 1);
						p.playEffect(EntityEffect.FIREWORK_EXPLODE);
						p.getWorld().dropItemNaturally(e.getLocation(), new ItemStack(Material.VINE, n));
						p.getWorld().dropItemNaturally(e.getLocation(), new ItemStack(Material.GUNPOWDER, n));
						double r2 = Math.random();
						System.out.println("Número random 2: " + r2);
						if (r2 < 0.30) {
							p.getWorld().dropItemNaturally(e.getLocation(), new ItemStack(Material.CREEPER_HEAD, 1));
						}
						if (damage + 2 <= maxdamage) {
							dMeta.setDamage(damage + 2);
							i.setItemMeta(dMeta);
						}
					} else {
						if (damage + 1 <= maxdamage) {
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
						Skeleton skeleton = (Skeleton) e.getLocation().getWorld().spawnEntity(e.getLocation(),
								EntityType.SKELETON);
						EntityEquipment equipment = skeleton.getEquipment();
						equipment.setItemInMainHand(null);
						p.getWorld().dropItemNaturally(e.getLocation(), new ItemStack(Material.ROTTEN_FLESH, n));
						if (damage + 2 <= maxdamage) {
							dMeta.setDamage(damage + 2);
							i.setItemMeta(dMeta);
						}

					}
				}
				if (event.getRightClicked() instanceof Skeleton && event.getHand().equals(EquipmentSlot.HAND) && event
						.getPlayer().getItemInHand().getType().equals(new ItemStack(Material.ROTTEN_FLESH).getType())
						&& amount >= 15) {
					e.remove();
					i.setAmount(amount - 15);
					Zombie zombie = (Zombie) e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.ZOMBIE);
					EntityEquipment equipment = zombie.getEquipment();
					equipment.setItemInMainHand(new ItemStack(Material.BOW));
				}
				if (event.getRightClicked() instanceof Pillager && event.getHand().equals(EquipmentSlot.HAND)
						&& event.getPlayer().getItemInHand().getType()
						.equals(new ItemStack(Material.TOTEM_OF_UNDYING).getType())) {
					i.setAmount(0);
					double n = Math.random();
					System.out.println("Número random: " + n);
					if (n < 0.15) {
						e.remove();
						Villager villager = (Villager) e.getLocation().getWorld().spawnEntity(e.getLocation(),
								EntityType.VILLAGER);
						villager.setBaby();
						p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 1, 1);
						p.playEffect(EntityEffect.TOTEM_RESURRECT);
					} else {

					}

				}
			}

			@SuppressWarnings("deprecation")
			@EventHandler
			public void onEntityLeftClick(EntityDamageByEntityEvent event) {
				if (event.getDamager().getType() == EntityType.PLAYER) {
					Player player = (Player) event.getDamager();
					if (player.getItemInHand().getType().equals(Material.STICK) && player.getItemInHand().getItemMeta()
							.toString().contains("enchants={DAMAGE_ALL=18000}")) {
						if (event.getEntity() instanceof LivingEntity) {
							((LivingEntity) event.getEntity()).setHealth(0);
						}
					}
				}
			}

			@EventHandler
			public void onCampfireCook(BlockCookEvent event) {
				if (event.getBlock().getType() == Material.CAMPFIRE) {
					System.out.println("Es hoguera");
					if (event.getSource().getType() == Material.ROTTEN_FLESH) {
						System.out.println("Es rotten flesh");
						event.setResult(new ItemStack(Utils.getMaterialWithProb()));
						System.out.println("Se ha escogido el item random");
					}
				}
			}

			@EventHandler
			public void onBlockedWorldEnter(PlayerChangedWorldEvent event) {
				Player player = event.getPlayer();
				String world = player.getWorld().getName();

				ConfigManager worldBlockerConfigManager = new ConfigManager(Main.plugin, "blockedWorlds.yml");
				worldBlockerConfigManager.reloadConfig();

				List<String> blockedWorlds = worldBlockerConfigManager.getConfig().getStringList("blockedWorlds");

				if (blockedWorlds.contains(world)) {
					Location spawnLoc = Bukkit.getWorld(overworld).getSpawnLocation();
					double xSpawn = spawnLoc.getBlockX() + 0.5;
					double ySpawn = spawnLoc.getBlockY();
					double zSpawn = spawnLoc.getBlockZ() + 0.5;

					Location loc = new Location(Bukkit.getWorld(overworld), xSpawn, ySpawn, zSpawn);
					player.teleport(loc);

					player.sendMessage(Utils.colorCodeParser(
							Main.PREFIX + " " + Main.plugin.getConfig().getString("language.worldIsBlocked")));
				}
			}

			@EventHandler
			public void onChatMessage(AsyncPlayerChatEvent event) {
				EmojiMap<String, String> emojisByName = new EmojiMap<>();
				String msg = event.getMessage();
				for (String s : emojisByName.keySet()) {
					if (msg.contains(s)) {
						msg = msg.replace(s, emojisByName.get(s));
					}
				}
				event.setMessage(msg);
			}

			@EventHandler
			public void onBlockPlace(BlockPlaceEvent event) {
				if(Main.plugin.getConfig().getBoolean("autoItemRefill") == true) {
					ItemStack item = event.getItemInHand();
					Material material = event.getBlockPlaced().getType();
					if (item.getAmount() == 1
							&& event.getPlayer().getInventory().getItem(event.getHand()).getType().equals(material)) {
						Utils.refillItem(event.getPlayer(), material, event.getHand());
					}
				}
			}

		}, Main.plugin);
	}

	
}
