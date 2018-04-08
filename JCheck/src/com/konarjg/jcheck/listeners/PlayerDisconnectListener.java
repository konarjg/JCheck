package com.konarjg.jcheck.listeners;

import java.util.Date;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.konarjg.jcheck.Main;
import com.konarjg.jcheck.data.Messages;
import com.konarjg.jcheck.data.utils.PluginConfig;

public class PlayerDisconnectListener implements Listener {
	@EventHandler
	public void onLeave (PlayerQuitEvent e) {
		if (Main.isChecked(e.getPlayer())) {
			Date d = new Date ();
			int days = d.getDay() ;		
			d.setDate(days += 11);
			Bukkit.getBanList(Type.NAME).addBan(e.getPlayer().getName(), PluginConfig.logoutBanReasonFormat, d, null);
			Messages.sendPlayerLogout(e.getPlayer());
		}
	}
}
