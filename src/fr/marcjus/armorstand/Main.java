package fr.marcjus.armorstand;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {
		getCommand("as").setExecutor(new CommandAs());
		for(Entity ent : Bukkit.getWorld("world").getEntities()){
			if(ent instanceof ArmorStand){
				ArmorStand stand = (ArmorStand) ent;
				stand.setCustomNameVisible(false);
			}
		}
	}
	
	@Override
	public void onDisable() {
		
	}

}
