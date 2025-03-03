package com.kmsichi.main.controller;

import com.kmsichi.main.domain.model.Quest;
import com.kmsichi.main.domain.model.QuestManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class QuestListener implements Listener {
    private QuestManager questManager;

    public QuestListener(QuestManager questManager) {
        this.questManager = questManager;
    }

    @EventHandler
    public void onEntityKill(EntityDeathEvent e) {
        if (e.getEntity().getKiller() != null) {
            Player p = e.getEntity().getKiller();
            questTrigger(p, e);
        }
    }

    private void questTrigger(Player p, Event e) {
        for (Quest q : questManager.findByUUID(p.getUniqueId())) {
            if (!q.isCompleted()) {
                q.triggerEvent(p, e);
            }
        }
    }
}
