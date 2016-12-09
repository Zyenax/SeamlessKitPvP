package net.seamless.main.handlers;

import net.seamless.main.Main;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildHandler implements Listener {
	@SuppressWarnings("unused")
	private Main plugin;

	public BuildHandler(Main hub) {
		this.plugin = hub;
	}

	@EventHandler
	public void onBreakEvent(BlockBreakEvent e) {
		if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
			e.setCancelled(false);
		} else
			e.setCancelled(true);
	}

	@EventHandler
	public void onPlaceEvent(BlockPlaceEvent e) {
		if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
			e.setCancelled(false);
		} else
			e.setCancelled(true);
		e.getPlayer().updateInventory();
	}

}
