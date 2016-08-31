package com.thorindev;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.thorindev.commands.ColorChatCommand;
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
		getCommand("hello").setExecutor(new HelloCommand(this));
		getCommand("fakesay").setExecutor(new FakesayCommand(this));
		getCommand("colorchat").setExecutor(new ColorChatCommand(this));
		getCommand("fw").setExecutor(new FWCommand(this));
		getCommand("vanish").setExecutor(new VanishCommand(this));
	}
	
	private void registerEvents() {
		pm.registerEvents(new VanishCommand(this), this);
	}
	
	private void registerPermissions() {
		PluginManager pm = getServer().getPluginManager();
		for(int i = 0; i<listOfPerms.length; i++) {
			pm.addPermission(listOfPerms[i]);
		}
	}
	
	public Permission luciusCommandFakesay = new Permission("lucius.fakesay");
	public Permission luciusCommandFakesayOverride = new Permission("lucius.fakesay.override");
	public Permission luciusCommandFakesayExempt = new Permission("lucius.fakesay.exempt");
	
	public Permission luciusCommandChatColor = new Permission("lucius.chatcolor"); 
	
	public Permission luciusCommandFW = new Permission("lucius.fw");
	public Permission luciusCommandVanish = new Permission("lucius.vanish");
		
	Permission listOfPerms[] = {
			luciusCommandFakesay,
			luciusCommandFakesayExempt,
			luciusCommandFakesayOverride,
			luciusCommandChatColor,
			luciusCommandFW,
			luciusCommandVanish,
	};
	
	private void registerConfig() {
		config.options().header(
				"Lucius \n" +
				"All commands can be disbaled and enabled, as well as changing other things such as the firework timeout \n" +
				"You can also change messages for command disabled and no permission, with full formatting support"
		);
		config.addDefault("commands.colorchat", true);
		config.addDefault("commands.fakesay", true);
		config.addDefault("commands.fw", false);
		config.addDefault("commands.fwtimeout",  15);
		config.addDefault("commands.hello", true);
		config.addDefault("commands.vanish", true);
		config.addDefault("messages.noperm", "&cYou do not have permission to use this command");
		config.addDefault("messages.commanddisabled", "&cCommand Disabled");
		config.options().copyDefaults(true);
		config.options().copyHeader(true);
		saveConfig();
	}
}
