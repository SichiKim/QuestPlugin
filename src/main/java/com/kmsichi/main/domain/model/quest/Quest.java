package com.kmsichi.main.domain.model.quest;

import java.util.List;
import java.util.UUID;

public class Quest {
    private final int id;
    private final java.util.UUID UUID;
    private final List<QuestObjective> objectives;
    private QuestState status;
    private String interactionCode;

    public Quest(int id, UUID performer_UUID, List<QuestObjective> objectives, QuestState status, String interactionCode) {
        this.id = id;
        this.UUID = performer_UUID;
        this.objectives = objectives;
        this.status = status;
        this.interactionCode = interactionCode;
    }

    public List<QuestObjective> getObjectives() {
        return objectives;
    }

    public int getId() {
        return id;
    }

    public UUID getUUID() {
        return UUID;
    }

    public QuestState getStatus() {
        return status;
    }

    public String getInteractionCode() {
        return interactionCode;
    }
}
