package net.seamless.main;

import net.seamless.main.utils.Utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener{
	
	@SuppressWarnings("unused")
	private Main plugin;
	public PlayerQuit(Main hub) {
		this.plugin = hub;
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		event.setQuitMessage(Utils.color("&8[&c-&8] &e" + player.getName()));
	}

}
