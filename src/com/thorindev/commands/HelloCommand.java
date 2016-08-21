package com.thorindev.commands;

import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.thorindev.Lucius;

import net.md_5.bungee.api.ChatColor;

public class HelloCommand implements CommandExecutor {
	
	Lucius plugin;
	 
	public HelloCommand(Lucius instance) {
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
			String CommandDisabledMessage = plugin.getConfig().getString("messages.commanddisabled");
			String CDMColor = ChatColor.translateAlternateColorCodes('&', CommandDisabledMessage);
			Boolean isHelloEnabled = plugin.getConfig().getBoolean("commands.hello");
			
			if(isHelloEnabled == true) {
				Random random = new Random();
				
				int RandomNumber = random.nextInt(6)+1;
				
				switch(RandomNumber) {
					case 1:
						player.sendMessage(ChatColor.GREEN + "Why hello there, " + player.getDisplayName());
						break;
					case 2:
						player.sendMessage(ChatColor.GREEN + "Hi " + player.getDisplayName());
						break;
					case 3:
						player.sendMessage(ChatColor.GREEN + "Peekabo " + player.getDisplayName());
						break;
					case 4:
						player.sendMessage(ChatColor.GREEN + "Bonjour " + player.getDisplayName());
						break;
					case 5:
						player.sendMessage(ChatColor.GREEN + "Hallo " + player.getDisplayName());
						break;
					case 6:
						player.sendMessage(ChatColor.GREEN + "Hallo " + player.getDisplayName());
						break;
				}
				return true;
			}
			else {
				player.sendMessage(CDMColor);
			}
		}
		return false;
	}
}