package fr.marcjus.armorstand;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {
		getCommand("as").setExecutor(new CommandAs());
	}
	
	@Override
	public void onDisable() {
		
	}

}
