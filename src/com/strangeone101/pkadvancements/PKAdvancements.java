package com.strangeone101.pkadvancements;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.UnsafeValues;
import org.bukkit.plugin.java.JavaPlugin;

import com.strangeone101.pkadvancements.advancement.Advancement;
import com.strangeone101.pkadvancements.advancement.AdvancementLevel;
import com.strangeone101.pkadvancements.database.DBConnection;

public class PKAdvancements extends JavaPlugin {
	
	public static final AdvancementLevel ANNOUNCE_TO_CHAT = AdvancementLevel.EASY;
	
	public static PKAdvancements instance;
	public static boolean reloaded = false;
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		super.onEnable();
		
		instance = this;
		
		Advancements.register();
		
		new AdvancementListener();
		
		DBConnection.init();
		
		if (!reloaded) {
			for (Advancement advancement : Advancement.getValues()) {
				org.bukkit.advancement.Advancement vanillaAdvancement;
				NamespacedKey key = new NamespacedKey("PKAdvancements", advancement.getId());
				try {
					vanillaAdvancement = Bukkit.getUnsafe().loadAdvancement(key, advancement.getJSON());
					advancement.setVanillaAdvancement(vanillaAdvancement);
					getLogger().info("Registered " + advancement.getId());
				} catch (IllegalArgumentException e) {
					vanillaAdvancement = Bukkit.getAdvancement(key);
					advancement.setVanillaAdvancement(vanillaAdvancement);
					getLogger().info("Reusing " + advancement.getId());
				}
				
				File file = new File(getDataFolder(), advancement.getId() + ".json");
				if (!file.exists()) {
					try {
						file.getParentFile().mkdirs();
						file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				try {
					BufferedWriter stream = new BufferedWriter(new FileWriter(file));
					
					String json = advancement.getJSON();
					
					for (String s : json.split("\n")) {
						stream.write(s);
						stream.newLine();
					}
					stream.flush();
					stream.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		} else {
			getLogger().info("Server reloaded... advancements won't be registered!");
		}
		
		getLogger().info("PK Advancements loaded!");
	}
	
	@Override
	public void reloadConfig() {
		super.reloadConfig();
		
		reloaded = true;
	}

}
