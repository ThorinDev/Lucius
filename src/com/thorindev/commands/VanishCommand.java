package com.thorindev.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.thorindev.Lucius;

import net.md_5.bungee.api.ChatColor;

public class VanishCommand implements CommandExecutor, Listener {
	
	public static ArrayList<Player> vanished = new ArrayList<Player>();
	
	Lucius plugin;
	 
	public VanishCommand(Lucius instance) {
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
			if(player.hasPermission("lucius.vanish")) {
				return true;
			}	
			else {
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
				return true;	
			}
		}
	}

}
