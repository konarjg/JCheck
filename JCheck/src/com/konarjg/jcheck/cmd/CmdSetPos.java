package com.konarjg.jcheck.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.konarjg.jcheck.Main;
import com.konarjg.jcheck.data.DataManager;
import com.konarjg.jcheck.data.Messages;
import com.konarjg.jcheck.data.utils.MessagesConfig;
import com.konarjg.jcheck.obj.CheckRoom;

public class CmdSetPos implements CommandExecutor {
	
	@Override
	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("pos")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.notAPlayer));
				return true;
			}
			Player p = (Player) sender;
			if (!p.hasPermission("jcheck.*") || !p.hasPermission("jcheck.pos")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.noPermissions));
				return true;
			}
			if (args.length == 0) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.posHelp));
				return true;
			}
			if (args.length != 1) {
				return false;
			}
			if (args[0].equalsIgnoreCase("player")) {
				CheckRoom h = Main.h;
				h.setPlayerLoc(p.getLocation());
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.playerPosSet));
				return true;
			}else if (args[0].equalsIgnoreCase("admin")) {
				CheckRoom h = Main.h;
				h.setAdminLoc(p.getLocation());
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.adminPosSet));
				DataManager.save();
				DataManager.load();
				return true;
			}
		}
		return false;
	}

}
