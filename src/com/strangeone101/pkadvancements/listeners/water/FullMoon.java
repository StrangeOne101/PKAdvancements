package com.strangeone101.pkadvancements.listeners.water;

import org.bukkit.event.EventHandler;

import com.projectkorra.projectkorra.ability.WaterAbility;
import com.projectkorra.projectkorra.event.AbilityStartEvent;
import com.strangeone101.pkadvancements.AdvancementListenerBase;
import com.strangeone101.pkadvancements.Advancements;
import com.strangeone101.pkadvancements.advancement.Advancement;

public class FullMoon extends AdvancementListenerBase {

	public FullMoon() {
		super(Advancements.WATER_MOON);
	}
	
	@EventHandler
	public void onAbilityCreate(AbilityStartEvent event) {
		if (event.getAbility() instanceof WaterAbility && !Advancement.playerHas(event.getAbility().getPlayer(), getAdvancement())) {
			if (WaterAbility.isFullMoon(event.getAbility().getPlayer().getWorld())) {
				Advancement.awardPlayer(event.getAbility().getPlayer(), getAdvancement());
			}
		}
	}

}
