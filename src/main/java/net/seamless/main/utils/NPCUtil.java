package net.seamless.main.utils;

import java.util.HashMap;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.seamless.main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;

public class NPCUtil implements Listener{
	
	@SuppressWarnings("unused")
	private static Main plugin;
	public NPCUtil(Main listener) {
		NPCUtil.plugin = listener;		
	}
	
	public static void createStandingNPC(Entity entity, boolean moving){
		net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity)entity).getHandle();
		NBTTagCompound tag = nmsEntity.getNBTTag();
		if(tag == null){
			tag = new NBTTagCompound();
		}
		nmsEntity.c(tag);
		if(moving == false){
			tag.setInt("NoAI", 1);
		}else{
			return;
		}
		nmsEntity.f(tag);
	}
	
	static String a = "&c&lERROR: &eEntityNPC is already made!";
	public static HashMap<Integer, Entity> EntityNPCStorage = new HashMap<Integer, Entity>();
	public static void createEntityNPC(String name1, String name2, Location loc, EntityType npctype, Integer NPCID, Integer HoloGramID1, Integer HoloGramID2){
		if(!EntityNPCStorage.containsKey(NPCID)){
			Entity npc = (Entity) loc.getWorld().spawnEntity(loc, npctype);
			NPCUtil.createStandingNPC(npc, false);
			npc.setCustomNameVisible(false);
			HoloGrams.createHoloGram(loc.add(0, 0.3, 0), Utils.color(name1), HoloGramID1, false, false, null, null, null);
			HoloGrams.createHoloGram(loc.add(0, -0.3, 0), Utils.color(name2), HoloGramID2, false, false, null, null, null);
		}else{
			ConsoleCommandSender v = Bukkit.getConsoleSender();
			v.sendMessage(Utils.color(a));
		}
	}

}
