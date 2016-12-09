package net.seamless.main;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import net.seamless.main.handlers.BlockSpread;
import net.seamless.main.handlers.BuildHandler;
import net.seamless.main.handlers.CommandHandler;
import net.seamless.main.handlers.CrateHandler;
import net.seamless.main.handlers.CurrencyHandler;
import net.seamless.main.handlers.DamageHandler;
import net.seamless.main.handlers.HungerHandler;
import net.seamless.main.handlers.NPCHandler;
import net.seamless.main.handlers.PadHandler;
import net.seamless.main.handlers.ScoreBoardHandler;
import net.seamless.main.handlers.WeatherHandler;
import net.seamless.main.menus.ArmorMenu;
import net.seamless.main.menus.DRewardMenu;
import net.seamless.main.menus.ItemMenu;
import net.seamless.main.menus.KitMenu;
import net.seamless.main.menus.RitzoMenu;
import net.seamless.main.menus.WeaponMenu;
import net.seamless.main.utils.BungeeUtil;
import net.seamless.main.utils.HashMapStorage;
import net.seamless.main.utils.HoloGrams;
import net.seamless.main.utils.NPCUtil;
import net.seamless.main.utils.Packets;
import net.seamless.main.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Main extends JavaPlugin implements Listener, PluginMessageListener{
	
	public static String GetServer;
	public static String[] serverList;
	
	public void onEnable(){
		this.saveConfig();
		registerListeners();
		registerCommands();
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		console.sendMessage(Utils.color("&a&lThe kitpvp plugin has been enabled!"));
		Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this,"BungeeCord");
	    Bukkit.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
	    List<Entity> entities = Bukkit.getWorld("world").getEntities();
		for ( Entity entity : entities){
			if((entity.getType() != EntityType.PLAYER)){
				entity.remove();
			}
		}
		for(Player p : Bukkit.getOnlinePlayers()){
			p.setGameMode(GameMode.ADVENTURE);
		}
		NPCHandler.loadNpcs();
		NPCHandler.NPCNameUpdater();
		NPCHandler.startRitzoTimers();
		CrateHandler.crateHologram();
	}
	
	public void onDisable(){
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		console.sendMessage(Utils.color("&a&lThe kitpvp plugin has been disabled!"));
		 List<Entity> entities = Bukkit.getWorld("world").getEntities();
			for ( Entity entity : entities){
				if((entity.getType() != EntityType.PLAYER)){
					entity.remove();
				}
			}
			for (Entry<UUID, Integer> entry : CurrencyHandler.Shards.entrySet()) {
				getConfig().set(entry.getKey() + ".Shards", entry.getValue());
			}
	    	for (Entry<UUID, Integer> entry : CurrencyHandler.Kills.entrySet()) {
				getConfig().set(entry.getKey() + ".Kills", entry.getValue());
			}
	    	for (Entry<UUID, Integer> entry : CurrencyHandler.Deaths.entrySet()) {
				getConfig().set(entry.getKey() + ".Deaths", entry.getValue());
			}
	    	for (Entry<UUID, Integer> entry : CurrencyHandler.Level.entrySet()) {
				getConfig().set(entry.getKey() + ".Level", entry.getValue());
			}
	        saveConfig();
	}
	
	public void registerListeners(){
		PluginManager manager = Bukkit.getServer().getPluginManager();
		manager.registerEvents(new PlayerJoin(this), this);
		manager.registerEvents(new PlayerQuit(this), this);
		manager.registerEvents(new BuildHandler(this), this);
		manager.registerEvents(new HungerHandler(this), this);
		manager.registerEvents(new ScoreBoardHandler(this), this);
		manager.registerEvents(new WeatherHandler(this), this);
		manager.registerEvents(new BungeeUtil(this), this);
		manager.registerEvents(new HashMapStorage(this), this);
		manager.registerEvents(new HoloGrams(this), this);
		manager.registerEvents(new NPCUtil(this), this);
		manager.registerEvents(new Packets(this), this);
		manager.registerEvents(new Utils(this), this);
		manager.registerEvents(new DamageHandler(this), this);
		manager.registerEvents(new BlockSpread(this), this);
		manager.registerEvents(new KitMenu(this), this);
		manager.registerEvents(new ArmorMenu(this), this);
		manager.registerEvents(new DRewardMenu(this), this);
		manager.registerEvents(new ItemMenu(this), this);
		manager.registerEvents(new RitzoMenu(this), this);
		manager.registerEvents(new WeaponMenu(this), this);
		manager.registerEvents(new PadHandler(this), this);
		manager.registerEvents(new CommandHandler(this), this);
		manager.registerEvents(new NPCHandler(this), this);
		manager.registerEvents(new CrateHandler(this), this);
		manager.registerEvents(new CurrencyHandler(this), this);
	}
	
	public void registerCommands(){
		getCommand("spawn").setExecutor(new CommandHandler(this));
	}
	
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
	    if (!channel.equals("BungeeCord")) {
	      return;
	    }
	    try{
	    	DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
		    String subchannel = in.readUTF();
	    if (subchannel.equals("PlayerCount")) {
	    	String PlayerCountServer = in.readUTF();
	    	Integer playercount = in.readInt();
	    	HashMapStorage.PlayerCount.remove(PlayerCountServer);
	    	HashMapStorage.PlayerCount.put(PlayerCountServer, playercount);
        } else if (subchannel.equals("GetServers")) {
        	serverList = in.readUTF().split("\n");
        } else if (subchannel.equals("GetServer")) {
            // Example: GetServer subchannel
        	GetServer = in.readUTF();
        }
	    }catch (Exception e){
	    	e.printStackTrace();
	    }
	  }

}
