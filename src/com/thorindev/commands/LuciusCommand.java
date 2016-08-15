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
			String NoPermissionMessage = plugin.getConfig().getString("messages.noperm");
			String NPMColor = ChatColor.translateAlternateColorCodes('&', NoPermissionMessage);
			if(player.hasPermission("lucius.lucius")) {
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("reload")) {
						plugin.reloadConfig();
						plugin.saveConfig();
						player.sendMessage(ChatColor.GREEN + "Reloaded the config!");
						return true;
					}
				}
				else {
					player.sendMessage(ChatColor.RED + "Too little or too many arguments");
					return true;
				}
			}
			else {
				player.sendMessage(NPMColor);
				return true;
			}
		}
		return false;
	}

}