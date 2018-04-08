package com.konarjg.jcheck.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import com.konarjg.jcheck.Main;
import com.konarjg.jcheck.data.utils.MessagesConfig;

public class FileManager {
	static File mainDir = Main.getInst().getDataFolder();
	static File msgFile = new File (mainDir, "messages.yml");
	static File cfgFile = new File (mainDir, "config.yml");
	
	public static void check () {
		if (!mainDir.exists()) mainDir.mkdirs();
		if (!msgFile.exists()) {
			try {
				msgFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(msgFile);
			yml.set("checking", MessagesConfig.checking);
			yml.set("playerIsBeingChecked", MessagesConfig.playerIsBeingChecked);
			yml.set("joinTeamSpeak", MessagesConfig.joinTeamSpeak);
			yml.set("banned", MessagesConfig.banned);
			yml.set("logout", MessagesConfig.logout);
			yml.set("clear", MessagesConfig.clear);
			yml.set("playerClear", MessagesConfig.playerClear);
			yml.set("logoutBan", MessagesConfig.logoutBan);
			yml.set("admissionBan", MessagesConfig.admissionBan);
			yml.set("noPermissions", MessagesConfig.noPermissions);
			yml.set("playerAlreadyChecking", MessagesConfig.playerAlreadyChecking);
			yml.set("playerNotOnline", MessagesConfig.playerNotOnline);
			yml.set("banPlayer", MessagesConfig.banPlayer);
			yml.set("notAPlayer", MessagesConfig.notAPlayer);
			yml.set("checkHelp", MessagesConfig.checkHelp);
			yml.set("posHelp", MessagesConfig.posHelp);
			yml.set("playerPosSet", MessagesConfig.playerPosSet);
			yml.set("adminPosSet", MessagesConfig.adminPosSet);
			yml.set("checkroomCreated", MessagesConfig.checkroomCreated);
			try {
				yml.save(msgFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!cfgFile.exists()) Main.getInst().saveDefaultConfig();
	}
	
	public static File mainDir () {
		return mainDir;
	}
	
	public static File getCfg () {
		return cfgFile;
	}
	
	public static File getMsg () {
		return msgFile;
	}
}
