package com.strangeone101.pkadvancements.listeners;

import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.Element.SubElement;
import com.projectkorra.projectkorra.ability.ComboAbility;
import com.projectkorra.projectkorra.event.AbilityStartEvent;
import com.projectkorra.projectkorra.event.EntityBendingDeathEvent;
import com.projectkorra.projectkorra.event.PlayerChangeElementEvent;
import com.projectkorra.projectkorra.event.PlayerChangeElementEvent.Result;
import com.strangeone101.pkadvancements.AdvancementListenerBase;
import com.strangeone101.pkadvancements.Advancements;
import com.strangeone101.pkadvancements.PKAdvancements;
import com.strangeone101.pkadvancements.advancement.Advancement;

public class GenericListener extends AdvancementListenerBase {

	public GenericListener() {
		super(null);
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
						} if (bPlayer.getElements().contains(Element.WATER) && !Advancement.playerHas(event.getPlayer(), Advancements.WATER_CHOOSE)) {
							Advancement.awardPlayer(event.getPlayer(), Advancements.WATER_CHOOSE);
						} if (bPlayer.getElements().contains(Element.EARTH) && !Advancement.playerHas(event.getPlayer(), Advancements.EARTH_CHOOSE)) {
							Advancement.awardPlayer(event.getPlayer(), Advancements.EARTH_CHOOSE);
						}
					}
				}
			}
			
		}.runTaskLater(PKAdvancements.instance, 10L);
		
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
			Advancement.addProgress(e.getAttacker(), Advancements.GENERIC_KILL_PLAYER_5);
			Advancement.addProgress(e.getAttacker(), Advancements.GENERIC_KILL_PLAYER_6);
			
			if (e.getEntity().getUniqueId().toString().equals("") && !Advancement.playerHas(e.getAttacker(), Advancements.GENERIC_KILL_DEV)) {
				Advancement.awardPlayer(e.getAttacker(), Advancements.GENERIC_KILL_DEV);
			}
			
			if (BendingPlayer.getBendingPlayer((Player)e.getEntity()) != null && BendingPlayer.getBendingPlayer((Player)e.getEntity()).getElements().size() >= 4 &&
					!Advancement.playerHas(e.getAttacker(), Advancements.GENERIC_KILL_AVATAR)) {
				Advancement.awardPlayer(e.getAttacker(), Advancements.GENERIC_KILL_AVATAR);
			}
			
			Element element = e.getAbility().getElement();
			if (element instanceof SubElement) {
				element = ((SubElement)element).getParentElement();
			}
			if (element == Element.FIRE) {
				Advancement.addProgress(e.getAttacker(), Advancements.FIRE_KILL_PLAYER);
				Advancement.addProgress(e.getAttacker(), Advancements.FIRE_KILL_PLAYER_2);
				Advancement.addProgress(e.getAttacker(), Advancements.FIRE_KILL_PLAYER_3);
				Advancement.addProgress(e.getAttacker(), Advancements.FIRE_KILL_PLAYER_4);
				Advancement.addProgress(e.getAttacker(), Advancements.FIRE_KILL_PLAYER_5);
				Advancement.addProgress(e.getAttacker(), Advancements.FIRE_KILL_PLAYER_6);
				
				if (e.getAbility() instanceof ComboAbility && !Advancement.playerHas(e.getAttacker(), Advancements.FIRE_COMBO_DEATH)) {
					Advancement.awardPlayer(e.getAttacker(), Advancements.FIRE_COMBO_DEATH);
				}
			} else if (element == Element.WATER) {
				Advancement.addProgress(e.getAttacker(), Advancements.WATER_KILL_PLAYER);
				Advancement.addProgress(e.getAttacker(), Advancements.WATER_KILL_PLAYER_2);
				Advancement.addProgress(e.getAttacker(), Advancements.WATER_KILL_PLAYER_3);
				Advancement.addProgress(e.getAttacker(), Advancements.WATER_KILL_PLAYER_4);
				Advancement.addProgress(e.getAttacker(), Advancements.WATER_KILL_PLAYER_5);
				Advancement.addProgress(e.getAttacker(), Advancements.WATER_KILL_PLAYER_6);
				
				if (e.getAbility() instanceof ComboAbility && !Advancement.playerHas(e.getAttacker(), Advancements.WATER_COMBO_DEATH)) {
					Advancement.awardPlayer(e.getAttacker(), Advancements.WATER_COMBO_DEATH);
				}
			} else if (element == Element.EARTH) {
				Advancement.addProgress(e.getAttacker(), Advancements.EARTH_KILL_PLAYER);
				Advancement.addProgress(e.getAttacker(), Advancements.EARTH_KILL_PLAYER_2);
				Advancement.addProgress(e.getAttacker(), Advancements.EARTH_KILL_PLAYER_3);
				Advancement.addProgress(e.getAttacker(), Advancements.EARTH_KILL_PLAYER_4);
				Advancement.addProgress(e.getAttacker(), Advancements.EARTH_KILL_PLAYER_5);
				//Advancement.addProgress(e.getAttacker(), Advancements.EARTH_KILL_PLAYER_6);
				
				if (e.getAbility() instanceof ComboAbility && !Advancement.playerHas(e.getAttacker(), Advancements.EARTH_COMBO_DEATH)) {
					Advancement.awardPlayer(e.getAttacker(), Advancements.EARTH_COMBO_DEATH);
				}
			} else if (element == Element.AIR) {
				
			} else if (element == Element.CHI) {
				
			}
		} else {
			Advancement.addProgress(e.getAttacker(), Advancements.GENERIC_KILL_MOB);
			Advancement.addProgress(e.getAttacker(), Advancements.GENERIC_KILL_MOB_2);
			Advancement.addProgress(e.getAttacker(), Advancements.GENERIC_KILL_MOB_3);
			Advancement.addProgress(e.getAttacker(), Advancements.GENERIC_KILL_MOB_4);
			Advancement.addProgress(e.getAttacker(), Advancements.GENERIC_KILL_MOB_5);
			//Advancement.addProgress(e.getAttacker(), Advancements.GENERIC_KILL_MOB_6);
			
			if (e.getEntity() instanceof Wither && !Advancement.playerHas(e.getAttacker(), Advancements.GENERIC_KILL_WITHER)) {
				Advancement.awardPlayer(e.getAttacker(), Advancements.GENERIC_KILL_WITHER);
			}
		}
	}
	
	@EventHandler
	public void onAbilityCreate(AbilityStartEvent event) {
		if (event.getAbility() instanceof ComboAbility) {
			Element element = event.getAbility().getElement();
			if (element instanceof SubElement) {
				element = ((SubElement)element).getParentElement();
			}
			
			if (element == Element.WATER && !Advancement.playerHas(event.getAbility().getPlayer(), Advancements.WATER_COMBO)) {
				Advancement.awardPlayer(event.getAbility().getPlayer(), Advancements.WATER_COMBO);
			} else if (element == Element.FIRE && !Advancement.playerHas(event.getAbility().getPlayer(), Advancements.FIRE_COMBO)) {
				Advancement.awardPlayer(event.getAbility().getPlayer(), Advancements.FIRE_COMBO);
			} if (element == Element.EARTH && !Advancement.playerHas(event.getAbility().getPlayer(), Advancements.EARTH_COMBO)) {
				Advancement.awardPlayer(event.getAbility().getPlayer(), Advancements.EARTH_COMBO);
			} if (element == Element.AIR && !Advancement.playerHas(event.getAbility().getPlayer(), Advancements.WATER_COMBO)) {
				//Advancement.awardPlayer(event.getAbility().getPlayer(), Advancements.AIR_COMBO);
			} if (element == Element.CHI && !Advancement.playerHas(event.getAbility().getPlayer(), Advancements.WATER_COMBO)) {
				//Advancement.awardPlayer(event.getAbility().getPlayer(), Advancements.CHI_COMBO);
			}
		}
	}
	
	/*@EventHandler
	public void onAdvancement(PlayerAdvancementDoneEvent event) {
		if (event.getAdvancement().getKey().getNamespace().toLowerCase().startsWith("pkadvancements:")) {
			
		}
	}*/
}
