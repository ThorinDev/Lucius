package com.thorindev.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.thorindev.Lucius;

import net.md_5.bungee.api.ChatColor;

public class FakesayCommand implements CommandExecutor {
	
	Lucius plugin;
	 
	public FakesayCommand(Lucius instance) {
		plugin = instance;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You need to be a player to use the command, " + ChatColor.GREEN + "/" + cmd.getName().toString());
			return true;
		}
		else {
			Player playerThatSent = (Player) sender;
			String NoPermissionMessage = plugin.getConfig().getString("messages.noperm").replaceAll("(&([a-f0-9]))", "\u00A7$2");
			String CommandDisabled = plugin.getConfig().getString("messages.commanddisabled").replaceAll("(&([a-f0-9]))", "\u00A7$2");
			if(playerThatSent.hasPermission("lucius.fakesay")) {
				if(args.length != 0) {
					if(args.length != 1) {
						Player playerToTalk = Bukkit.getPlayerExact(args[0]);
						StringBuilder sb = new StringBuilder();
						
						for(int i = 1; i < args.length; i++) {
							sb.append(args[i]).append(" ");
						}
						
						if(playerToTalk != null) {
							if(!(playerToTalk.hasPermission("lucius.fakesay.excempt"))) {
								String allArgs = sb.toString().trim();
								playerToTalk.chat(allArgs);
								return true;	
							}
							else {
								if(playerThatSent.hasPermission("lucius.fakesay.override")) {
									String allArgs = sb.toString().trim();
									playerToTalk.chat(allArgs);
									return true;							
								}
								else {
									playerThatSent.sendMessage(ChatColor.RED + "Sorry you can't make that person talk!");
									return true;
								}
							}
						}
						else {
							playerThatSent.sendMessage(ChatColor.RED + "The player you specified " + args[0] + " is not online");
							return true;
						}
					}
					else {
						playerThatSent.sendMessage(ChatColor.RED + "You need to specify the message");
						return true;
					}
				}
				else {
					playerThatSent.sendMessage(ChatColor.RED + "You need to specify the player and the message");
					return true;
				}
			}
			else {
				playerThatSent.sendMessage(ChatColor.RED + "You do not have permission to use this command");
				return true;
			}
		}
	}

}
