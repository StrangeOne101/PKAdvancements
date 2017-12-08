package com.strangeone101.pkadvancements.listeners.fire;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.event.EventHandler;

import com.projectkorra.projectkorra.event.AbilityEndEvent;
import com.projectkorra.projectkorra.event.AbilityProgressEvent;
import com.projectkorra.projectkorra.event.AbilityStartEvent;
import com.projectkorra.projectkorra.firebending.HeatControl;
import com.strangeone101.pkadvancements.AdvancementListenerBase;
import com.strangeone101.pkadvancements.Advancements;
import com.strangeone101.pkadvancements.advancement.Advancement;

public class FoodCook extends AdvancementListenerBase {
	
	private Map<Integer, Long> cookIntervals = new HashMap<Integer, Long>();
	private Field heatControlType;
	private Field heatControlCookTime;

	public FoodCook() {
		super(Advancements.FIRE_COOK);
	}
	
	@EventHandler
	public void onAbilityStart(AbilityStartEvent event) {
		if (event.getAbility().getClass().equals(HeatControl.class) && !Advancement.playerHas(event.getAbility().getPlayer(), getAdvancement())) {
			HeatControl heatcontrol = (HeatControl) event.getAbility();
			
			if (heatControlType == null) {
				try {
					heatControlType = heatcontrol.getClass().getDeclaredField("heatControlType");
					heatControlType.setAccessible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if (heatControlCookTime == null) {
				try {
					heatControlCookTime = heatcontrol.getClass().getDeclaredField("cookTime");
					heatControlCookTime.setAccessible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			try {
				if (heatControlType.get(heatcontrol) == HeatControl.HeatControlType.COOK) {
					cookIntervals.put(heatcontrol.getId(), heatControlCookTime.getLong(heatcontrol));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@EventHandler
	public void onAbilityProgress(AbilityProgressEvent event) {
		if (event.getAbility().getClass().equals(HeatControl.class) && cookIntervals.containsKey(((HeatControl)event.getAbility()).getId()) && 
				!Advancement.playerHas(event.getAbility().getPlayer(), getAdvancement())) {
			try {
				long newCookTime = heatControlCookTime.getLong(event.getAbility());
				if (newCookTime > cookIntervals.get(((HeatControl)event.getAbility()).getId())) {
					Advancement.awardPlayer(event.getAbility().getPlayer(), getAdvancement());
					cookIntervals.remove(((HeatControl)event.getAbility()).getId());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@EventHandler
	public void onAbilityRemove(AbilityEndEvent event) {
		if (event.getAbility().getClass().equals(HeatControl.class) && cookIntervals.containsKey(((HeatControl)event.getAbility()).getId())) {
			cookIntervals.remove(((HeatControl)event.getAbility()).getId());
		}
	}

}
