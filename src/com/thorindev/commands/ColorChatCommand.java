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
			String NoPermissionMessage = plugin.getConfig().getString("messages.noperm").replaceAll("(&([a-f0-9]))", "\u00A7$2");
			String CommandDisabled = plugin.getConfig().getString("messages.commanddisabled").replaceAll("(&([a-f0-9]))", "\u00A7$2");
			Boolean isChatColorEnabled = plugin.getConfig().getBoolean("commands.colorchat");
			if(player.hasPermission("lucius.colorchat")) {
				if(args.length >= 1) {
					String Message = "";
					for (String argument : args) {
						Message += argument;
						Message += " ";
						Message = ChatColor.translateAlternateColorCodes('&', Message);
					}
					
					if(Message.contains("ChatColor.BOLD")) {
						if(player.hasPermission("lucius.chatcolor.bold")) {
							player.chat(Message);
						}
						else {
							player.sendMessage(ChatColor.RED + "You do not have permission to use bold formatting");
						}
					}
					else if(Message.contains("ChatColor.UNDERLINE")) {
						if(player.hasPermission("lucius.chatcolor.underline")) {
							player.chat(Message);
						}
						else {
							player.sendMessage(ChatColor.RED + "You do not have permission to use underline formatting");
						}
					}
					else if(Message.contains("ChatColor.ITALLIC")) {
						if(player.hasPermission("lucius.chatcolor.itallic")) {
							player.chat(Message);
						}
						else {
							player.sendMessage(ChatColor.RED + "You do not have permission to use itallic formating");
						}	
					}
					else if(Message.contains("ChatColor.MAGIC")){
						if(player.hasPermission("lucius.chatcolor.magic")) {
							player.chat(Message);
						}
						else {
							player.sendMessage(ChatColor.RED + "You do not have permission to use magic formatting");
						}
					}
					else if(Message.contains("ChatColor.STRIKETHROUGH")) {
						if(player.hasPermission("lucius.chatcolor.strike")) {
							player.chat(Message);
						}
						else {
							player.sendMessage(ChatColor.RED + "You do not have permission to use strikethrough formatting");
						}
					}
					else {
						player.chat(Message);
					}
					
					
					return true;
				}
				else {
					player.sendMessage(ChatColor.RED + "You need to say what you want to say");
					return true;
				}
			}
			else {
				player.sendMessage(ChatColor.RED + NoPermissionMessage);
				return true;
			}
		}
	}

}
