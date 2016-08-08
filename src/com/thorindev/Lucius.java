package com.thorindev;

import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.thorindev.commands.ColorChat;
import com.thorindev.commands.FakesayCommand;
import com.thorindev.commands.HelloCommand;

public class Lucius extends JavaPlugin {
	
	public boolean isFakesayEnabled;
	public boolean isHelloEnabled;
	PluginManager pm = getServer().getPluginManager();
	
	@Override
	public void onEnable() {
		getLogger().info("Lucius Made By ThorinDev");
		
		registerCommands();
		registerPermissions();
		registerEvents();
	}
	
	@Override
	public void onDisable() {}
	
	private void registerCommands() {
		getCommand("hello").setExecutor(new HelloCommand());
		getCommand("hi").setExecutor(new HelloCommand());
		getCommand("hey").setExecutor(new HelloCommand());
		getCommand("fakesay").setExecutor(new FakesayCommand());
		getCommand("colorchat").setExecutor(new ColorChat());
		getCommand("colourchat").setExecutor(new ColorChat());
		getCommand("cc").setExecutor(new ColorChat());
	}
	
	
	private void registerPermissions() {
		pm.addPermission(luciusCommandFakesay);
		pm.addPermission(luciusCommandFakesayExcempt);
		pm.addPermission(luciusCommandFakesayOverride);
		pm.addPermission(luciusCommandHello);
		pm.addPermission(luciusCommandChatColor);
	}
	
	public Permission luciusCommandHello = new Permission("lucius.hello");
	public Permission luciusCommandFakesay = new Permission("lucius.fakesay");
	public Permission luciusCommandFakesayOverride = new Permission("lucius.fakesay.override");
	public Permission luciusCommandFakesayExcempt = new Permission("lucius.fakesay.excempt");
	public Permission luciusCommandChatColor = new Permission("lucius.chatcolor");
	
	private void registerEvents() {
		//for future use
	}
}
