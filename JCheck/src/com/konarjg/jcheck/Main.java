package com.konarjg.jcheck;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.konarjg.jcheck.cmd.CmdCheck;
import com.konarjg.jcheck.cmd.CmdSetCheckRoom;
import com.konarjg.jcheck.cmd.CmdSetPos;
import com.konarjg.jcheck.data.DataManager;
import com.konarjg.jcheck.data.FileManager;
import com.konarjg.jcheck.data.Messages;
import com.konarjg.jcheck.data.utils.PluginConfig;
import com.konarjg.jcheck.listeners.PlayerDisconnectListener;
import com.konarjg.jcheck.obj.CheckRoom;

public class Main extends JavaPlugin {
	
	static Main inst;
	public static CheckRoom h;
	public static Map<String, Boolean> checkedPlayers = new HashMap<String, Boolean> ();	
	
	public Main () {
		inst = this;
	}
	
	public static boolean isChecked (Player p) {
		if (!checkedPlayers.containsKey(p.getName())) return false;
		if (checkedPlayers.get(p.getName())) return true;
		return false;
	}
	
	@Override
	public void onEnable() {
		inst = this;
		FileManager.check();
		DataManager.load();
		PluginConfig.load();
		Messages.load();
		getCommand("checkroom").setExecutor(new CmdSetCheckRoom());
		getCommand("pos").setExecutor(new CmdSetPos ());
		getCommand ("check").setExecutor(new CmdCheck ());	
		Bukkit.getPluginManager().registerEvents(new PlayerDisconnectListener(), this);
	}
	
	@Override
	public void onDisable() {
		saveDefaultConfig();
		DataManager.save();
	}
	
	public static Main getInst () {
		if (inst == null) inst = new Main ();
		return inst;
	}
}