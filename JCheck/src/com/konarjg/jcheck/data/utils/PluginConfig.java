package com.konarjg.jcheck.data.utils;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import com.konarjg.jcheck.data.FileManager;

public class PluginConfig {
	
	public static String teamspeakServer;
	public static String teamspeakChannel;
	public static int logoutBanTime;
	public static int admissionBanTime;
	public static String logoutBanReasonFormat;
	
	public static void load () {
		File f = FileManager.getCfg();
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		teamspeakServer = yml.getString("TeamSpeak");
		teamspeakChannel = yml.getString("teamspeakChannel");
		logoutBanTime = yml.getInt("logoutBanTime");
		admissionBanTime = yml.getInt("admissionBanTime");
		logoutBanReasonFormat = yml.getString("logoutBanReasonFormat");
	}
}
