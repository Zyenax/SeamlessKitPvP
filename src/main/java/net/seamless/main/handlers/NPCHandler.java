package net.seamless.main.handlers;

import java.util.HashMap;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.seamless.main.Main;
import net.seamless.main.menus.ArmorMenu;
import net.seamless.main.menus.DRewardMenu;
import net.seamless.main.menus.ItemMenu;
import net.seamless.main.menus.KitMenu;
import net.seamless.main.menus.RitzoMenu;
import net.seamless.main.menus.WeaponMenu;
import net.seamless.main.utils.HoloGrams;
import net.seamless.main.utils.NPCUtil;
import net.seamless.main.utils.Packets;
import net.seamless.main.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class NPCHandler implements Listener{
	
	private static Main plugin;
	public NPCHandler(Main listener) {
		NPCHandler.plugin = listener;
	}
	
	public static Player player;
	public static Location loc1 = new Location(Bukkit.getWorld("world"), 136.5, 121.0, 1239.5, -90, 0);
	public static Location loc2 = new Location(Bukkit.getWorld("world"), 136.5, 121.0, 1235.5, -90, 0);
	public static Location loc3 = new Location(Bukkit.getWorld("world"), 136.5, 121.0, 1231.5, -90, 0);
	public static Location loc4 = new Location(Bukkit.getWorld("world"), 136.5, 121.0, 1227.5, -90, 0);
	public static Location loc5 = new Location(Bukkit.getWorld("world"), 136.5, 121.0, 1223.5, -90, 0);
	
	
	public static Location ritzoloc = new Location(Bukkit.getWorld("world"), 147.5, 112.0, 1241.5, 180, 0);
	public static Location ritzoNameLoc = new Location(Bukkit.getWorld("world"), 163.5, 122.0, 1223.5);
	
	public static void loadNpcs(){
		Zombie npc1 = (Zombie) loc1.getWorld().spawn(loc1, Zombie.class);
		NPCUtil.createStandingNPC(npc1, false);
		npc1.setCustomNameVisible(false);
		npc1.setBaby(false);
		npc1.getEquipment().setHelmet(Utils.createSkull("Jarofdirt", null, null));
		npc1.getEquipment().setChestplate(Utils.createItem(Material.IRON_CHESTPLATE, 1, 0, null, null));
		npc1.getEquipment().setLeggings(Utils.createItem(Material.IRON_LEGGINGS, 1, 0, null, null));
		npc1.getEquipment().setBoots(Utils.createItem(Material.IRON_BOOTS, 1, 0, null, null));
		npc1.getEquipment().setItemInHand(Utils.createItem(Material.BOW, 1, 0, null, null));
		HoloGrams.createHoloGram(loc1, Utils.color("&6KITS"), 1, false, false, null, null, null);
		
		IronGolem npc2 = (IronGolem) loc2.getWorld().spawn(loc2, IronGolem.class);
		NPCUtil.createStandingNPC(npc2, false);
		npc2.setCustomNameVisible(false);
		HoloGrams.createHoloGram(loc2.add(0, 0.7, 0), Utils.color("&6ARMORSMITH"), 2, false, false, null, null, null);
		loc2.subtract(0, 0.7, 0);
		
		Enderman npc3 = (Enderman) loc3.getWorld().spawn(loc3, Enderman.class);
		NPCUtil.createStandingNPC(npc3, false);
		npc3.setCustomNameVisible(false);
		HoloGrams.createHoloGram(loc3.add(0, 0.7, 0), Utils.color("&6DAILY REWARD"), 3, false, false, null, null, null);
		loc3.subtract(0, 0.7, 0);
		
		Witch npc4 = (Witch) loc4.getWorld().spawn(loc4, Witch.class);
		NPCUtil.createStandingNPC(npc4, false);
		npc4.setCustomNameVisible(false);
		HoloGrams.createHoloGram(loc4.add(0, 0.3, 0), Utils.color("&6ITEM SHOP"), 4, false, false, null, null, null);
		loc4.subtract(0, 0.3, 0);
		
		Villager npc5 = (Villager) loc5.getWorld().spawn(loc5, Villager.class);
		NPCUtil.createStandingNPC(npc5, false);
		npc5.setCustomNameVisible(false);
		npc5.isAdult();
		npc5.setProfession(Profession.BLACKSMITH);
		HoloGrams.createHoloGram(loc5, Utils.color("&6WEAPONSMITH"), 5, false, false, null, null, null);
		
		HoloGrams.createHoloGram(ritzoNameLoc.add(0, 0.1, 0), Utils.color("&6RITZO'S CAVE"), 7, false, false, null, null, null);
	}
	
	public static void NPCNameUpdater(){
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
    		@SuppressWarnings("unused")
			public void run() {
    			for(Player player : Bukkit.getOnlinePlayers()){
    				//HoloGrams.renameHoloGram("&e" + BungeeUtil.playerCountOfServer(player, "KitPvP") + " Playing", 2);
    				//HoloGrams.renameHoloGram("&e" + BungeeUtil.playerCountOfServer(player, "RankedKPVP") + " Playing", 4);
    			}
    		}
    	}, 0, 5);
		
	}
	
	@EventHandler
	public void npcInteract(PlayerInteractEntityEvent event){
		if (event.getRightClicked().getType() == EntityType.ZOMBIE && event.getRightClicked().getLocation().equals(loc1)) {
			event.setCancelled(true);
			//OPEN KIT MENU
			KitMenu.Menu(event.getPlayer());
		}else if (event.getRightClicked().getType() == EntityType.IRON_GOLEM && event.getRightClicked().getLocation().equals(loc2)) {
			event.setCancelled(true);
			//OPEN ARMOR MENU
			ArmorMenu.Menu(event.getPlayer());
		}else if (event.getRightClicked().getType() == EntityType.ENDERMAN && event.getRightClicked().getLocation().equals(loc3)) {
			event.setCancelled(true);
			//OPEN DReward MENU
			DRewardMenu.Menu(event.getPlayer());
		}else if (event.getRightClicked().getType() == EntityType.WITCH && event.getRightClicked().getLocation().equals(loc4)) {
			event.setCancelled(true);
			//OPEN ITEM MENU
			ItemMenu.Menu(event.getPlayer());
		}else if (event.getRightClicked().getType() == EntityType.VILLAGER && event.getRightClicked().getLocation().equals(loc5)) {
			event.setCancelled(true);
			//OPEN WEAPON MENU
			WeaponMenu.Menu(event.getPlayer());
		}else if (event.getRightClicked().getType() == EntityType.SKELETON && event.getRightClicked().getLocation().equals(ritzoloc)) {
			event.setCancelled(true);
			//OPEN RITZO MENU
			RitzoMenu.Menu(event.getPlayer());
		}
	}
	
	@EventHandler
	public void npcCombust(EntityCombustEvent event){
		if(event.getEntityType() != EntityType.PLAYER){
			event.setCancelled(true);
		}
	}
	
	static Entity ritzo1;
	public static void createRitzo(){
		Skeleton ritzo = (Skeleton) ritzoloc.getWorld().spawn(ritzoloc, Skeleton.class);
		ritzo1 = ritzo;
		NPCUtil.createStandingNPC(ritzo, false);
		ritzo.setCustomNameVisible(false);
		ritzo.setSkeletonType(SkeletonType.WITHER);
		ritzo.getEquipment().setItemInHand(null);
		ritzo.getEquipment().setHelmet(Utils.createSkull("BurningFurnace", null, null));
		ritzo.getEquipment().setChestplate(Utils.createDyedItem(Material.LEATHER_CHESTPLATE, null, Color.BLACK));
		ritzo.getEquipment().setLeggings(Utils.createDyedItem(Material.LEATHER_LEGGINGS, null, Color.BLACK));
		ritzo.getEquipment().setBoots(Utils.createDyedItem(Material.LEATHER_BOOTS, null, Color.BLACK));
		HoloGrams.createHoloGram(ritzoloc.add(0, 0.5, 0), Utils.color("&6RITZO"), 8, false, false, null, null, null);
		ritzoloc.subtract(0, 0.5, 0);
	}
	
	
	static HashMap<Entity, BukkitTask> MenuMap = new HashMap<Entity, BukkitTask>();
	public static void startRitzoTimers(){
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
    		public void run() {
    			createRitzo();
    			
    			Bukkit.getServer().broadcastMessage(Utils.color("&b&lRITZO: &eI have arrived in my cave!"));
    			
    			if(!(MenuMap.containsKey(ritzo1))){
    				BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
    				MenuMap.put(ritzo1, scheduler.runTaskTimer(plugin, new Runnable() {
    	    		public void run() {
    	    			Packets.createParticleHelix(ritzoloc, 3, 2, 5, EnumParticle.PORTAL);
    	    			Packets.createParticleHelix(ritzoloc, 3, 2, 10, EnumParticle.PORTAL);
    	    			Packets.createParticleHelix(ritzoloc, 3, 2, 15, EnumParticle.PORTAL);
    	    			Packets.createParticleHelix(ritzoloc, 3, 2, 20, EnumParticle.PORTAL);
    	    			Packets.createParticleHelix(ritzoloc, 3, 2, 25, EnumParticle.PORTAL);
    	    			Packets.createParticleHelix(ritzoloc, 3, 2, 30, EnumParticle.PORTAL);
    	    		}
    			}, 0, 40));
    			}
    			
    			
    			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
    	    		public void run() {
    	    			MenuMap.get(ritzo1).cancel();
    	    			HoloGrams.removeHoloGram(8);
    	    			MenuMap.remove(ritzo1);
    	    			ritzo1.remove();
    	    		}
    			}, 300 * 20);
    		}
		}, 0, 1800 * 20);
	}

}
