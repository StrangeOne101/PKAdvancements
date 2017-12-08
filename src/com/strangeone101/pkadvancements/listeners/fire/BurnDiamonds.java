package com.strangeone101.pkadvancements.listeners.fire;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.metadata.FixedMetadataValue;

import com.projectkorra.projectkorra.util.TempBlock;
import com.strangeone101.pkadvancements.AdvancementListenerBase;
import com.strangeone101.pkadvancements.Advancements;
import com.strangeone101.pkadvancements.PKAdvancements;
import com.strangeone101.pkadvancements.advancement.Advancement;

public class BurnDiamonds extends AdvancementListenerBase {

	public BurnDiamonds() {
		super(Advancements.FIRE_BURNDIAMONDS);
	}
	
	@EventHandler
	public void onItemBurn(EntityCombustEvent event) {
		if (event.isCancelled()) return;
		
		if (event.getEntity() instanceof Item && event.getEntity().getLocation().getBlock().getType() == Material.FIRE) {
			Item item = (Item) event.getEntity();
			if (item.hasMetadata("PKA_DiamondDrop")) {
				if (TempBlock.isTempBlock(item.getLocation().getBlock())) {
					Advancement.awardPlayer(Bukkit.getPlayer(UUID.fromString(item.getMetadata("PKA_DiamondDrop").get(0).asString())), getAdvancement());
				}
			}
		}
	}
	
	@EventHandler
	public void onThrowItem(PlayerDropItemEvent event) {
		if (event.isCancelled() || Advancement.playerHas(event.getPlayer(), getAdvancement())) return;
		
		if (event.getItemDrop().getItemStack().getType() == Material.DIAMOND) {
			event.getItemDrop().setMetadata("PKA_DiamondDrop", new FixedMetadataValue(PKAdvancements.instance, event.getPlayer().getUniqueId().toString()));
		}
	}

}
