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
			String NoPermissionMessage = plugin.getConfig().getString("messages.noperm");
			String NPMColor = ChatColor.translateAlternateColorCodes('&', NoPermissionMessage);
			String CommandDisabledMessage = plugin.getConfig().getString("messages.commanddisabled");
			String CDMColor = ChatColor.translateAlternateColorCodes('&', CommandDisabledMessage);
			Boolean isVanishEnabled = plugin.getConfig().getBoolean("commands.vanish");
			if(isVanishEnabled == true) {
				if(player.hasPermission("lucius.vanish")) {
					if (!vanished.contains(player)) {
						VanishPlayer(player);
						player.sendMessage(ChatColor.GREEN + "You have been vanished!");
						return true;
					}
					else {
						UnVanishPlayer(player);
						player.sendMessage(ChatColor.GREEN + "You have been unvanished!");
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
	}
	
	private void VanishPlayer(Player player) {
		for(Player toHide : Bukkit.getServer().getOnlinePlayers()) {
			toHide.hidePlayer(player);
		}
		vanished.add(player);
	}
	
	private void UnVanishPlayer(Player player) {
		for(Player toShow : Bukkit.getServer().getOnlinePlayers()) {
			toShow.hidePlayer(player);
		}
		vanished.remove(player);
	}
	
	@EventHandler
	public void onPlayerJoinVanish(PlayerJoinEvent event) {
		for(Player player : vanished) {
			event.getPlayer().hidePlayer(player);
		}
	}
	
	@EventHandler
	public void onPlayerQuitVanish(PlayerQuitEvent event) {
		vanished.remove(event.getPlayer());
	}

}
