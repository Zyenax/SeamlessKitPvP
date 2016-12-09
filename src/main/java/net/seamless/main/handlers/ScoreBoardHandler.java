package net.seamless.main.handlers;

import java.util.Map.Entry;
import java.util.UUID;

import net.seamless.main.Main;
import net.seamless.main.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class ScoreBoardHandler implements Listener{
	
	private static Main plugin;
	public ScoreBoardHandler(Main listener) {
		ScoreBoardHandler.plugin = listener;
		boardUpdater();
	}
	
public static Scoreboard board;
public static Objective objective;
	
    public static void makeScoreBoard(final Player p){
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        board = scoreboardManager.getNewScoreboard();
        objective = board.registerNewObjective("lobby","dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(Utils.color("&b&lSeamless"));
        Score score = objective.getScore(Utils.color("&6&l» &c&lKILLS"));
        score.setScore(14);
        Score score2 = objective.getScore(Utils.color("&b"));
        score2.setScore(13);
        Score score3 = objective.getScore(Utils.color(" "));
        score3.setScore(12);
        Score score4 = objective.getScore(Utils.color("&6&l» &c&lSHARDS"));
        score4.setScore(11);
        Score score5 = objective.getScore(Utils.color(ChatColor.YELLOW + "" + ChatColor.BOLD));
        score5.setScore(10);
        Score score6 = objective.getScore(Utils.color("  "));
        score6.setScore(9);
        Score score7 = objective.getScore(Utils.color("&6&l» &c&lDEATHS"));
        score7.setScore(8);
        Score score8 = objective.getScore(Utils.color(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD));
        score8.setScore(7);
        Score score9 = objective.getScore(Utils.color("   "));
        score9.setScore(6);
        Score score10 = objective.getScore(Utils.color("&6&l» &c&lLEVEL"));
        score10.setScore(5);
        Score score11 = objective.getScore(Utils.color(ChatColor.AQUA + "" + ChatColor.BOLD));
        score11.setScore(4);
        Score score12 = objective.getScore(Utils.color("    "));
        score12.setScore(3);
        Score score13 = objective.getScore(Utils.color("&6&l» &c&lWEBSITE"));
        score13.setScore(2);
        Score score14 = objective.getScore(Utils.color(ChatColor.RED + "" + ChatColor.BOLD));
        score14.setScore(1);
        
        Team team1 = board.registerNewTeam("team1");
        team1.addEntry(Utils.color("&b"));
        
        Team team2 = board.registerNewTeam("team2");
        team2.addEntry(ChatColor.YELLOW + "" + ChatColor.BOLD);

        Team team3 = board.registerNewTeam("team3");
        team3.addEntry(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD);
        
        Team team4 = board.registerNewTeam("team4");
        team4.addEntry(ChatColor.AQUA + "" + ChatColor.BOLD);
        
        Team team5 = board.registerNewTeam("team5");
        team5.addEntry(ChatColor.RED + "" + ChatColor.BOLD);
		p.setScoreboard(board);
    }
    
    
    public static void boardUpdater(){
    	Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
        		public void run() {
        			
        			
        			for (Entry<UUID, Integer> entry : CurrencyHandler.Kills.entrySet()) {
    					plugin.getConfig().set(entry.getKey() + ".Kills", entry.getValue());
    				}
        			for (Entry<UUID, Integer> entry : CurrencyHandler.Shards.entrySet()) {
    					plugin.getConfig().set(entry.getKey() + ".Shards", entry.getValue());
    				}
        			for (Entry<UUID, Integer> entry : CurrencyHandler.Deaths.entrySet()) {
    					plugin.getConfig().set(entry.getKey() + ".Deaths", entry.getValue());
    				}
        			for (Entry<UUID, Integer> entry : CurrencyHandler.Level.entrySet()) {
    					plugin.getConfig().set(entry.getKey() + ".Level", entry.getValue());
    				}
        			
        			for(final Player p : Bukkit.getOnlinePlayers()){
        				if(!(p == null)){
        					
        					
        					
        					
        			Team team1 = p.getScoreboard().getTeam("team1");
    				team1.setPrefix(Utils.color("&8&l" + "▶ "));
    				team1.setSuffix(Utils.color("&a" + CurrencyHandler.Kills.get(p.getUniqueId()).toString()));
        			
    				Team team2 = p.getScoreboard().getTeam("team2"); 
    				team2.setPrefix(Utils.color("&8&l" + "▶ "));
    				team2.setSuffix(Utils.color("&a" + CurrencyHandler.Shards.get(p.getUniqueId()).toString()));
    				
    				Team team3 = p.getScoreboard().getTeam("team3");
    				team3.setPrefix(Utils.color("&8&l" + "▶ "));
    				team3.setSuffix(Utils.color("&a" + CurrencyHandler.Deaths.get(p.getUniqueId()).toString()));
    				
    				Team team4 = p.getScoreboard().getTeam("team4");
    				team4.setPrefix(Utils.color("&8&l" + "▶ "));
    				team4.setSuffix(Utils.color("&a" + CurrencyHandler.Level.get(p.getUniqueId()).toString()));
    				
    				Team team5 = p.getScoreboard().getTeam("team5");
    				team5.setPrefix(Utils.color("&8&l" + "▶ "));
    				team5.setSuffix(Utils.color("&aSeamlessMC.net"));
        			}
        			}
        			}
            	}, 0, 5);
					//END OF SCOREBOARD DISPLAYNAME
    }
}
