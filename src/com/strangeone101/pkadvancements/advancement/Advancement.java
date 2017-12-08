package com.strangeone101.pkadvancements.advancement;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.projectkorra.projectkorra.util.ReflectionHandler;
import com.strangeone101.pkadvancements.PKAdvancements;
import com.strangeone101.pkadvancements.database.DBUtil;

public class Advancement {
	
	private static List<Advancement> values = new ArrayList<Advancement>();
	
	public static final String FRAME_DEFAULT = "task";
	public static final String FRAME_CHALLENGE = "challenge";
	public static final String FRAME_GOAL = "goal";
	
	private static int GID = 0;
	
	private String id, title, description;
	private Advancement parent = null;
	private ItemStack icon;
	private AdvancementLevel level = AdvancementLevel.NORMAL;
	private String background = "minecraft:textures/gui/advancements/backgrounds/stone.png";
	private int criteria = 1;
	private org.bukkit.advancement.Advancement vanillaAdvancement;
	private boolean hidden = false;
	private String frame = null;
	
	public Advancement(String id, String title, String description, ItemStack icon) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.icon = icon;
		
		values.add(this);
	}
	
	
	public Advancement(String id, String title, String description) {
		this(id, title, description, new ItemStack(Material.STICK));
	}
	
	public Advancement setParent(Advancement parent) {
		this.parent = parent;
		return this;
	}
	
	public Advancement addChild(Advancement child) {
		child.setParent(this);
		return this;
	}
	
	public Advancement setLevel(AdvancementLevel level) {
		this.level = level;
		return this;
	}
	
	public Advancement setHidden(boolean hidden) {
		this.hidden = hidden;
		return this;
	}
	
	public Advancement setCriteriaAmount(int criteria) {
		this.criteria = criteria;
		return this;
	}
	
	public Advancement setBackground(String background) {
		this.background = background;
		return this;
	}
	
	public Advancement setBackground(ItemStack background) {
		this.background = "minecraft:textures/" + (background.getType().isBlock() ? "block" : "item") + 
				"/" + background.getType().name() + ".png";
		return this;
	}
	
	public Advancement setFrame(String frame) {
		this.frame = frame;
		return this;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public ItemStack getIcon() {
		return icon;
	}
	
	public AdvancementLevel getLevel() {
		return level;
	}
	
	public boolean isHidden() {
		return hidden;
	}
	
	public Advancement getParent() {
		return parent;
	}
	
	@SuppressWarnings("unchecked")
	public String getJSON() {
		JSONObject json = new JSONObject();

        //
        JSONObject icon = new JSONObject();
        
        try {
        	Method asNMSCopy = ReflectionHandler.getMethod("CraftItemStack", com.projectkorra.projectkorra.util.ReflectionHandler.PackageType.CRAFTBUKKIT_INVENTORY, "asNMSCopy", ItemStack.class);
    		Object nmsCopy = asNMSCopy.invoke(null, getIcon());
    		Constructor<?> nbtTag = ReflectionHandler.getConstructor("NBTTagCompound", com.projectkorra.projectkorra.util.ReflectionHandler.PackageType.MINECRAFT_SERVER);
            Method save = ReflectionHandler.getMethod(nmsCopy.getClass(), "save", nbtTag.getDeclaringClass());
            Object updatedNbt = save.invoke(nmsCopy, nbtTag.newInstance());
            Method getString = ReflectionHandler.getMethod(updatedNbt.getClass(), "getString", String.class);
            String id = (String) getString.invoke(updatedNbt, "id");
            icon.put("item", id);
        } catch (Exception e) {
        	e.printStackTrace();
        	icon.put("item", "minecraft:stone");
        }
        
        if (getIcon().getDurability() > 0) {
        	icon.put("data", getIcon().getDurability());
        }
        
        //
        JSONObject display = new JSONObject();
        display.put("icon", icon);
        display.put("title", getTitle());
        display.put("description", getDescription());
        if (this.parent == null) {
        	display.put("background", getBackground());
        }
        display.put("hidden", hidden);
        display.put("frame", level.getFrame());
        if (frame != null) {
        	display.put("frame", frame);
        }
        
        if (!level.canBroadcast()) {
        	display.put("can_broadcast", false);
        }
        
        //
        if (parent != null) {
        	json.put("parent", PKAdvancements.instance.getDescription().getName() + ":" + parent.getId());
        }
        //
        JSONObject criteria = new JSONObject();
        
        for (int i = 0; i < 1; i++) {
        	JSONObject criteriaTag = new JSONObject();

            criteriaTag.put("trigger", "minecraft:impossible");

            criteria.put("impossible" + "_" + (i + (GID++)), criteriaTag);
        }
        /*for (int i = 0; i < this.criteria; i++) {
        	JSONObject criteriaTag = new JSONObject();

            criteriaTag.put("trigger", "minecraft:impossible");

            criteria.put("impossible" + "_" + (i + (GID++)), criteriaTag);
        }*/
        

        json.put("criteria", criteria);
        json.put("display", display);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);

        return prettyJson;	
		
	}
	
	public String getBackground() {
		return background;
	}
	
	public String getId() {
		return id;
	}
	
	public String getDatabaseId() {
		return id.replaceAll("/", "\\/");
	}
	
	public org.bukkit.advancement.Advancement getVanillaAdvancement() {
		return vanillaAdvancement;
	}
	
	public static List<Advancement> getValues() {
		return values;
	}
	
	/**
	 * Returns whether a player has completed the provided advancement
	 * 
	 * @param player The player
	 * @param advancement The advancement
	 * @return
	 */
	public static boolean playerHas(Player player, Advancement advancement) {
		return player.getAdvancementProgress(advancement.getVanillaAdvancement()).isDone();
	}
	
	/**
	 * Awards the provided player with the advancement. <b>Note</b>: If this
	 * advancement has more than one criteria to fill, it will fill them all.
	 * You might not want to use this for criteria that say, need a player
	 * to have so many kills. Use <code>addProgress()</code> instead.
	 * 
	 * @param player The player
	 * @param advancement The advancement
	 */
	public static void awardPlayer(Player player, Advancement advancement) {
		for (String criteria : player.getAdvancementProgress(advancement.getVanillaAdvancement()).getRemainingCriteria()) {
			player.getAdvancementProgress(advancement.getVanillaAdvancement()).awardCriteria(criteria);
		}
		
		DBUtil.setAdvancement(player, advancement, advancement.criteria);
	}
	
	/**
	 * Increases the progress on an advancement for the provided player.
	 * If there isn't any progress to mark, it will just mark the
	 * advancement as cleared.
	 * 
	 * @param player The player
	 * @param advancement The advancement
	 */
	public static void addProgress(Player player, Advancement advancement) {
		if (playerHas(player, advancement)) return;
		
		
		if (advancement.criteria <= 1) {
			awardPlayer(player, advancement);
			//player.getAdvancementProgress(advancement.getVanillaAdvancement()).awardCriteria((String) player.getAdvancementProgress(advancement.getVanillaAdvancement()).getRemainingCriteria().toArray()[0]);
			DBUtil.addAdvancementProgress(player, advancement);
		} else {
			if (DBUtil.getAdvancementProgress(player, advancement) + 1 >= advancement.criteria) {
				awardPlayer(player, advancement);
			} else {
				DBUtil.addAdvancementProgress(player, advancement); //So it stops adding progress after it has been awarded
			}
			/*new BukkitRunnable() {
				
				@Override
				public void run() {
					ResultSet rs2 = DBConnection.sql.readQuery("SELECT * FROM advancement_progress WHERE UUID = '" + player.getUniqueId().toString() + "'");
					try {
						int amount = rs2.getInt(advancement.getId()) + 1;
						if (amount >= advancement.criteria) {
							player.getAdvancementProgress(advancement.getVanillaAdvancement()).awardCriteria((String) player.getAdvancementProgress(advancement.getVanillaAdvancement()).getRemainingCriteria().toArray()[0]);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.runTaskAsynchronously(PKAdvancements.instance);*/
		}
		
	}


	public void setVanillaAdvancement(org.bukkit.advancement.Advancement advancement) {
		if (this.vanillaAdvancement != null) {
			try {
				throw new Exception("Cannot set vanilla advancement field after it has been set!");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		this.vanillaAdvancement = advancement;
	}
}
