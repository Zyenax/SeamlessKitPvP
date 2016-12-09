package net.seamless.main.handlers;

import net.seamless.main.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockSpreadEvent;

public class BlockSpread implements Listener{

	@SuppressWarnings("unused")
	private Main plugin;
	public BlockSpread(Main listener) {
		this.plugin = listener;		
	}
	
	@EventHandler
	public void onBlockSpread(BlockSpreadEvent event) {
	event.setCancelled(true);
	}
	
}
