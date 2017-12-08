package com.strangeone101.pkadvancements.advancement;

import com.strangeone101.pkadvancements.PKAdvancements;

public enum AdvancementLevel {
	
	EASY(0), NORMAL(1), CHALLENGING(2), LEGENDARY(3);
	
	private int level;
	
	AdvancementLevel(int level) {
		this.level = level;
	}
	
	public boolean canBroadcast() {
		return this.level <= PKAdvancements.ANNOUNCE_TO_CHAT.level;
	}
	
	public String getFrame() {
		return this.level >= 2 ? "challenge" : "task";
	}

}
