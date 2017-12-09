package com.strangeone101.pkadvancements;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.strangeone101.pkadvancements.database.DBUtil;

public class AdvancementListener implements Listener {

	public AdvancementListener() {
		Bukkit.getPluginManager().registerEvents(this, PKAdvancements.instance);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onLoginAsync(AsyncPlayerPreLoginEvent event) {
		if (event.getLoginResult() == org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.ALLOWED) {
			DBUtil.loadCache(event.getUniqueId());
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		DBUtil.unloadCache(event.getPlayer().getUniqueId(), false);
	}
}
