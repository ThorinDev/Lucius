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
		} else {
			Player player = (Player) sender;
			String NoPermissionMessage = plugin.getConfig().getString("messages.noperm");
			String NPMColor = ChatColor.translateAlternateColorCodes('&', NoPermissionMessage);
			String CommandDisabledMessage = plugin.getConfig().getString("messages.commanddisabled");
			String CDMColor = ChatColor.translateAlternateColorCodes('&', CommandDisabledMessage);
			boolean isChatColorEnabled = plugin.getConfig().getBoolean("commands.colorchat");
			if(isChatColorEnabled) {
				if(player.hasPermission("lucius.chatcolor")) {
					if(args.length >= 1) {
						StringBuilder sb = new StringBuilder();						
						for(int i = 0; i<args.length; i++) {
							sb.append(args[i]).append(" ");
						}
						String ChatColorMessage = ChatColor.translateAlternateColorCodes('&', sb.toString());
						player.chat(ChatColorMessage);
						return true;
					} else {
						player.sendMessage(ChatColor.RED + "You need to specify what you want to say");
						return true;
					}
				} else {
					player.sendMessage(NPMColor);
					return true;
				}
			} else {
				player.sendMessage(CDMColor);
				return true;
			}
		}
	}
}