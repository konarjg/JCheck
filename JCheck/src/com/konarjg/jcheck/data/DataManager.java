package com.konarjg.jcheck.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import com.konarjg.jcheck.Main;
import com.konarjg.jcheck.obj.CheckRoom;

public class DataManager {
	
	static Location fromString (String s) {
		String[] ss = s.split(" ");
		return new Location(Bukkit.getWorld(ss[0]), Integer.parseInt(ss[1]), Integer.parseInt(ss[2]), Integer.parseInt(ss[3]));
	}
	
	static String fromLocation (Location l) {
		return new String(l.getWorld().getName() + " " + l.getBlockX() + " " + l.getBlockY() + " " + l.getBlockZ());
	}
	
	public static void load () {
		File cfg = FileManager.getCfg();
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(cfg);
		
		Main.h = new CheckRoom ();
		if (yml.getString("playerLoc") != null) {
			Main.h.setPlayerLoc(fromString (yml.getString("playerLoc")));
		}
		if (yml.getString("adminLoc") != null) {
			Main.h.setAdminLoc(fromString(yml.getString("adminLoc")));
		}
		Bukkit.getConsoleSender().sendMessage("§aWczytano!");
	}
	
	public static void save () {
		File cfg = FileManager.getCfg();
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(cfg);
		
		CheckRoom h = Main.h;
		if (h.getPlayerLoc() != null) yml.set("playerLoc", fromLocation (h.getPlayerLoc()));
		if (h.getAdminLoc() != null) yml.set("adminLoc", fromLocation (h.getAdminLoc()));
		try {
			yml.save(cfg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
