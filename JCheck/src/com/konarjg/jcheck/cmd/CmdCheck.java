package com.konarjg.jcheck.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.konarjg.jcheck.Main;
import com.konarjg.jcheck.data.Messages;
import com.konarjg.jcheck.data.utils.MessagesConfig;
import com.konarjg.jcheck.obj.CheckRoom;

public class CmdCheck implements CommandExecutor {
	
	BukkitTask bt;
	
	@Override
	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("check")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.notAPlayer));
				return true;
			}
			final Player p = (Player) sender;
			if (args.length == 0) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.checkHelp));
				return true;
			}
			if (args.length > 2 || args.length < 2) {
				return false;
			}
			if (args[0].equalsIgnoreCase("player")) {
				if (!p.hasPermission("jcheck.*") || !p.hasPermission("jcheck.check.*") || !p.hasPermission("jcheck.check.player")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.noPermissions));
				}
				if (Main.isChecked(Bukkit.getPlayer(args[1]))) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.playerAlreadyChecking));
					return true;
				}
				if (!Bukkit.getPlayer(args[1]).isOnline()) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.playerNotOnline));
					return true;
				}
				CheckRoom h = Main.h;
				Messages.sendChecking(Bukkit.getPlayer(args[1]));
				p.teleport(h.getAdminLoc());
				Bukkit.getPlayer(args[1]).teleport(h.getPlayerLoc());
				Bukkit.getPlayer(args[1]).sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.playerIsBeingChecked));
				Messages.sendLogoutTime(Bukkit.getPlayer(args[1]));
				Messages.sendAdmissionTime(Bukkit.getPlayer(args[1]));
				Messages.sendJoinTS(Bukkit.getPlayer(args[1]));
				Bukkit.getPlayer(args[1]).sendMessage(ChatColor.translateAlternateColorCodes('&', MessagesConfig.space));
				Main.checkedPlayers.put(Bukkit.getPlayer(args[1]).getName(), true);
				return true;
			}
			final Player p2 = Bukkit.getPlayer(args[1]);
			Location l = p2.getLocation();
			if (args[0].equalsIgnoreCase("cheats")) {
				if (!p.hasPermission("jcheck.*") || !p.hasPermission("jcheck.check.*") || !p.hasPermission("jcheck.check.cheats")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.noPermissions));
					return true;
				}
				if (!Main.isChecked(p2)) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.playerAlreadyChecking));
					return true;
				}
				Main.checkedPlayers.remove(p2.getName());
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.banPlayer));
				p2.teleport(p2.getWorld().getSpawnLocation());
				bt = Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(Main.getInst(), new Runnable () {
					@Override
					public void run () {
						Messages.sendBannedPlayer(p2);
						p.teleport(p.getWorld().getSpawnLocation());
						bt.cancel();
					}
				}, 10*20);
				return true;
			}else if (args[0].equalsIgnoreCase("clear")) {
				if (!p.hasPermission("jcheck.*") || !p.hasPermission("jcheck.check.*") || !p.hasPermission("jcheck.check.clear")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.noPermissions));
					return true;
				}
				if (!Main.isChecked(p2)) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.playerAlreadyChecking));
					return true;
				}
				Main.checkedPlayers.remove(p2.getName());
				p.teleport(p.getWorld().getSpawnLocation());
				p2.teleport(l);
				Messages.sendClear(p2);
				p2.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.playerClear));
				return true;
			}
			return true;
		}
		return false;
	}
}
