package com.strangeone101.pkadvancements.listeners.fire;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.world.PortalCreateEvent;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.event.AbilityEndEvent;
import com.projectkorra.projectkorra.firebending.FireBlast;
import com.strangeone101.pkadvancements.AdvancementListenerBase;
import com.strangeone101.pkadvancements.Advancements;
import com.strangeone101.pkadvancements.advancement.Advancement;

public class FurnaceLight extends AdvancementListenerBase {

	public FurnaceLight() {
		super(Advancements.FIRE_FURNACE);
	}
	
	@EventHandler
	public void onAbilityEnd(AbilityEndEvent event) {
		if (event.getAbility().getClass().equals(FireBlast.class) && !Advancement.playerHas(event.getAbility().getPlayer(), getAdvancement())) {
			FireBlast fireblast = (FireBlast) event.getAbility();
			Material block = fireblast.getLocation().getBlock().getType();
			if (block == Material.FURNACE && !GeneralMethods.isRegionProtectedFromBuild(event.getAbility().getPlayer(), event.getAbility().getLocation())) {
				Advancement.awardPlayer(event.getAbility().getPlayer(), getAdvancement());
			}
		}
	}
	
	@EventHandler
	public void onPortalLight(PortalCreateEvent event) {
		//event.
	}

}
