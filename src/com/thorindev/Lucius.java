package com.thorindev;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.thorindev.commands.ColorChat;
import com.thorindev.commands.FWCommand;
import com.thorindev.commands.FakesayCommand;
import com.thorindev.commands.HelloCommand;
import com.thorindev.commands.VanishCommand;

public class Lucius extends JavaPlugin {
	
	PluginManager pm = getServer().getPluginManager();
	final FileConfiguration config = this.getConfig();
	
	@Override
	public void onEnable() {
		getLogger().info("Lucius Made By ThorinDev");
		
		registerCommands();
		registerEvents();
		registerPermissions();
		registerConfig();
	}
	
	@Override
	public void onDisable() {}
	
	private void registerCommands() {
		getCommand("hello").setExecutor(new HelloCommand());
		getCommand("fakesay").setExecutor(new FakesayCommand());
		getCommand("colorchat").setExecutor(new ColorChat());
		getCommand("fw").setExecutor(new FWCommand());
		getCommand("vanish").setExecutor(new VanishCommand());
	}
	
	private void registerEvents() {
		pm.registerEvents(new VanishCommand(), this);
	}
	
	private void registerPermissions() {
		PluginManager pm = getServer().getPluginManager();
		
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
	
	public static Permission luciusCommandFakesay = new Permission("lucius.fakesay");
	public static Permission luciusCommandFakesayOverride = new Permission("lucius.fakesay.override");
	public static Permission luciusCommandFakesayExcempt = new Permission("lucius.fakesay.excempt");
	
	public Permission luciusCommandChatColor = new Permission("lucius.chatcolor");
	public Permission luciusCommandChatColorBold = new Permission("lucius.chatcolor.bold");
	public Permission luciusCommandChatColorUnderline = new Permission("lucius.chatcolor.underline");
	public Permission luciusCommandChatColorItallic = new Permission("lucius.chatcolor.itallic");
	public Permission luciusCommandChatColorMagic = new Permission("lucius.chatcolor.magic");
	public Permission luciusCommandChatColorStrike = new Permission("lucius.chatcolor.strike");
	
	public Permission luciusCommandFW = new Permission("lucius.fw");
	
	private void registerConfig() {
	}
}
