package com.thorindev.commands;

import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class HelloCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return false;
		}
		else {
			Player player = (Player) sender;
			Random random = new Random();
			
			int RandomNumber = random.nextInt(3)+1;
			
			switch(RandomNumber) {
				case 1:
					player.sendMessage(ChatColor.GREEN + "Why hello there, " + player.getDisplayName());
					break;
				case 2:
					player.sendMessage(ChatColor.GREEN + "Hi; " + player.getDisplayName());
					break;
				case 3:
					player.sendMessage(ChatColor.GREEN + "Peekabo " + player.getDisplayName());
					break;
			}
			return true;
		}
	}
}
