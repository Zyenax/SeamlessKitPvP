package net.seamless.main.handlers;

import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;
import net.seamless.main.Main;
import net.seamless.main.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DamageHandler implements Listener {
	@SuppressWarnings("unused")
	private Main plugin;

	public DamageHandler(Main hub) {
		this.plugin = hub;
	}

	@EventHandler
	public void onRespawn(PlayerDeathEvent e) {
		if(e.getEntity() instanceof Player){
			if(e.getEntity().getKiller() instanceof Player){
				Player killer = e.getEntity().getKiller();
				Player player = e.getEntity();
				CurrencyHandler.giveKills(killer, 1);
				CurrencyHandler.giveDeaths(player, 1);
				CurrencyHandler.giveShards(killer, Utils.randomNum(1, 3));
				((CraftPlayer) player).getHandle().playerConnection.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));
				player.setVelocity(player.getLocation().getDirection().setX(0).setY(0).setZ(0));
				Location loc = new Location(Bukkit.getWorld("world"), 174.5, 121, 1249.5, 90, 0);
				player.teleport(loc);
			}
		}
	}
	
	@EventHandler
	  public void onEntityDamage(EntityDamageEvent e){
	   if(e.getCause().equals(DamageCause.FALL)){
		   e.setCancelled(true);
	   }
	  }

	@EventHandler
	public void noPlayerDamage(EntityDamageByEntityEvent e) {
		if(e.getEntity().getType().equals(EntityType.PLAYER)){
			if(e.getDamager().getType().equals(EntityType.PLAYER)){
				if(e.getEntity().getLocation().getY() >= 99){
					e.setCancelled(true);
					e.getDamager().sendMessage(Utils.color("&c&lYou must jump down the hole in order to pvp!"));
				}else{
					e.getEntity().getWorld().playEffect(e.getEntity().getLocation(), Effect.STEP_SOUND, 152);
					e.setCancelled(false);
				}
			}
		}else{
			e.setCancelled(true);
		}
	}

}
