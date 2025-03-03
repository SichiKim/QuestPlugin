package com.kmsichi.main.domain.model;

import org.bukkit.entity.Player;

import java.util.*;

public class QuestManager {
    private Long UID = 0L;
    private Map<UUID, List<Quest>> quests = new HashMap<>(); //UUID, QuestID
    private static int sequence = 0;

    public Long getUID() {
        return UID;
    }

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

    public List<Quest> findAll(Player p) {
        List<Quest> tempQuests = new ArrayList<>();
        for (List<Quest> qs : quests.values()) {
            tempQuests.addAll(qs);
        }
        return tempQuests;
    }
}
