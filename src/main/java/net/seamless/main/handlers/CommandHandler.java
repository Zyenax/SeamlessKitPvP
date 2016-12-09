package net.seamless.main.handlers;

import net.seamless.main.Main;
import net.seamless.main.utils.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandHandler implements Listener, CommandExecutor {
	
	@SuppressWarnings("unused")
	private Main plugin;

	public CommandHandler(Main hub) {
		this.plugin = hub;
	}

	@EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
            if (e.getMessage().startsWith("/reload")) {
                    e.getPlayer().sendMessage(Utils.color("&cThis command has been blocked by a server admin."));
                    e.setCancelled(true);
            }
    }
	
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		Player player = (Player) sender;
		if(sender.equals(player)){
			if (command.getName().equalsIgnoreCase("spawn")){
				//Location loc = new Location(Bukkit.getWorld("world"), 763.5, 32, 441.5, -90, 0);
				//player.teleport(loc);
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

	/*public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		Player player = (Player) sender;
		if(sender.equals(player)){
			if (command.getName().equalsIgnoreCase("runes")){
				if(sender.hasPermission("runes.use")){
				if(args.length == 0){
					sender.sendMessage(Utils.color("&a&lRUNES&b&l: &cUsage: /runes [playername] [runetype] [amount]"));
				}
			
				if(args.length == 1){
					Player target = Bukkit.getServer().getPlayer(args[0]);
						if(target == null){
							sender.sendMessage(Utils.color("&a&lRUNES&b&l: &cYou must supply an online player name!"));
							return true;
						}
						sender.sendMessage(Utils.color("&a&lRUNES&b&l: &cYou must supply a rune type!"));
					}
			
			if(args.length == 2){
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(args[1].equalsIgnoreCase("speed")){
					sender.sendMessage(Utils.color("&a&lRUNES&b&l: &cYou have given &e" + target.getName() + " &ca &espeed &crune!"));
					target.sendMessage(Utils.color("&a&lRUNES&b&l: &cYou have recieved a &espeed &crune from &e" + sender.getName()));
					target.getInventory().addItem(Utils.createItem(Material.SUGAR, 1, 0, Utils.color("&b&lSpeed Rune"), Arrays.asList(Utils.color("&7Gives you a speed 3 boost!"))));
				}else if(args[1].equalsIgnoreCase("strength")){
					target.sendMessage(Utils.color("&a&lRUNES&b&l: &cYou have recieved a &estrength &crune from &e" + sender.getName()));
					sender.sendMessage(Utils.color("&a&lRUNES&b&l: &cYou have given &e" + target.getName() + " &ca &estrength &crune!"));
					target.getInventory().addItem(Utils.createItem(Material.BLAZE_POWDER, 1, 0, Utils.color("&c&lStrength Rune"), Arrays.asList(Utils.color("&7Gives you a strength 3 boost!"))));
				}else{
					sender.sendMessage(Utils.color("&a&lRUNES&b&l: &cYou must supply a valid rune type!"));
				}
			}
			
			if(args.length == 3){
				Player target = Bukkit.getServer().getPlayer(args[0]);
				try{
					Integer amount = Integer.parseInt(args[2]);
					if(args[1].equalsIgnoreCase("speed")){
						sender.sendMessage(Utils.color("&a&lRUNES&b&l: &cYou have given &e" + target.getName() + " &ca &espeed &crune!"));
						target.sendMessage(Utils.color("&a&lRUNES&b&l: &cYou have recieved a &e" + amount + " speed &crune from &e" + sender.getName()));
						target.getInventory().addItem(Utils.createItem(Material.SUGAR, amount, 0, Utils.color("&b&lSpeed Rune"), Arrays.asList(Utils.color("&7Gives you a speed 3 boost!"))));
					}else if(args[1].equalsIgnoreCase("strength")){
						target.sendMessage(Utils.color("&a&lRUNES&b&l: &cYou have recieved a &e" + amount + " strength &crune from &e" + sender.getName()));
						sender.sendMessage(Utils.color("&a&lRUNES&b&l: &cYou have given &e" + target.getName() + " &ca &estrength &crune!"));
						target.getInventory().addItem(Utils.createItem(Material.BLAZE_POWDER, amount, 0, Utils.color("&c&lStrength Rune"), Arrays.asList(Utils.color("&7Gives you a strength 3 boost!"))));
					}else{
						sender.sendMessage(Utils.color("&a&lRUNES&b&l: &cYou must supply a valid rune type!"));
					}
				}catch (NumberFormatException e1){
					sender.sendMessage(Utils.color("&a&lRUNES&b&l: &cYou must supply a valid number!"));
				}
			}
			
			
			
			
			
				}else{
					sender.sendMessage(Utils.color("&a&lRUNES&b&l: &cYou don't have permission to do this!"));
				}
			
			
			
			}
		}
		return true;
	}*/

}
