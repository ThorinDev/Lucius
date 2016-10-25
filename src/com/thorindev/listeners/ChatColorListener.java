package com.thorindev.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.thorindev.Lucius;

import net.md_5.bungee.api.ChatColor;

public class ChatColorListener implements Listener {
	
	Lucius plugin;
	 
	public ChatColorListener(Lucius instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerChatEvent(AsyncPlayerChatEvent e) {
		String CommandDisabledMessage = plugin.getConfig().getString("messages.commanddisabled");
		String CDMColor = ChatColor.translateAlternateColorCodes('&', CommandDisabledMessage);
		Boolean isChatColorEnabled = plugin.getConfig().getBoolean("commands.colorchat");
		
		Player player = e.getPlayer();
		String m = e.getMessage();
		
		if(isChatColorEnabled) {
			if(player.hasPermission("lucius.chatcolor")) {
				if(m.contains("&")) {
					e.setCancelled(true);
					player.chat(ChatColor.translateAlternateColorCodes('&', m));
				}
			}
		} else {
			player.sendMessage(CDMColor);
		}
	}
	
}
