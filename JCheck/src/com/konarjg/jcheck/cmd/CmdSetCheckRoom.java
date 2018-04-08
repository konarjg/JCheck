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

public class CmdSetCheckRoom  implements CommandExecutor {
	@Override
	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("checkroom")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.notAPlayer));
				return true;
			}
			Player p = (Player) sender;
			if (!p.hasPermission("jcheck.*") || !p.hasPermission("jcheck.checkroom")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.noPermissions));
				return true;
			}
			if (args.length != 0) {
				return false;
			}
			CheckRoom h = new CheckRoom ();
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.checkroomCreated));
			Main.h = h;
			DataManager.save();
			DataManager.load();
			return true;
		}
		return false;
	}
}
