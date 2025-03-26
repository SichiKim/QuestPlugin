package com.kmsichi.main.controller;

import com.kmsichi.main.domain.model.quest.QuestObjective;
import com.kmsichi.main.service.QuestService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class CollectListener implements Listener {
    private final QuestService questService;

    public CollectListener(QuestService questService) {
        this.questService = questService;
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();

        for (QuestObjective objective : questService.getActiveObjectives(p)) {
            objective.onEvent(p, e);
        }
    }
}
