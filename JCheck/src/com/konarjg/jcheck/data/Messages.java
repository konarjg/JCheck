package com.konarjg.jcheck.data;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.konarjg.jcheck.data.utils.PluginConfig;

public class Messages {
	static String checking;
	static String joinTeamSpeak;
	public static String playerIsBeingChecked;
	static String banned;
	static String logout;
	static String clear;
	public static String playerClear;
	static String logoutBan;
	static String admissionBan;
	public static String noPermissions;
	public static String playerAlreadyChecking;
	public static String playerNotOnline;
	public static String banPlayer;
	public static String notAPlayer;
	public static String checkHelp;
	public static String posHelp;
	public static String playerPosSet;
	public static String adminPosSet;
	public static String checkroomCreated;
	
	public static void load () {
		File f = FileManager.getMsg();
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		checking = yml.getString("checking");
		joinTeamSpeak = yml.getString("joinTeamSpeak");
		playerIsBeingChecked = yml.getString("playerIsBeingChecked");
		banned = yml.getString("banned");
		logout = yml.getString("logout");
		clear = yml.getString("clear");
		playerClear = yml.getString("playerClear");
		logoutBan = yml.getString("logoutBan");
		admissionBan = yml.getString("admissionBan");
		noPermissions = yml.getString("noPermissions");
		playerAlreadyChecking = yml.getString("playerAlreadyChecking");
		playerNotOnline = yml.getString("playerNotOnline");
		banPlayer = yml.getString("banPlayer");
		notAPlayer = yml.getString("notAPlayer");
		checkHelp = yml.getString("checkHelp");
		posHelp = yml.getString("posHelp");
		adminPosSet = yml.getString("adminPosSet");
		playerPosSet = yml.getString("playerPosSet");
		checkroomCreated = yml.getString("checkroomCreated");
	}
	
	public static void sendClear (Player p) {
		Bukkit.broadcastMessage(replaceClearPlayer(p));
	}
	
	public static void sendChecking (Player p) {
		Bukkit.broadcastMessage(replacePlayer (p));
	}
	
	public static void sendJoinTS (Player p) {
		p.sendMessage(replaceTSServer());
	}
	
	public static void sendBannedPlayer (Player p) {
		Bukkit.broadcastMessage(replaceBannedPlayer(p));
	}
	
	public static void sendPlayerLogout (Player p) {
		Bukkit.broadcastMessage(replaceLoggedPlayer(p));
	}
	
	public static void sendLogoutTime (Player p) {
		p.sendMessage(replaceLogoutTime());
	}
	
	public static void sendAdmissionTime (Player p) {
		p.sendMessage(replaceAdmissionTime());
	}
	
	static String replaceAdmissionTime () {
		String[] s = admissionBan.split(" ");
		s[4] = "&c" + PluginConfig.admissionBanTime;
		return ChatColor.translateAlternateColorCodes('&', s[0] + " " + s[1] + " " + s[2] + " " + s[3] + " " + s[4] + " " + s[5]);
	}
	
	static String replaceLogoutTime () {
		String[] s = logoutBan.split(" ");
		s[4] = "&c" + PluginConfig.logoutBanTime;
		return ChatColor.translateAlternateColorCodes('&', s[0] + " " + s[1] + " " + s[2] + " " + s[3] + " " + s[4] + " " + s[5]);
	}
	
	static String replaceLoggedPlayer(Player p) {
		String[] s = logout.split(" ");
		s[1] = "&c" + p.getName();
		return ChatColor.translateAlternateColorCodes('&', s[0] + " " + s[1] + " " + s[2] + " " + s[3] + " " + s[4]);
	}

	static String replacePlayer (Player p) {
		String[] s = checking.split(" ");
		s[1] = "&c" + p.getName();
		return ChatColor.translateAlternateColorCodes('&', s[0] + " " + s[1] + " " + s[2] + " " + s[3]);
	}
	
	static String replaceTSServer () {
		String[] s = joinTeamSpeak.split(" ");
		s[4] = "&c" + PluginConfig.teamspeakServer;
		s[7] = "&c" + PluginConfig.teamspeakChannel;
		return ChatColor.translateAlternateColorCodes('&', s[0] + " " + s[1] + " " + s[2] + " " + s[3] + " " + s[4] + " " + s[5] + " " + s[6] + " " + s[7]);
	}
	
	static String replaceBannedPlayer (Player p) {
		String[] s = banned.split(" ");
		s[1] = "&c" + p.getName ();
		return ChatColor.translateAlternateColorCodes('&', s[0] + " " + s[1] + " " + s[2] + " " + s[3] + " " + s[4] + " " + s[5]);
	}
	
	static String replaceClearPlayer (Player p) {
		String[] s = clear.split(" ");
		s[1] = "&a" + p.getName();
		return ChatColor.translateAlternateColorCodes('&', s[0] + " " + s[1] + " " + s[2] + " " + s[3]);
	}
}
