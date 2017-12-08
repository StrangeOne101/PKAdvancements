package com.strangeone101.pkadvancements;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.event.EntityBendingDeathEvent;
import com.projectkorra.projectkorra.event.PlayerChangeElementEvent;
import com.projectkorra.projectkorra.event.PlayerChangeElementEvent.Result;
import com.strangeone101.pkadvancements.advancement.Advancement;

public class AdvancementListener implements Listener {

	public AdvancementListener() {
		Bukkit.getPluginManager().registerEvents(this, PKAdvancements.instance);
	}
	
	@EventHandler
	public void onElementChoose(PlayerChangeElementEvent e) {
		if (e.getResult() == Result.CHOOSE || e.getResult() == Result.ADD) {
			if (Advancement.playerHas(e.getTarget(), Advancements.GENERIC_CHOOSE)) {
				if (e.getResult() == Result.CHOOSE && !Advancement.playerHas(e.getTarget(), Advancements.GENERIC_RECHOOSE)) {
					Advancement.awardPlayer(e.getTarget(), Advancements.GENERIC_RECHOOSE);
				}
				
			} else {
				Advancement.awardPlayer(e.getTarget(), Advancements.GENERIC_CHOOSE);
				
				 /*else if (e.getElement() == Element.AIR && !Advancement.playerHas(e.getTarget(), Advancements.AIR_CHOOSE)) {
					Advancement.awardPlayer(e.getTarget(), Advancements.AIR_CHOOSE);
				}*/
			}
			
			if (e.getElement() == Element.FIRE && !Advancement.playerHas(e.getTarget(), Advancements.FIRE_CHOOSE)) {
				Advancement.awardPlayer(e.getTarget(), Advancements.FIRE_CHOOSE);
			} else if (e.getElement() == Element.WATER && !Advancement.playerHas(e.getTarget(), Advancements.WATER_CHOOSE)) {
				Advancement.awardPlayer(e.getTarget(), Advancements.WATER_CHOOSE);
			} else if (e.getElement() == Element.EARTH && !Advancement.playerHas(e.getTarget(), Advancements.EARTH_CHOOSE)) {
				Advancement.awardPlayer(e.getTarget(), Advancements.EARTH_CHOOSE);
			}
		}
	}
	
	@EventHandler
	public void onBendingKill(EntityBendingDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			Advancement.addProgress(e.getAttacker(), Advancements.GENERIC_KILL_PLAYER);
			Advancement.addProgress(e.getAttacker(), Advancements.GENERIC_KILL_PLAYER_2);
			Advancement.addProgress(e.getAttacker(), Advancements.GENERIC_KILL_PLAYER_3);
			Advancement.addProgress(e.getAttacker(), Advancements.GENERIC_KILL_PLAYER_4);
			
			if (e.getEntity().getUniqueId().toString().equals("") && !Advancement.playerHas(e.getAttacker(), Advancements.GENERIC_BEATDEV)) {
				Advancement.awardPlayer(e.getAttacker(), Advancements.GENERIC_BEATDEV);
			}
		} else {
			Advancement.addProgress(e.getAttacker(), Advancements.GENERIC_KILL_MOB);
			Advancement.addProgress(e.getAttacker(), Advancements.GENERIC_KILL_MOB_2);
			Advancement.addProgress(e.getAttacker(), Advancements.GENERIC_KILL_MOB_3);
			Advancement.addProgress(e.getAttacker(), Advancements.GENERIC_KILL_MOB_4);
			
			if (e.getEntity() instanceof Wither && !Advancement.playerHas(e.getAttacker(), Advancements.GENERIC_KILL_WITHER)) {
				Advancement.awardPlayer(e.getAttacker(), Advancements.GENERIC_KILL_WITHER);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onLogin(PlayerLoginEvent event) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (BendingPlayer.getBendingPlayer(event.getPlayer()) != null) {
					BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(event.getPlayer());
					
					if (bPlayer.getElements().size() > 1) {
						if (!Advancement.playerHas(event.getPlayer(), Advancements.GENERIC_CHOOSE)) {
							Advancement.awardPlayer(event.getPlayer(), Advancements.GENERIC_CHOOSE);
						}
						if (bPlayer.getElements().contains(Element.FIRE) && !Advancement.playerHas(event.getPlayer(), Advancements.FIRE_CHOOSE)) {
							Advancement.awardPlayer(event.getPlayer(), Advancements.FIRE_CHOOSE);
						} else if (bPlayer.getElements().contains(Element.WATER) && !Advancement.playerHas(event.getPlayer(), Advancements.WATER_CHOOSE)) {
							Advancement.awardPlayer(event.getPlayer(), Advancements.WATER_CHOOSE);
						} else if (bPlayer.getElements().contains(Element.EARTH) && !Advancement.playerHas(event.getPlayer(), Advancements.EARTH_CHOOSE)) {
							Advancement.awardPlayer(event.getPlayer(), Advancements.EARTH_CHOOSE);
						}
					}
				}
			}
			
		}.runTaskLater(PKAdvancements.instance, 10L);
		
	}
}
