package com.kmsichi.main.domain.model.quest;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

public interface QuestObjective extends Listener {
    boolean isCompleted(Player p);
    void onEvent(Player p, Event e);
}
