package com.thorindev.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class ColorChat implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You need to be a player to use the command, " + ChatColor.GREEN + "/" + cmd.getName().toString());
			return true;
		}
		else {
			Player player = (Player) sender;
			
			if(player.hasPermission("lucius.colorchat")) {
				if(args.length >= 1) {
					
					String Message = "";
					for (String argument : args) {
						Message += argument;
						Message += " ";
						Message = ChatColor.translateAlternateColorCodes('&', Message);
					}
					
					player.chat(Message);
					
				}
				else {
					player.sendMessage(ChatColor.RED + "You need to say what you want to say");
				}
			}
			else {
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
			}
		}
		return false;
	}

}
