package com.thorindev.commands;

import java.util.HashMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.thorindev.Lucius;

import net.md_5.bungee.api.ChatColor;

public class FWCommand2 implements CommandExecutor {
	
	public HashMap<String, Long> cooldowns = new HashMap<String, Long>();
	Lucius plugin;
	 
	public FWCommand2(Lucius instance) {
		plugin = instance;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You need to be a player to use the command, " + ChatColor.GREEN + "/" + cmd.getName().toString());
			return true;
		}
		else {
			Player player = (Player) sender;
			String NoPermissionMessage = plugin.getConfig().getString("messages.noperm");
			String NPMColor = ChatColor.translateAlternateColorCodes('&', NoPermissionMessage);
			String CommandDisabledMessage = plugin.getConfig().getString("messages.commanddisabled");
			String CDMColor = ChatColor.translateAlternateColorCodes('&', CommandDisabledMessage);
			Boolean isFWEnabled = plugin.getConfig().getBoolean("commands.fw");
			if(isFWEnabled == true) {
				if(player.hasPermission("lucius.fw")) {
					int cooldownTime = 60; // Get number of seconds from wherever you want
			        if(cooldowns.containsKey(player.getName())) {
			            long secondsLeft = ((cooldowns.get(player.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
			            if(secondsLeft > 0) {
			                player.sendMessage("You cant use that commands for another "+ secondsLeft +" seconds!");
			                return true;
			            }
			        }
			        cooldowns.put(player.getName(), System.currentTimeMillis());
			        player.sendMessage("Command Activated!");
			        return true; 
				}
				else {
					player.sendMessage(NPMColor);
					return true;
				}
			}
			else {
				player.sendMessage(CDMColor);
				return true;
			}
		}
	}
}
