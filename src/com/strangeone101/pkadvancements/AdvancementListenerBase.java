package com.strangeone101.pkadvancements;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import com.strangeone101.pkadvancements.advancement.Advancement;

public class AdvancementListenerBase implements Listener {
	
	private Advancement advancement;
	
	public AdvancementListenerBase(Advancement advancement) {
		this.advancement = advancement;
		
		Bukkit.getPluginManager().registerEvents(this, PKAdvancements.instance);
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[advancement=" + (advancement.getId() == null ? "null" : advancement.getId()) + ",events=" + (this.getClass().getDeclaredMethods().length - 2) + "]";
	}
	
	public Advancement getAdvancement() {
		return advancement;
	}

}
