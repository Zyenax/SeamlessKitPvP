package net.seamless.main.handlers;

import net.seamless.main.Main;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherHandler implements Listener {
	@SuppressWarnings("unused")
	private Main plugin;

	public WeatherHandler(Main hub) {
		this.plugin = hub;
	}

	@EventHandler
	  public void weatherChange(WeatherChangeEvent event)
	  {
	    if (event.toWeatherState()) {
	        World world = event.getWorld();
	          event.setCancelled(true);
	          world.setStorm(false);
	          world.setThundering(false);
	          world.setWeatherDuration(0);
	    }
	  }

}
