package com.strangeone101.pkadvancements.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.strangeone101.pkadvancements.PKAdvancements;
import com.strangeone101.pkadvancements.advancement.Advancement;

public class DBUtil {
	
	private static Map<UUID, Map<Advancement, Integer>> cache = new HashMap<UUID, Map<Advancement, Integer>>();
	private static Map<UUID, Map<Advancement, Integer>> delta = new HashMap<UUID, Map<Advancement, Integer>>();
	
	private static BukkitRunnable deltaTask;
	
	public static void setAdvancement(Player player, Advancement advancement, int amount) {
		new BukkitRunnable() {
			@Override
			public void run() {
				ResultSet rs2 = DBConnection.sql.readQuery("SELECT * FROM advancement_progress WHERE UUID = '" + player.getUniqueId().toString() + "'");
				try {
					if (!rs2.next()) {
						DBConnection.sql.modifyQuery("INSERT INTO advancement_progress (UUID, PlayerName, `" + advancement.getDatabaseId() + "`) VALUES ('" + player.getUniqueId().toString() + "', '" + player.getName() + "', " + amount + ")");
						
					} else {
						DBConnection.sql.modifyQuery("UPDATE advancement_progress SET `" + advancement.getDatabaseId() + "` = '" + amount + "', PlayerName = '" + player.getName() + "' WHERE UUID = '" + player.getUniqueId().toString() + "'", true);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}.runTaskAsynchronously(PKAdvancements.instance);
	}
	
	public static void addAdvancementProgress(Player player, Advancement advancement) {
		addDelta(player, advancement); //Add 1 to the DB
		
		if (!cache.containsKey(player.getUniqueId())) {
			cache.put(player.getUniqueId(), new HashMap<Advancement, Integer>());
		}
		
		cache.get(player.getUniqueId()).put(advancement, cache.get(player.getUniqueId()).containsKey(advancement) ? cache.get(player.getUniqueId()).get(advancement) + 1 : 1);
		
		/*new BukkitRunnable() {
			@Override
			public void run() {
				ResultSet rs2 = DBConnection.sql.readQuery("SELECT * FROM advancement_progress WHERE UUID = '" + player.getUniqueId().toString() + "'");
				try {
					if (!rs2.next()) {
						DBConnection.sql.modifyQuery("INSERT INTO advancement_progress (UUID, PlayerName, `" + advancement.getDatabaseId() + "`) VALUES ('" + player.getUniqueId().toString() + "', '" + player.getName() + "', " + 1 + ")");
						
					} else {
						int amount = rs2.getInt(advancement.getDatabaseId()) + 1;
						DBConnection.sql.modifyQuery("UPDATE advancement_progress SET `" + advancement.getDatabaseId() + "` = '" + amount + "', PlayerName = '" + player.getName() + "' WHERE UUID = '" + player.getUniqueId().toString() + "'", true);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}.runTaskAsynchronously(PKAdvancements.instance);*/
	}
	
	public static int getAdvancementProgress(Player player, Advancement advancement) {
		if (!cache.containsKey(player.getUniqueId())) {
			return 0;
		}
		
		if (!cache.get(player.getUniqueId()).containsKey(advancement)) {
			return 0;
		}
		
		return cache.get(player.getUniqueId()).get(advancement);
	}
	
	private static void addDelta(Player player, Advancement advancement) {
		if (!delta.containsKey(player.getUniqueId())) {
			delta.put(player.getUniqueId(), new HashMap<Advancement, Integer>());
		}
		
		//Add one to the delta values
		delta.get(player.getUniqueId()).put(advancement, delta.get(player.getUniqueId()).containsKey(advancement) ? delta.get(player.getUniqueId()).get(advancement) + 1 : 1);
	
		//Update the delta 2 mins later
		if (deltaTask == null) {
			deltaTask = new BukkitRunnable() {
				@Override
				public void run() {
					updateWithDelta();
					deltaTask = null;
				}
			};
			deltaTask.runTaskLater(PKAdvancements.instance, 20 * 60 * 2);
		}
	}
	
	/**
	 * Updates the database with the values that have recently been added. Clears
	 * the delta values after the DB has been updated
	 */
	public static void updateWithDelta() {
		 for (UUID id : delta.keySet()) {
			 Map<Advancement, Integer> deltaValues = delta.get(id);
			 
			 if (deltaValues != null) {
				 for (Advancement adv : deltaValues.keySet()) {
					int delta = deltaValues.get(adv);
					ResultSet rs2 = DBConnection.sql.readQuery("SELECT * FROM advancement_progress WHERE UUID = '" + id.toString() + "'");
					try {
						if (!rs2.next()) {
							DBConnection.sql.modifyQuery("INSERT INTO advancement_progress (UUID, PlayerName, `" + adv.getDatabaseId() + "`) VALUES ('" + id.toString() + "', '" + Bukkit.getOfflinePlayer(id).getName() + "', " + 1 + ")");
								
						} else {
							DBConnection.sql.modifyQuery("UPDATE advancement_progress SET `" + adv.getDatabaseId() + "` = '" + adv.getDatabaseId() + "' + " + delta + " WHERE UUID = '" + id.toString() + "'", true);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				 }
			 }
		 }
		 delta.clear();
	}
	
	/**
	 * Unloads a player's cache. If it is not forced, the
	 * cache will be removed in a minute as long as the player
	 * remains offline
	 * @param uuid The UUID
	 * @param force If it should be instant or not
	 */
	public static void unloadCache(UUID uuid, boolean force) {
		if (force) {
			cache.remove(uuid);
			return;
		} 
		
		new BukkitRunnable() {

			@Override
			public void run() {
				if (Bukkit.getOfflinePlayer(uuid).isOnline()) return; //Don't unload if the player is still online
				
				cache.remove(uuid);
			}
			
		}.runTaskLater(PKAdvancements.instance, 20 * 60); //1 min later
	}
	
	/**
	 * Loads the cache of a player from the DB
	 * @param uuid The player UUID
	 */
	public static void loadCache(UUID uuid) {
		if (cache.containsKey(uuid)) cache.remove(uuid); //Remove old cache if it's there
		cache.put(uuid, new HashMap<Advancement, Integer>());
		
		ResultSet rs = DBConnection.sql.readQuery("SELECT * FROM advancement_progress WHERE UUID = '" + uuid.toString() + "'");
	
		try {
			while (rs.next()) {
				for (Advancement advancement : Advancement.getValues()) {
					try {
						rs.findColumn(advancement.getDatabaseId()); //This will throw an exception if it isn't found
						cache.get(uuid).put(advancement, rs.getInt(advancement.getDatabaseId()));
					} catch (SQLException e) {continue;}
				}
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Forcefully unloads all cache. Used on reloads/shutdown
	 */
	public static void unloadAllCache() {
		for (UUID uuid : cache.keySet()) {
			unloadCache(uuid, true);
		}
	}

}
