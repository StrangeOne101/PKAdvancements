package com.strangeone101.pkadvancements;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.strangeone101.pkadvancements.advancement.Advancement;
import com.strangeone101.pkadvancements.advancement.AdvancementLevel;

public class Advancements {
	
	public static Advancement GENERIC_CHOOSE;
	
	public static Advancement GENERIC_RECHOOSE;
	public static Advancement GENERIC_SURVIVE;
	
	public static Advancement GENERIC_KILL_PLAYER;
	public static Advancement GENERIC_KILL_PLAYER_2;
	public static Advancement GENERIC_KILL_PLAYER_3;
	public static Advancement GENERIC_KILL_PLAYER_4;
	public static Advancement GENERIC_KILL_PLAYER_5;
	public static Advancement GENERIC_KILL_PLAYER_6;
	
	public static Advancement GENERIC_KILL_MOB;
	public static Advancement GENERIC_KILL_MOB_2;
	public static Advancement GENERIC_KILL_MOB_3;
	public static Advancement GENERIC_KILL_MOB_4;
	public static Advancement GENERIC_KILL_MOB_5;
	
	public static Advancement GENERIC_KILL_WITHER;
	public static Advancement GENERIC_KILL_AVATAR;
	public static Advancement GENERIC_KILL_DEV;
	//public static Advancement GENERIC_KILL_ORION;
	public static Advancement FIRE_CHOOSE;
	
	public static Advancement FIRE_KILL_PLAYER;
	public static Advancement FIRE_KILL_PLAYER_2;
	public static Advancement FIRE_KILL_PLAYER_3;
	public static Advancement FIRE_KILL_PLAYER_4;
	public static Advancement FIRE_KILL_PLAYER_5;
	public static Advancement FIRE_KILL_PLAYER_6;
	
	public static Advancement FIRE_COOK;
	public static Advancement FIRE_RAGEBURST;
	public static Advancement FIRE_PORTAL;
	public static Advancement FIRE_FURNACE;
	public static Advancement FIRE_VOIDSAVE;
	public static Advancement FIRE_COMBO;
	public static Advancement FIRE_COMBO_DEATH;
	public static Advancement FIRE_CONTROL;
	public static Advancement FIRE_BURNDIAMONDS;
	
	public static Advancement WATER_CHOOSE;
	
	public static Advancement WATER_KILL_PLAYER;
	public static Advancement WATER_KILL_PLAYER_2;
	public static Advancement WATER_KILL_PLAYER_3;
	public static Advancement WATER_KILL_PLAYER_4;
	public static Advancement WATER_KILL_PLAYER_5;
	public static Advancement WATER_KILL_PLAYER_6;
	
	public static Advancement WATER_BUBBLE;
	public static Advancement WATER_SWIMMER;
	public static Advancement WATER_FALL;
	public static Advancement WATER_BOTTLES;
	public static Advancement WATER_ENVIROMENTALIST;
	public static Advancement WATER_MOON;
	public static Advancement WATER_VOIDSAVE;
	public static Advancement WATER_COMBO;
	public static Advancement WATER_COMBO_DEATH;
	
	public static Advancement EARTH_CHOOSE;
	
	public static Advancement EARTH_KILL_PLAYER;
	public static Advancement EARTH_KILL_PLAYER_2;
	public static Advancement EARTH_KILL_PLAYER_3;
	public static Advancement EARTH_KILL_PLAYER_4;
	public static Advancement EARTH_KILL_PLAYER_5;
	
	public static Advancement EARTH_VOIDSAVE;
	public static Advancement EARTH_COMBO;
	public static Advancement EARTH_COMBO_DEATH;
	
	public static Advancement EARTH_ROLLINGSTONES;
	public static Advancement EARTH_GROUNDSHOCK;
	public static Advancement EARTH_TRAP;
	public static Advancement EARTH_FALLKILL;
	public static Advancement EARTH_TREEDEATH;
	public static Advancement EARTH_SUFFOCATEKILL;
	public static Advancement EARTH_TERRAINLOVER;
	public static Advancement EARTH_EXTRACTION;

	
	
	public static void register() {
		
		GENERIC_CHOOSE = new Advancement("bending/main", "Bending", "Become a bender", new ItemStack(Material.BOOK));
		GENERIC_RECHOOSE = new Advancement("bending/rechoose", "Uncertainty", "Change your element", new ItemStack(Material.PUMPKIN)).setParent(GENERIC_CHOOSE);
		GENERIC_SURVIVE = new Advancement("bending/survive", "Survivor", "Survive an entire night with nothing but your bending", new ItemStack(Material.GLOWSTONE)).setParent(GENERIC_RECHOOSE);
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner("StrangeOne101");
		skull.setItemMeta(meta);
		
		GENERIC_KILL_PLAYER = new Advancement("bending/kill/player", "First kill", "Kill a player with your bending", new ItemStack(Material.STICK)).setParent(GENERIC_CHOOSE).setLevel(AdvancementLevel.EASY);
		GENERIC_KILL_PLAYER_2 = new Advancement("bending/kill/player2", "Beginner", "Kill ten players with your bending", new ItemStack(Material.WOOD_SWORD)).setParent(GENERIC_KILL_PLAYER).setLevel(AdvancementLevel.EASY).setCriteriaAmount(10);
		GENERIC_KILL_PLAYER_3 = new Advancement("bending/kill/player3", "Novice", "Kill one hundred players with your bending", new ItemStack(Material.IRON_SWORD)).setParent(GENERIC_KILL_PLAYER_2).setLevel(AdvancementLevel.NORMAL).setCriteriaAmount(100);
		GENERIC_KILL_PLAYER_4 = new Advancement("bending/kill/player4", "Expert", "Kill five hundred players with your bending", new ItemStack(Material.GOLD_SWORD)).setParent(GENERIC_KILL_PLAYER_3).setLevel(AdvancementLevel.CHALLENGING).setCriteriaAmount(500);
		GENERIC_KILL_PLAYER_5 = new Advancement("bending/kill/player5", "Master", "Kill one thousand players with your bending", new ItemStack(Material.DIAMOND_SWORD)).setParent(GENERIC_KILL_PLAYER_4).setLevel(AdvancementLevel.CHALLENGING).setCriteriaAmount(1000);
		GENERIC_KILL_PLAYER_6 = new Advancement("bending/kill/player6", "Grand Master", "Kill two thousand players with your bending", new ItemStack(Material.DIAMOND_SWORD)).setParent(GENERIC_KILL_PLAYER_5).setLevel(AdvancementLevel.CHALLENGING).setCriteriaAmount(2000);
		
		GENERIC_KILL_MOB = new Advancement("bending/kill/mob", "Mob Hunter", "Kill ten mobs with your bending", new ItemStack(Material.SKULL_ITEM, 1, (short) 0)).setParent(GENERIC_CHOOSE);
		GENERIC_KILL_MOB_2 = new Advancement("bending/kill/mob2", "Pest Control", "Kill one hundred mobs with your bending", new ItemStack(Material.SKULL_ITEM, 1, (short) 2)).setParent(GENERIC_KILL_MOB).setLevel(AdvancementLevel.NORMAL).setCriteriaAmount(100);
		GENERIC_KILL_MOB_3 = new Advancement("bending/kill/mob3", "Butcherer", "Kill five hundred mobs with your bending", new ItemStack(Material.SKULL_ITEM, 1, (short) 4)).setParent(GENERIC_KILL_MOB_2).setLevel(AdvancementLevel.CHALLENGING).setCriteriaAmount(500);
		GENERIC_KILL_MOB_4 = new Advancement("bending/kill/mob4", "Exterminator", "Kill one thousand mobs with your bending", new ItemStack(Material.SKULL_ITEM, 1, (short) 5)).setParent(GENERIC_KILL_MOB_3).setLevel(AdvancementLevel.CHALLENGING).setCriteriaAmount(1000);
		GENERIC_KILL_MOB_5 = new Advancement("bending/kill/mob5", "Terminator 2.0", "Kill ten thousand mobs with your bending", new ItemStack(Material.SKULL_ITEM, 1, (short) 3)).setParent(GENERIC_KILL_MOB_4).setLevel(AdvancementLevel.LEGENDARY).setCriteriaAmount(10000);
		
		GENERIC_KILL_WITHER = new Advancement("bending/kill_wither", "Conquerer", "Kill a wither with your bending", new ItemStack(Material.SKULL_ITEM, 1, (short) 1)).setParent(GENERIC_KILL_MOB_3).setLevel(AdvancementLevel.CHALLENGING);
		GENERIC_KILL_AVATAR = new Advancement("bending/kill_avatar", "Can't stop me", "Kill an avatar with your bending", new ItemStack(Material.NETHER_STAR)).setParent(GENERIC_KILL_PLAYER).setLevel(AdvancementLevel.NORMAL).setHidden(true).setFrame(Advancement.FRAME_CHALLENGE);
		GENERIC_KILL_DEV = new Advancement("bending/kill_dev", "Better than the Developer", "Beat the developer with your bending", skull).setParent(GENERIC_KILL_PLAYER_3).setHidden(true).setLevel(AdvancementLevel.CHALLENGING);
		//GENERIC_KILL_ORIAN = new Advancement("bending/kill_orian", "Better than the Creator", "Beat the creator of Bending with your bending", skull).setParent(GENERIC_KILL_PLAYER_3).setHidden(true).setLevel(AdvancementLevel.LEGENDARY);
		
		FIRE_CHOOSE = new Advancement("fire/choose", "Firebending", "Become a firebender", new ItemStack(Material.BLAZE_POWDER)).setBackground("minecraft:textures/gui/advancements/backgrounds/nether.png").setLevel(AdvancementLevel.EASY);
		
		FIRE_KILL_PLAYER = new Advancement("fire/kill1", "Beginner", "Kill ten player with your firebending", new ItemStack(Material.WOOD_SWORD)).setParent(FIRE_CHOOSE).setCriteriaAmount(10);;
		FIRE_KILL_PLAYER_2 = new Advancement("fire/kill2", "Novice", "Kill fifty players with your firebending", new ItemStack(Material.STONE_SWORD)).setParent(FIRE_KILL_PLAYER).setLevel(AdvancementLevel.NORMAL).setCriteriaAmount(50);
		FIRE_KILL_PLAYER_3 = new Advancement("fire/kill3", "Expert", "Kill two hundred players with your firebending", new ItemStack(Material.IRON_SWORD)).setParent(FIRE_KILL_PLAYER_2).setLevel(AdvancementLevel.CHALLENGING).setCriteriaAmount(200);
		FIRE_KILL_PLAYER_4 = new Advancement("fire/kill4", "Master Firebender", "Kill one thousand players with your firebending", new ItemStack(Material.GOLD_SWORD)).setParent(FIRE_KILL_PLAYER_3).setLevel(AdvancementLevel.LEGENDARY).setCriteriaAmount(1000);
		FIRE_KILL_PLAYER_5 = new Advancement("fire/kill5", "Grandmaster Firebender", "Kill two thousand players with your firebending", new ItemStack(Material.DIAMOND_SWORD)).setParent(FIRE_KILL_PLAYER_4).setLevel(AdvancementLevel.LEGENDARY).setCriteriaAmount(2000);
		FIRE_KILL_PLAYER_6 = new Advancement("fire/kill6", "Supreme Firelord", "Kill five thousand players with your firebending!", new ItemStack(Material.DIAMOND_SWORD)).setParent(FIRE_KILL_PLAYER_5).setLevel(AdvancementLevel.LEGENDARY).setCriteriaAmount(5000).setHidden(true);
		
		FIRE_COMBO = new Advancement("fire/combo", "Wild Flames", "Perform a firebending combo", new ItemStack(Material.STAINED_CLAY, 1, (short)14)).setParent(FIRE_CHOOSE);
		FIRE_COMBO_DEATH = new Advancement("fire/combo_kill", "Deadly Flames", "Kill a player with a firebending combo", new ItemStack(Material.STAINED_CLAY, 1, (short)1)).setParent(FIRE_COMBO);
		FIRE_VOIDSAVE = new Advancement("fire/void_save", "Lifesaver", "Recover from falling in the void", new ItemStack(Material.INK_SACK, 1, (short)1)).setParent(FIRE_COMBO);
		
		FIRE_COOK = new Advancement("fire/cook", "Natrual Chef", "Cook your own food with HeatControl", new ItemStack(Material.COOKED_MUTTON)).setParent(FIRE_CHOOSE);
		FIRE_RAGEBURST = new Advancement("fire/fireburst", "Rage Burst", "Release a charged fireburst", new ItemStack(Material.NETHERRACK)).setParent(FIRE_COOK);
		FIRE_PORTAL = new Advancement("fire/portal_light", "Hell Fire", "Light a nether portal with your firebending", new ItemStack(Material.OBSIDIAN)).setParent(FIRE_CHOOSE);
		FIRE_FURNACE = new Advancement("fire/furnace", "Hot Stuff", "Light a furnace with your firebending", new ItemStack(Material.FURNACE)).setParent(FIRE_COOK);
		FIRE_CONTROL = new Advancement("fire/control", "Under Control", "Extinguish yourself with your bending", new ItemStack(Material.COAL)).setParent(FIRE_PORTAL);
		FIRE_BURNDIAMONDS = new Advancement("fire/burn_diamonds", "This wasn't meant to be!", "Burn some diamonds in fire created by firebending", new ItemStack(Material.DIAMOND)).setParent(FIRE_PORTAL).setHidden(true).setLevel(AdvancementLevel.CHALLENGING);
		
		WATER_CHOOSE = new Advancement("water/choose", "Waterbending", "Become a waterbender", new ItemStack(Material.WATER_BUCKET)).setBackground("minecraft:textures/blocks/ice_packed.png").setLevel(AdvancementLevel.EASY);
		
		WATER_KILL_PLAYER = new Advancement("water/kill1", "Beginner", "Kill one player with your waterbending", new ItemStack(Material.WOOD_SWORD)).setParent(WATER_CHOOSE);
		WATER_KILL_PLAYER_2 = new Advancement("water/kill2", "Novice", "Kill ten players with your waterbending", new ItemStack(Material.STONE_SWORD)).setParent(WATER_KILL_PLAYER).setLevel(AdvancementLevel.NORMAL).setCriteriaAmount(10);
		WATER_KILL_PLAYER_3 = new Advancement("water/kill3", "Expert", "Kill fifty players with your waterbending", new ItemStack(Material.IRON_SWORD)).setParent(WATER_KILL_PLAYER_2).setLevel(AdvancementLevel.CHALLENGING).setCriteriaAmount(50);
		WATER_KILL_PLAYER_4 = new Advancement("water/kill4", "Master Waterbender", "Kill two hundred players with your waterbending", new ItemStack(Material.GOLD_SWORD)).setParent(WATER_KILL_PLAYER_3).setLevel(AdvancementLevel.LEGENDARY).setCriteriaAmount(200);
		WATER_KILL_PLAYER_5 = new Advancement("water/kill5", "Legendary Waterbender", "Kill five hundred players with your waterbending", new ItemStack(Material.DIAMOND_SWORD)).setParent(WATER_KILL_PLAYER_4).setLevel(AdvancementLevel.LEGENDARY).setCriteriaAmount(500);
		
		WATER_COMBO = new Advancement("water/combo", "Rapid Water", "Perform a waterbending combo", new ItemStack(Material.STAINED_CLAY, 1, (short)3)).setParent(WATER_CHOOSE);
		WATER_COMBO_DEATH = new Advancement("water/combo_kill", "Extreme Waters", "Kill a player with a waterbending combo", new ItemStack(Material.STAINED_CLAY, 1, (short)11)).setParent(WATER_COMBO);
		WATER_VOIDSAVE = new Advancement("water/void_save", "Lifesaver", "Recover from falling in the void", new ItemStack(Material.INK_SACK, 1, (short)1)).setParent(WATER_COMBO);
		
		WATER_BUBBLE = new Advancement("water/bubble", "Bubble Breather", "Use WaterBubble underwater for over 10 minutes", new ItemStack(Material.WOOL, 1, (short)11)).setParent(WATER_CHOOSE);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta meta1 = (LeatherArmorMeta) boots.getItemMeta();
		meta1.setColor(Color.fromRGB(0x1010F0));
		boots.setItemMeta(meta1);
		
		WATER_SWIMMER = new Advancement("water/fastswim", "Mermaid in the making", "Swim over 1000m with fastswim in one sitting", new ItemStack(boots)).setParent(WATER_CHOOSE);
		WATER_MOON = new Advancement("water/moon", "Power in the sky", "Feel the power of the full moon", new ItemStack(Material.STAINED_GLASS)).setParent(WATER_SWIMMER); //Bend a lot during the full moon
		WATER_FALL = new Advancement("water/fall", "Soft landing", "Drop over 100 blocks without taking fall damage", new ItemStack(Material.SNOW_BLOCK)).setParent(WATER_SWIMMER);
		WATER_BOTTLES = new Advancement("water/bottles", "Always ready", "Kill a player in a desert with nothing but a waterbottle", new ItemStack(Material.POTION, 1, (short)0)).setParent(WATER_BUBBLE);
		WATER_ENVIROMENTALIST = new Advancement("water/environmentalist", "Nature Lover", "Use 50 plants as a source of water", new ItemStack(Material.LEAVES, 1, (short)0)).setParent(WATER_BUBBLE).setCriteriaAmount(50);
		
		EARTH_CHOOSE = new Advancement("earth/choose", "Earthbending", "Become an earthbender", new ItemStack(Material.GRASS)).setBackground("minecraft:textures/blocks/sand.png").setLevel(AdvancementLevel.EASY);
		
		EARTH_KILL_PLAYER = new Advancement("earth/kill1", "Beginner", "Kill one player with your earthbending", new ItemStack(Material.WOOD_SWORD)).setParent(EARTH_CHOOSE);
		EARTH_KILL_PLAYER_2 = new Advancement("earth/kill2", "Novice", "Kill ten players with your earthbending", new ItemStack(Material.STONE_SWORD)).setParent(EARTH_KILL_PLAYER).setLevel(AdvancementLevel.NORMAL).setCriteriaAmount(10);
		EARTH_KILL_PLAYER_3 = new Advancement("earth/kill3", "Expert", "Kill fifty players with your earthbending", new ItemStack(Material.IRON_SWORD)).setParent(EARTH_KILL_PLAYER_2).setLevel(AdvancementLevel.CHALLENGING).setCriteriaAmount(50);
		EARTH_KILL_PLAYER_4 = new Advancement("earth/kill4", "Master Waterbender", "Kill two hundred players with your earthbending", new ItemStack(Material.GOLD_SWORD)).setParent(EARTH_KILL_PLAYER_3).setLevel(AdvancementLevel.LEGENDARY).setCriteriaAmount(200);
		EARTH_KILL_PLAYER_5 = new Advancement("earth/kill5", "Legendary Waterbender", "Kill five hundred players with your earthbending", new ItemStack(Material.DIAMOND_SWORD)).setParent(EARTH_KILL_PLAYER_4).setLevel(AdvancementLevel.LEGENDARY).setCriteriaAmount(500);
		
		EARTH_COMBO = new Advancement("earth/combo", "Rigid Earth", "Perform an earthbending combo", new ItemStack(Material.STAINED_CLAY, 1, (short)5)).setParent(EARTH_CHOOSE);
		EARTH_COMBO_DEATH = new Advancement("earth/combo_kill", "Piercing Earth", "Perform a waterbending combo", new ItemStack(Material.STAINED_CLAY, 1, (short)13)).setParent(EARTH_COMBO);
		
		EARTH_ROLLINGSTONES = new Advancement("earth/earthsmash", "Rolling stones", "Ride on the top of an EarthSmash", new ItemStack(Material.STONE)).setParent(EARTH_CHOOSE);
		EARTH_FALLKILL = new Advancement("earth/fall_kill", "Sky high", "Kill a player by blasting them in the air with catapult", new ItemStack(Material.GRASS)).setParent(EARTH_CHOOSE);
		EARTH_TREEDEATH = new Advancement("earth/tree_death", "Too many leaves", "Die from catapulting up and landing on a tree", new ItemStack(Material.LOG)).setParent(EARTH_FALLKILL);
		EARTH_EXTRACTION = new Advancement("earth/extraction", "Shiny!", "Extract some gold from an ore with extraction", new ItemStack(Material.GOLD_INGOT)).setParent(EARTH_CHOOSE);
		
	
	}
	
}
