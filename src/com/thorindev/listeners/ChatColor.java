package com.thorindev.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatColor implements Listener {
	
	@EventHandler
	public void onPlayerChatEvent(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
	}
	
}
