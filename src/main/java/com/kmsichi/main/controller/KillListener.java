package com.kmsichi.main.controller;

import com.kmsichi.main.domain.model.quest.QuestObjective;
import com.kmsichi.main.service.QuestService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillListener implements Listener {
    private final QuestService questService;

    public KillListener(QuestService questService) {
        this.questService = questService;
    }

    @EventHandler
    public void onEntityKill(EntityDeathEvent e) {
        if (e.getEntity().getKiller() != null) {
            Player p = e.getEntity().getKiller();

            for (QuestObjective objective : questService.getActiveObjectives(p)) {
                objective.onEvent(p, e);
            }
        }
    }
}
