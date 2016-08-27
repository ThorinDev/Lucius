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
			            if(secondsLeft > 0) {
			                // Still cooling down		            	
			            	player.sendMessage(ChatColor.RED + "You need to wait another " + secondsLeft + " second(s)");
			                return true;
			            }
			            else {			            	
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
							
							player.sendMessage(ChatColor.GREEN + "Launching firework!");
				        	cooldowns.put(player.getName(), System.currentTimeMillis());
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
						player.sendMessage(ChatColor.GREEN + "Launching firework!");
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
	private Color getColor() {
		Color c = null;
		Color[] listOfColors = {
			Color.AQUA,
			Color.BLACK,
			Color.BLUE,
			Color.FUCHSIA,
			Color.GREEN,
			Color.GRAY,
			Color.LIME,
			Color.MAROON,
			Color.NAVY,
			Color.OLIVE,
			Color.ORANGE,
			Color.PURPLE,
			Color.RED,
			Color.SILVER,
			Color.TEAL,
			Color.WHITE,
			Color.YELLOW
		};
		int i = random.nextInt(17);
		c = listOfColors[i];
		return c;
	}
	private Type getType() {
		Type type = null;
		int i = random.nextInt(5)+1;
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
			case 5:
				type = Type.CREEPER;
				break;
		}
		return type;		
	}
}
