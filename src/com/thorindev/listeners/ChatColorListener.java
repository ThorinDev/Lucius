package com.thorindev.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.md_5.bungee.api.ChatColor;

public class ChatColorListener implements Listener {
	
	@EventHandler
	public void onPlayerChatEvent(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String m = e.getMessage();
		if(p.hasPermission("lucius.chatcolor")) {
			if(m.contains("&")) {
				e.setCancelled(true);
				p.chat(ChatColor.translateAlternateColorCodes('&', m));
			}
		}
	}
	
}
