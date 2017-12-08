package com.strangeone101.pkadvancements.listeners.water;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.ability.WaterAbility;
import com.projectkorra.projectkorra.earthbending.EarthArmor;
import com.projectkorra.projectkorra.event.AbilityProgressEvent;
import com.projectkorra.projectkorra.waterbending.WaterSpout;
import com.projectkorra.projectkorra.waterbending.multiabilities.WaterArms;
import com.projectkorra.projectkorra.waterbending.passive.FastSwim;
import com.strangeone101.pkadvancements.AdvancementListenerBase;
import com.strangeone101.pkadvancements.Advancements;
import com.strangeone101.pkadvancements.advancement.Advancement;

public class Fastswim1000 extends AdvancementListenerBase {
	
	private Map<UUID, Location> lastLocations = new HashMap<UUID, Location>();
	private Map<UUID, Double> lengths = new HashMap<UUID, Double>();
	

	public Fastswim1000() {
		super(Advancements.WATER_SWIMMER);
	}
	
	@EventHandler
	public void onProgress(AbilityProgressEvent event) {
		if (((CoreAbility)event.getAbility()).getCurrentTick() % 4 != 0) return; //Only check every 4 ticks
		
		if (event.getAbility().getClass().equals(FastSwim.class) && !Advancement.playerHas(event.getAbility().getPlayer(), getAdvancement()) &&
			BendingPlayer.getBendingPlayer(event.getAbility().getPlayer()).canBendPassive((CoreAbility) event.getAbility())) {
			if (isFastSwimming(event.getAbility().getPlayer(), (FastSwim) event.getAbility())) {
				boolean inWater = WaterAbility.isWater(event.getAbility().getPlayer().getLocation().getBlock());
				Player player = event.getAbility().getPlayer();
				if (!inWater) {
					lastLocations.put(player.getUniqueId(), player.getLocation().clone());
					return;
				}
				
				if (!lengths.containsKey(player.getUniqueId())) {
					lastLocations.put(player.getUniqueId(), player.getLocation().clone());
					lengths.put(player.getUniqueId(), 0D);
					return;
				}
				
				Location lastLoc = lastLocations.get(player.getUniqueId());
				Location currLoc = player.getLocation();
				
				double distance = Math.sqrt(lastLoc.distanceSquared(currLoc)) + lengths.get(player.getUniqueId());
				lengths.put(player.getUniqueId(), distance);
				lastLocations.put(player.getUniqueId(), currLoc.clone());
				
				if (distance > 1000) {
					Advancement.awardPlayer(player, getAdvancement());
					lastLocations.remove(event.getAbility().getPlayer().getUniqueId());
					lengths.remove(event.getAbility().getPlayer().getUniqueId());
				}
				
			} else if (lastLocations.containsKey(event.getAbility().getPlayer().getUniqueId()) || 
					lengths.containsKey(event.getAbility().getPlayer().getUniqueId())){
				lastLocations.remove(event.getAbility().getPlayer().getUniqueId());
				lengths.remove(event.getAbility().getPlayer().getUniqueId());
			}
		}
	}
	
	/**
	 * Returns if they are (possibly) fast swimming. This DOES NOT test if
	 * they are currently in water and this is because it allows for dolphin dives.
	 * But since it doesn't test for them in water, the location shouldn't be added
	 * to the last one if they aren't in water.
	 * @param player The player
	 * @param fastswim The instance
	 * @return If they are fast swimming
	 */
	private boolean isFastSwimming(Player player, FastSwim fastswim) {
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		if (!bPlayer.canUsePassive(fastswim) || !bPlayer.canBendPassive(fastswim) || 
				CoreAbility.hasAbility(player, WaterSpout.class) || CoreAbility.hasAbility(player, EarthArmor.class) || CoreAbility.hasAbility(player, WaterArms.class)) {
			return false;
		}
		
		if (bPlayer.getBoundAbility() == null || (bPlayer.getBoundAbility() != null && !bPlayer.getBoundAbility().isSneakAbility())) {
			if (player.isSneaking() && !bPlayer.isOnCooldown(fastswim)) {
				return true;
			}
		}
		
		return false;
	}

}
