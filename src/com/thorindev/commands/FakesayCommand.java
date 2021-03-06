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
		} else {
			Player playerThatSent = (Player) sender;
			String NoPermissionMessage = plugin.getConfig().getString("messages.noperm");
			String NPMColor = ChatColor.translateAlternateColorCodes('&', NoPermissionMessage);
			String CommandDisabledMessage = plugin.getConfig().getString("messages.commanddisabled");
			String CDMColor = ChatColor.translateAlternateColorCodes('&', CommandDisabledMessage);
			boolean isFakesayEnabled = plugin.getConfig().getBoolean("commands.fakesay");

			if(isFakesayEnabled) {
				if(playerThatSent.hasPermission("lucius.fakesay")) {
					if(args.length != 0) {
						if(args.length != 1) {
							Player playerToTalk = Bukkit.getPlayerExact(args[0]);
							StringBuilder sb = new StringBuilder();
							
							for(int i = 1; i < args.length; i++) {
								sb.append(args[i]).append(" ");
							}
							
							String Message = sb.toString().trim();
							
							if(playerToTalk != null) {
								if(!(playerToTalk.hasPermission("lucius.fakesay.excempt"))) {
									playerToTalk.chat(Message);
									return true;	
								} else {
									if(playerThatSent.hasPermission("lucius.fakesay.override")) {
										playerToTalk.chat(Message);
										return true;							
									} else {
										playerThatSent.sendMessage(ChatColor.RED + "Sorry you can't make that person talk!");
										return true;
									}
								}
							} else {
								playerThatSent.sendMessage(ChatColor.RED + "The player you specified " + args[0] + " is not online");
								return true;
							}
						} else {
							playerThatSent.sendMessage(ChatColor.RED + "You need to specify the message for the player");
							return true;
						}
					} else {
						playerThatSent.sendMessage(ChatColor.RED + "You need to specify the player and the message");
						return true;
					}
				} else {
					playerThatSent.sendMessage(NPMColor);
					return true;
				}
			} else {
				playerThatSent.sendMessage(CDMColor);
				return true;
			}
		}
	}

}
