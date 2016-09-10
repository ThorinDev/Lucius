package com.thorindev;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;

public class LuciusPermissions {
	
	public Permission luciusCommandFakesay = new Permission("lucius.fakesay");
	public Permission luciusCommandFakesayOverride = new Permission("lucius.fakesay.override");
	public Permission luciusCommandFakesayExempt = new Permission("lucius.fakesay.exempt");
	public Permission luciusCommandChatColor = new Permission("lucius.chatcolor"); 
	public Permission luciusCommandFW = new Permission("lucius.fw");
	public Permission luciusCommandVanish = new Permission("lucius.vanish");
	public Permission luciusCommandLucius = new Permission("lucius.lucius");
		
	Permission[] listOfPerms = {
			luciusCommandFakesay,
			luciusCommandFakesayExempt,
			luciusCommandFakesayOverride,
			luciusCommandChatColor,
			luciusCommandFW,
			luciusCommandVanish,
			luciusCommandLucius
	};
	
	public void registerPermissions() {
		Bukkit.getLogger().info("Succesfully accessed the permissions.");
		PluginManager pm = Bukkit.getServer().getPluginManager();
		for(int i = 0; i<listOfPerms.length; i++) {
			pm.addPermission(listOfPerms[i]);
		}
	}


}
