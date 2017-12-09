package com.strangeone101.pkadvancements.listeners.fire;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.event.AbilityEndEvent;
import com.projectkorra.projectkorra.firebending.BlazeArc;
import com.projectkorra.projectkorra.firebending.FireBlast;
import com.strangeone101.pkadvancements.AdvancementListenerBase;
import com.strangeone101.pkadvancements.Advancements;
import com.strangeone101.pkadvancements.PKAdvancements;
import com.strangeone101.pkadvancements.advancement.Advancement;

public class NetherPortalIgnition extends AdvancementListenerBase {

	public NetherPortalIgnition() {
		super(Advancements.FIRE_PORTAL);
	}
	
	@EventHandler
	public void onAbilityEnd(AbilityEndEvent event) {
		if (event.getAbility().getClass().equals(FireBlast.class) && !Advancement.playerHas(event.getAbility().getPlayer(), getAdvancement())) {
			FireBlast fireblast = (FireBlast) event.getAbility();
			Block block = fireblast.getLocation().getBlock();
			if (block.getType() == Material.OBSIDIAN && !GeneralMethods.isRegionProtectedFromBuild(event.getAbility().getPlayer(), event.getAbility().getLocation())) {
				//This uses the vector of the FireBlast to calculate the block it was in before it hit.
				//Block lastBlock = fireblast.getLocation().clone().add(fireblast.getDirection().clone().normalize().multiply(-0.2)).getBlock();
				Block lastBlock = block.getRelative(BlockFace.UP);
				fireblast.getPlayer().sendMessage(lastBlock.getType().toString());
				new BukkitRunnable() {

					@Override
					public void run() {
						fireblast.getPlayer().sendMessage(lastBlock.getType().toString());
					}
					
				}.runTaskLater(PKAdvancements.instance, 1L);
				if (BlazeArc.isIgnitable(event.getAbility().getPlayer(), lastBlock)) {
					fireblast.getPlayer().sendMessage(lastBlock.getType().toString());
					
					
				//} else {
				//	fireblast.getPlayer().sendMessage("CurrLoc: " + block.getX() + ", " + block.getY() + ", " + block.getZ());
					//fireblast.getPlayer().sendMessage("OldLoc: " + lastBlock.getX() + ", " + lastBlock.getY() + ", " + lastBlock.getZ());
				//}
				}
				//Advancement.awardPlayer(event.getAbility().getPlayer(), getAdvancement());
			}
		}
	}

}
