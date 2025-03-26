package com.kmsichi.main.domain.model.quest;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KillObjective implements QuestObjective {
    private final String entityType;
    private final int count;
    private final Map<UUID, Integer> progress = new HashMap<>();

    public KillObjective(String entityType, int count) {
        this.entityType = entityType;
        this.count = count;
    }

    @Override
    public boolean isCompleted(Player p) {
        return progress.getOrDefault(p.getUniqueId(), 0) >= count;
    }

    @Override
    public void onEvent(Player p, Event e) {
        if (!(e instanceof EntityDeathEvent deathEvent)) return;
        else if (deathEvent.getEntity().getKiller() == null) return;
        else if (!deathEvent.getEntity().getType().name().equalsIgnoreCase(entityType)) return;

        UUID pid = p.getUniqueId();
        progress.put(pid, progress.getOrDefault(pid, 0) + 1);
        if (isCompleted(p)) {
            p.sendMessage(ChatColor.GREEN + "목표 " + entityType + "를(을) " + count + " 마리 처치하기 달성");
        }
    }
}
