package com.strangeone101.pkadvancements.database;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import com.strangeone101.pkadvancements.PKAdvancements;
import com.strangeone101.pkadvancements.advancement.Advancement;

public class DBConnection {

	public static Database sql;

	public static String host = "localhost";
	public static int port = 3306;
	public static String db = "minecraft";
	public static String user = "root";
	public static String pass;
	public static boolean isOpen = false;

	public static void init() {
		sql = new SQLite(PKAdvancements.instance.getLogger(), "Loading Data from DB", "progress.db", PKAdvancements.instance.getDataFolder().getAbsolutePath());
		if (((SQLite) sql).open() == null) {
			PKAdvancements.instance.getLogger().severe("Disabling due to database error");
			return;
		}

		isOpen = true;
		try {
			if (!sql.tableExists("advancement_progress")) {
				PKAdvancements.instance.getLogger().info("No DB found, creating it.");
				sql.getConnection().setAutoCommit(false);
				String query = "CREATE TABLE `advancement_progress` (" + "PRIMARY KEY `UUID` TEXT(36) PRIMARY KEY, `PlayerName` TEXT(16));";
				sql.modifyQuery(query, false);
					
				for (Advancement advancement : Advancement.getValues()) {
					sql.modifyQuery("ALTER TABLE `advancement_progress` ADD `" + advancement.getDatabaseId() + "` INTEGER DEFAULT '0';", false);
				}
				sql.getConnection().commit();
				sql.getConnection().setAutoCommit(true);
			} else {
				try {
					DatabaseMetaData md = sql.connection.getMetaData();
					sql.getConnection().setAutoCommit(false);
					for (Advancement advancement : Advancement.getValues()) {
						if (!md.getColumns(null, null, "advancement_progress", advancement.getDatabaseId()).next()) {
							sql.modifyQuery("ALTER TABLE `advancement_progress` ADD `" + advancement.getDatabaseId() + "` INTEGER DEFAULT '0';", false);
						}
					} 
					sql.getConnection().commit();
					sql.getConnection().setAutoCommit(true);
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				
				
			}
		} catch (SQLException e) {
			PKAdvancements.instance.getLogger().severe("Error occured in DB...");
			e.printStackTrace();
		}
		
	}
	
	

	public static boolean isOpen() {
		return isOpen;
	}
}
