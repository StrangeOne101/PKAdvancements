package com.strangeone101.pkadvancements.listeners.water;

import org.bukkit.event.EventHandler;

import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.event.AbilityProgressEvent;
import com.projectkorra.projectkorra.waterbending.WaterBubble;
import com.strangeone101.pkadvancements.AdvancementListenerBase;
import com.strangeone101.pkadvancements.Advancements;
import com.strangeone101.pkadvancements.advancement.Advancement;

public class WaterBubble10 extends AdvancementListenerBase {

	public WaterBubble10() {
		super(Advancements.WATER_BUBBLE);
	}
	
	@EventHandler
	public void onProgress(AbilityProgressEvent event) {
		if (((CoreAbility)event.getAbility()).getCurrentTick() % 120 != 0) return; //Only check every 5 seconds
		
		if (event.getAbility().getClass().equals(WaterBubble.class) && !Advancement.playerHas(event.getAbility().getPlayer(), getAdvancement())) {
			WaterBubble bubble = (WaterBubble) event.getAbility();
			if (!bubble.getName().equals("WaterBubble")) return; //In case of AirBubble variants that extend it
			
			if (bubble.getStartTime() + 1000 * 60 * 10 > System.currentTimeMillis()) {
				Advancement.awardPlayer(event.getAbility().getPlayer(), getAdvancement());
			}
		}
				
	}

}
