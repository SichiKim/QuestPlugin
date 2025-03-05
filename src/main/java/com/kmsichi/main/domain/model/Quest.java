package com.kmsichi.main.domain.model;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Quest {
    private final int id;
    private final java.util.UUID UUID;
    private final String name;
    private final Map<String, Integer> objective;
    private Map<String, Integer> progress;
    private QuestState status;
    private QuestEventListener eventListener;
    private String interactionCode;

    public Quest(int id, UUID performer_UUID, String name, Map<String, Integer> objective, QuestState status, String interactionCode) {
        this.id = id;
        this.UUID = performer_UUID;
        this.name = name;
        this.objective = objective;
        this.status = status;
        this.progress = new HashMap<>();
        for (String k : objective.keySet()) {
            progress.put(k, 0);
        }
        this.interactionCode = interactionCode;
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

    public String getInteractionCode() {
        return interactionCode;
    }

    public String getName() {
        return name;
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
