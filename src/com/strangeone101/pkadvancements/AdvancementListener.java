package com.strangeone101.pkadvancements;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.event.EntityBendingDeathEvent;
import com.projectkorra.projectkorra.event.PlayerChangeElementEvent;
import com.projectkorra.projectkorra.event.PlayerChangeElementEvent.Result;
import com.strangeone101.pkadvancements.advancement.Advancement;
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
