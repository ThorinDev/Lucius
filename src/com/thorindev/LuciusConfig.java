package com.thorindev;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class LuciusConfig {
	
	Lucius plugin;
	 
	public LuciusConfig(Lucius instance) {
		plugin = instance;
	}

	public void registerConfig() {
		FileConfiguration config = plugin.config;
		Bukkit.getLogger().info("Succesfully accessed the config class and adding the config.");
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

	private void saveConfig() {
		plugin.saveConfig();
	}
}