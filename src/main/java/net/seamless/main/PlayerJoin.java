package net.seamless.main;

import net.seamless.main.handlers.ScoreBoardHandler;
import net.seamless.main.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener{
	
	@SuppressWarnings("unused")
	private Main plugin;
	public PlayerJoin(Main listener) {
		this.plugin = listener;		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		if(player.hasPlayedBefore() == false){
			event.setJoinMessage(Utils.color("&8[&a+&8] &e" + player.getName() + " &b&lFIRST TIME"));
			event.getPlayer().setFoodLevel(20);
			Location loc = new Location(Bukkit.getWorld("world"), 174.5, 121, 1249.5, 90, 0);
			player.teleport(loc);
			ScoreBoardHandler.makeScoreBoard(player);
			player.setGameMode(GameMode.SURVIVAL);
		}else{
			event.setJoinMessage(Utils.color("&8[&a+&8] &e" + player.getName()));
			event.getPlayer().setFoodLevel(20);
			Location loc = new Location(Bukkit.getWorld("world"), 174.5, 121, 1249.5, 90, 0);
			player.teleport(loc);
			ScoreBoardHandler.makeScoreBoard(player);
			player.setGameMode(GameMode.SURVIVAL);
		}
	}

}
