package net.seamless.main.handlers;

import net.seamless.main.Main;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PadHandler implements Listener{
	
	@SuppressWarnings("unused")
	private Main plugin;
	public PadHandler(Main listener) {
		this.plugin = listener;		
	}
	
	@EventHandler
	public void jumpPad(PlayerMoveEvent event){
		if(event.getPlayer().getLocation().getBlock().getType().equals(Material.WOOD_PLATE)){
			if(event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType().equals(Material.IRON_BLOCK)){
				event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(4).setY(2));
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENDERDRAGON_WINGS, Integer.MAX_VALUE, Integer.MAX_VALUE);
			}
		}
	}

}
