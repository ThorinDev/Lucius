package com.thorindev;

import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.thorindev.commands.ColorChat;
import com.thorindev.commands.FWCommand;
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
		getCommand("cc").setExecutor(new ColorChat());
		getCommand("fw").setExecutor(new FWCommand());
	}
	
	private void registerPermissions() {
		pm.addPermission(luciusCommandFakesay);
		pm.addPermission(luciusCommandFakesayExcempt);
		pm.addPermission(luciusCommandFakesayOverride);

		pm.addPermission(luciusCommandChatColor);
		pm.addPermission(luciusCommandChatColorBold);
		pm.addPermission(luciusCommandChatColorUnderline);
		pm.addPermission(luciusCommandChatColorItallic);
		pm.addPermission(luciusCommandChatColorMagic);
		pm.addPermission(luciusCommandChatColorStrike);
	}

	public Permission luciusCommandFakesay = new Permission("lucius.fakesay");
	public Permission luciusCommandFakesayOverride = new Permission("lucius.fakesay.override");
	public Permission luciusCommandFakesayExcempt = new Permission("lucius.fakesay.excempt");
	
	public Permission luciusCommandChatColor = new Permission("lucius.chatcolor");
	public Permission luciusCommandChatColorBold = new Permission("lucius.chatcolor.bold");
	public Permission luciusCommandChatColorUnderline = new Permission("lucius.chatcolor.underline");
	public Permission luciusCommandChatColorItallic = new Permission("lucius.chatcolor.itallic");
	public Permission luciusCommandChatColorMagic = new Permission("lucius.chatcolor.magic");
	public Permission luciusCommandChatColorStrike = new Permission("lucius.chatcolor.strike");
	
	
	private void registerEvents() {
		//for future use
	}
	
}
