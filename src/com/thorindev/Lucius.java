package com.thorindev;

import org.bukkit.plugin.java.JavaPlugin;

import com.thorindev.commands.HelloCommand;
import com.thorindev.commands.FakesayCommand;

public class Lucius extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getLogger().info("Lucius Made By ThorinDev");
		
		registerPermissions();
		registerCommands();
	}
	
	@Override
	public void onDisable() {}
	
	private void registerPermissions() {
		
	}
	private void registerCommands() {
		getCommand("hello").setExecutor(new HelloCommand()); 
		getCommand("fakesay").setExecutor(new FakesayCommand());
	}
	
}
