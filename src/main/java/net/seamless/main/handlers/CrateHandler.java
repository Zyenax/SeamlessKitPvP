package net.seamless.main.handlers;

import java.util.HashMap;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.seamless.main.Main;
import net.seamless.main.utils.HoloGrams;
import net.seamless.main.utils.Packets;
import net.seamless.main.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CrateHandler implements Listener {
	private static Main plugin;

	public CrateHandler(Main hub) {
		CrateHandler.plugin = hub;
		updateParticles();
		startLightning();
	}
	
	final static Location loc = new Location(Bukkit.getWorld("world"), 147.5, 124, 1263.5);
	
	public void updateParticles(){
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
    		public void run() {
    			Packets.createParticleHelix(loc, 5, 4, 5, EnumParticle.CRIT);
    			Packets.createParticleHelix(loc, 5, 4, 10, EnumParticle.PORTAL);
    			Packets.createParticleHelix(loc, 5, 4, 15, EnumParticle.FIREWORKS_SPARK);
    			Packets.createParticleHelix(loc, 5, 4, 20, EnumParticle.CRIT_MAGIC);
    			Packets.createParticleHelix(loc, 5, 4, 25, EnumParticle.DRIP_LAVA);
    			Packets.createParticleHelix(loc, 5, 4, 30, EnumParticle.DRIP_WATER);
    		}
		}, 0, 40);
	}
	
	public void startLightning(){
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
    		public void run() {
    			int i = Utils.randomNum(1, 5);
    			if(i == 3){
    				Bukkit.getWorld("world").strikeLightningEffect(loc);
    			}
    		}
		}, 0, 2 * 20);
	}
	
	public static void crateHologram(){
		HoloGrams.createHoloGram(loc.add(0, 0.3, 0), Utils.color("&C&lMETEOR"), 6, false, false, null, null, null);
		HoloGrams.createHoloGram(loc.subtract(0, 0.5, 0), Utils.color("&6Coming Soon"), 9, false, false, null, null, null);
	}
	
	ItemStack item;
	public static HashMap<String, String> crateFired = new HashMap<String, String>();
	@EventHandler
	public void crateClick(final PlayerInteractEvent e){
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock() != null) {
				if (e.getClickedBlock().getType() == Material.ENDER_PORTAL_FRAME){
					e.getPlayer().sendMessage(Utils.color("&c&lComing Soon"));
					/*if(!crateFired.containsKey("inUse")){
						//CODE HERE
						crateFired.put("inUse", "true");
						Integer items = Utils.randomNum(1, 3);
						if(items.equals(1)){
							item = Utils.createItem(Material.APPLE, 1, 0, "APPLE", null);
						}else if(items.equals(2)){
							item = Utils.createItem(Material.CHEST, 1, 0, "CHEST", null);
						}else if(items.equals(3)){
							item = Utils.createItem(Material.ANVIL, 1, 0, "ANVIL", null);
						}
						Bukkit.getWorld("world").dropItemNaturally(loc, item);
						HoloGrams.renameHoloGram(Utils.color(item.getItemMeta().getDisplayName()), 9);
						
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
				    		public void run() {
				    			item.setAmount(0);
				    			crateFired.remove("inUse");
				    			HoloGrams.renameHoloGram(Utils.color("&6Right Click"), 9);
				    			e.getPlayer().sendMessage(Utils.color("&c&lThe Meteor can be used again!"));
				    		}
						}, 5 * 20);
					}else{
						e.getPlayer().sendMessage(Utils.color("&c&lMeteor is already in use!"));
					}*/
				}
			}
		}
	}

}
