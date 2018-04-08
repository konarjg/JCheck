package com.konarjg.jcheck.obj;

import org.bukkit.Location;

public class CheckRoom {
	Location playerLoc;
	Location adminLoc;
	
	public CheckRoom () {
		
	}
	
	public Location getPlayerLoc() {
		return playerLoc;
	}
	
	public void setPlayerLoc(Location playerLoc) {
		this.playerLoc = playerLoc;
	}

	public Location getAdminLoc() {
		return adminLoc;
	}

	public void setAdminLoc(Location adminLoc) {
		this.adminLoc = adminLoc;
	}
}
