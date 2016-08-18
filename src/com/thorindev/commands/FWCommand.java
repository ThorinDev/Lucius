package com.thorindev.commands;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import com.thorindev.Lucius;

import net.md_5.bungee.api.ChatColor;

public class FWCommand implements CommandExecutor {
	
	public HashMap<String, Long> cooldowns = new HashMap<String, Long>();
	Lucius plugin;
	Random random = new Random();
	 
	public FWCommand(Lucius instance) {
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
			int FWTimeout = plugin.getConfig().getInt("commands.fwtimeout");
			Boolean isFWEnabled = plugin.getConfig().getBoolean("commands.fw");
			if(isFWEnabled == true) {
				if(player.hasPermission("lucius.fw")) {
					int cooldownTime = FWTimeout;
			        if(cooldowns.containsKey(player.getName())) {
			            long secondsLeft = ((cooldowns.get(player.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
			            if(secondsLeft>0) {
			                // Still cooling down
			                player.sendMessage(ChatColor.GREEN + "You cannot use " + cmd.getName() + " for another " + secondsLeft + " seconds.");
			                return true;
			            }
			        }
			        else {
			        	cooldowns.put(player.getName(), System.currentTimeMillis());
			        	
			        	Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
			        	FireworkMeta fwm = fw.getFireworkMeta();
						Type t1 = getType();
						Color c1 = getColor();
						Color c2 = getColor();
						FireworkEffect effect = FireworkEffect.builder().flicker(random.nextBoolean()).withColor(c1).withFade(c2).with(t1).trail(random.nextBoolean()).build();
						fwm.addEffect(effect);
						int rp = random.nextInt(2) + 1;
						fwm.setPower(rp);
						fw.setFireworkMeta(fwm);
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
		return false;
	}
	
	private Color getColor() {
		Color c = null;
		int i = random.nextInt(17)+1;	
		switch(i) {
			case 1:  
				c = Color.AQUA;
				break;
			case 2:  
				c = Color.BLACK;
				break;
			case 3:  
				c = Color.BLUE;
				break;
			case 4:  
				c = Color.FUCHSIA;
				break;
			case 5:  
				c = Color.GREEN;
				break;
			case 6:  
				c = Color.LIME;
				break;
			case 7:  
				c = Color.MAROON;
				break;
			case 8:  
				c = Color.NAVY;
				break;
			case 9:  
				c = Color.OLIVE;
				break;
			case 10:  
				c = Color.ORANGE;
				break;
			case 11:  
				c = Color.PURPLE;
				break;
			case 12:  
				c = Color.RED;
				break;
			case 13:  
				c = Color.SILVER;
				break;
			case 14:  
				c = Color.TEAL;
				break;
			case 15:  
				c = Color.WHITE;
				break;
			case 16:  
				c = Color.YELLOW;
				break;
			case 17:  
				c = Color.AQUA;
				break;	
		}	 
		return c;
	}
	private Type getType() {
		Type type = null;
		int i = random.nextInt(4)+1;
		switch(i) {
			case 1:
				type = Type.BALL;
				break;
			case 2:
				type = Type.BALL_LARGE;
				break;
			case 3:
				type = Type.BURST;
				break;
			case 4:
				type = Type.STAR;
				break;
		}
		return type;		
	}
}
