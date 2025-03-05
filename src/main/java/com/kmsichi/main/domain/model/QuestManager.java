package com.kmsichi.main.domain.model;

import org.bukkit.entity.Player;

import java.util.*;

public class QuestManager {
    private Map<UUID, List<Quest>> quests = new HashMap<>(); //UUID, QuestID

    public void register(Player p, Quest q) {
        UUID uid = p.getUniqueId();
        if (quests.containsKey(uid)) {
            quests.get(p.getUniqueId()).add(q);
        } else {
            List<Quest> questList = new ArrayList<>();
            questList.add(q);
            quests.put(uid, questList);
        }
    }

    public List<Quest> findByUUID(UUID id) {
        return quests.get(id).stream()
                .filter(q -> q.getUUID() == id).toList();
    }

    public Quest findById(int id, Player p) {
        return quests.get(p.getUniqueId()).stream()
                .filter(q -> q.getId() == id).findFirst().orElse(null);
    }

    public List<Quest> findAll(Player p) {
        List<Quest> tempQuests = new ArrayList<>();
        for (List<Quest> qs : quests.values()) {
            tempQuests.addAll(qs);
        }
        return tempQuests;
    }
}
