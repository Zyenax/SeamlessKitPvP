package net.seamless.main.utils;

import java.util.List;
import java.util.Random;

import net.seamless.main.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Utils implements Listener{
	
	@SuppressWarnings("unused")
	private static Main plugin;

	public Utils(Main hub) {
		Utils.plugin = hub;
	}

	public static String color(String string){
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	@SuppressWarnings("deprecation")
	public static void setTag(Player player, String tag)
	  {
	    Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
	    Team team = board.getTeam(player.getName());
	    if (team == null) {
	      team = board.registerNewTeam(player.getName());
	    }
	    team.setPrefix(tag);
	    team.addPlayer(player);
	    for (Player players : Bukkit.getOnlinePlayers()) {
	      players.setScoreboard(board);
	    }
	  }
	
	  public static ItemStack createDyedItem(Material leatherPiece, String displayName, Color color) {
		  ItemStack item = new ItemStack(leatherPiece);
		  LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		  meta.setDisplayName(displayName);
		  meta.setColor(Color.fromRGB(color.asRGB()));
		  item.setItemMeta(meta);
		  return item;
		  }
	
	public static int randomNum(int Low, int High){
		Random r = new Random();
		int R = r.nextInt(High-Low) + Low;
		return R;
	}
	
	public static ItemStack createItem(Material material, int amount ,int dataValue, String name,
			List<String> list) {
		ItemStack selector = new ItemStack(material, amount, (short) dataValue);
		ItemMeta selectorMeta = selector.getItemMeta();
		selectorMeta.setDisplayName(name);
		if (list != null)
			selectorMeta.setLore(list);
		selector.setItemMeta(selectorMeta);
		return selector;
	}

	public static ItemStack createSkull(String pname, String name,
			List<String> lore) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta3 = (SkullMeta) skull.getItemMeta();
		meta3.setOwner(pname);
		meta3.setDisplayName(name);
		meta3.setLore(lore);
		skull.setItemMeta(meta3);
		return skull;
	}
	
	public static void setGamemode(Player player, GameMode gamemode){
		player.setGameMode(gamemode);
	}
	
}
