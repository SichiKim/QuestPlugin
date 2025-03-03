package com.kmsichi.main.domain.model;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Quest {
    private final int id;
    private final java.util.UUID UUID;
    private final Map<String, Integer> objective;
    private Map<String, Integer> progress;
    private QuestState status;
    private QuestEventListener eventListener;

    public Quest(int id, UUID performer_UUID, Map<String, Integer> objective, QuestState status) {
        this.id = id;
        this.UUID = performer_UUID;
        this.objective = objective;
        this.status = status;
        this.progress = new HashMap<>();
        for (String k : objective.keySet()) {
            progress.put(k, objective.get(k));
        }
    }

    public void triggerEvent(Player p, Object eventData) {
        if (eventListener != null) {
            eventListener.onEvent(p, eventData);
        }
    }

    public int getId() {
        return id;
    }

    public UUID getUUID() {
        return UUID;
    }

    public Map<String, Integer> getObjective() {
        return objective;
    }

    public Map<String, Integer> getProgress() {
        return progress;
    }

    public QuestState getStatus() {
        return status;
    }

    public boolean isCompleted() {
        for (String ob : objective.keySet()) {
            if (progress.getOrDefault(ob, 0) < objective.get(ob)) {
                return false;
            }
        }
        return true;
    }
}
