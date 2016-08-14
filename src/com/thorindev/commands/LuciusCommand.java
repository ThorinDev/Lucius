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
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You need to be a player to use the command, " + ChatColor.GREEN + "/" + cmd.getName().toString());
			return true;
		}
		else {
			Player player = (Player) sender;
			String NoPermissionMessage = plugin.getConfig().getString("messages.noperm").replaceAll("(&([a-f0-9]))", "\u00A7$2");
		}
		return false;
	}

}
