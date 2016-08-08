package com.thorindev;

import org.bukkit.plugin.java.JavaPlugin;

import com.thorindev.commands.*;

public class Lucius extends JavaPlugin {

	@Override
	public void onEnable() {
		getLogger().info("Lucius Made By ThorinDev");
		registerCommands();
	}
	@Override
	public void onDisable() {}
	private void registerCommands() {
		getCommand("hello").setExecutor(new HelloCommand());
	}
	
}
