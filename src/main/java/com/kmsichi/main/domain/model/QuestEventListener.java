package com.kmsichi.main.domain.model;

import org.bukkit.entity.Player;

public interface QuestEventListener {
    void onEvent(Player p, Object eventData);
}
