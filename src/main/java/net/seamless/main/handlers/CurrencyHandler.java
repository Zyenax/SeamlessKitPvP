package net.seamless.main.handlers;

import java.util.HashMap;
import java.util.UUID;

import net.seamless.main.Main;
import net.seamless.main.utils.Utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class CurrencyHandler implements Listener {
	
	public static HashMap<UUID, Integer> Shards = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> Kills = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> Deaths = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> Level = new HashMap<UUID, Integer>();
	
	private static Main plugin;
	public CurrencyHandler(Main listener) {
		CurrencyHandler.plugin = listener;	
	}

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!plugin.getConfig().contains(player.getUniqueId().toString())) {
        	plugin.getConfig().set(player.getUniqueId() + ".Shards", 0);
			Shards.put(player.getUniqueId(), 0);
			
			plugin.getConfig().set(player.getUniqueId() + ".Kills", 0);
			Kills.put(player.getUniqueId(), 0);
			
			plugin.getConfig().set(player.getUniqueId() + ".Deaths", 0);
			Deaths.put(player.getUniqueId(), 0);
			
			plugin.getConfig().set(player.getUniqueId() + ".Level", 0);
			Level.put(player.getUniqueId(), 0);
		} else {
			Shards.put(player.getUniqueId(), plugin.getConfig().getInt(player.getUniqueId() + ".Shards"));
			Kills.put(player.getUniqueId(), plugin.getConfig().getInt(player.getUniqueId() + ".Kills"));
			Deaths.put(player.getUniqueId(), plugin.getConfig().getInt(player.getUniqueId() + ".Deaths"));
			Level.put(player.getUniqueId(), plugin.getConfig().getInt(player.getUniqueId() + ".Level"));
		}
    }
    
    @EventHandler
    public void onDeath(EntityDeathEvent e){
    	Player player = (Player)e.getEntity();
    	if(e.getEntity().getKiller() instanceof Player){
    		if(e.getEntity() instanceof Player){
    			CurrencyHandler.giveShards(e.getEntity().getKiller(), Utils.randomNum(1, 5));
    			CurrencyHandler.giveKills(e.getEntity().getKiller(), 1);
    			CurrencyHandler.giveDeaths(player, 1);
        	}
    	}
    }

    public static void giveShards(Player player, int amount) {
        UUID uuid = (UUID)player.getUniqueId();
        Shards.put(uuid, Shards.get(uuid) + amount);
    }
    
    public static void giveKills(Player player, int amount) {
    	UUID uuid = (UUID)player.getUniqueId();
        Kills.put(uuid, Kills.get(uuid) + amount);
    }
    
    public static void giveDeaths(Player player, int amount) {
    	UUID uuid = (UUID)player.getUniqueId();
        Deaths.put(uuid, Deaths.get(uuid) + amount);
    }
    
    public static void giveLevels(Player player, int amount) {
    	UUID uuid = (UUID)player.getUniqueId();
        Level.put(uuid, Level.get(uuid) + amount);
    }

    public static void takeShards(Player player, int amount) {
    	UUID uuid = (UUID)player.getUniqueId();
        Shards.put(uuid, Shards.get(uuid) - amount);
    }
}