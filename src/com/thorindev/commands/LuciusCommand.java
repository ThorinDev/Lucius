package com.thorindev.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.thorindev.Lucius;

import net.md_5.bungee.api.ChatColor;

public class LuciusCommand implements CommandExecutor {
	
	Lucius plugin;
	 
	public LuciusCommand(Lucius instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		String NoPermissionMessage = plugin.getConfig().getString("messages.noperm");
		String NPMColor = ChatColor.translateAlternateColorCodes('&', NoPermissionMessage);
		
		if(player.hasPermission("lucius.lucius")) {
			switch(args[0]) {
				case "reload":
					plugin.reloadConfig();
					player.sendMessage(ChatColor.GREEN + "Reloaded the config!");
					return true;
				case "test":
					player.sendMessage(ChatColor.GREEN + "Everything seems to be working");
					player.sendMessage(ChatColor.GREEN + "If something seems broken, I am unsure what it is");
					return true;
				default:
					player.sendMessage(ChatColor.RED + "Unknown argument");
					return true;
			}
		} else {
			player.sendMessage(NPMColor);
			return true;
		}
	}
}