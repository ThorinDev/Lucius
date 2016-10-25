package com.thorindev;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.thorindev.commands.ColorChatCommand;
import com.thorindev.commands.FWCommand;
import com.thorindev.commands.FakesayCommand;
import com.thorindev.commands.HelloCommand;
import com.thorindev.commands.LuciusCommand;
import com.thorindev.commands.VanishCommand;

public class Lucius extends JavaPlugin {
	
	PluginManager pm = getServer().getPluginManager();
	final FileConfiguration config = this.getConfig();
	LuciusSubroutines ls = new LuciusSubroutines(this);
	
	@Override
	public void onEnable() {
		getLogger().info("Lucius Made By ThorinDev");
		
		ls.doStuff(); //calls all the subroutines in that class
		registerCommands();
		registerEvents();
	}
	
	@Override
	public void onDisable() {}
	
	private void registerCommands() {
		getCommand("hello").setExecutor(new HelloCommand(this));
		getCommand("fakesay").setExecutor(new FakesayCommand(this));
		getCommand("colorchat").setExecutor(new ColorChatCommand(this));
		getCommand("fw").setExecutor(new FWCommand(this));
		getCommand("vanish").setExecutor(new VanishCommand(this));
		getCommand("lucius").setExecutor(new LuciusCommand(this));
	}
	
	private void registerEvents() {
		pm.registerEvents(new VanishCommand(this), this);
	}
}
