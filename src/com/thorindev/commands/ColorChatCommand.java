package com.thorindev.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.thorindev.Lucius;

import net.md_5.bungee.api.ChatColor;

public class ColorChatCommand implements CommandExecutor {
	
	Lucius plugin;
	 
	public ColorChatCommand(Lucius instance) {
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
			Boolean isChatColorEnabled = plugin.getConfig().getBoolean("commands.colorchat");
			if(isChatColorEnabled == true) {
				if(player.hasPermission("lucius.chatcolor")) {
					if(args.length >= 1) {
						
						String Message = "";
						for (String argument : args) {
							Message += argument;
							Message += " ";
							Message = ChatColor.translateAlternateColorCodes('&', Message);
						}
						
						player.chat(Message);
						return true;
					}
					else {
						player.sendMessage(ChatColor.RED + "You need to say what you want to say");
						return true;
					}
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

}}
