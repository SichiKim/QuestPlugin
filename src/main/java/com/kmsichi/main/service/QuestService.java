package com.kmsichi.main.service;

import com.kmsichi.main.domain.model.quest.Quest;
import com.kmsichi.main.domain.model.quest.QuestObjective;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class QuestService {
    private final static Map<UUID, Set<Quest>> quests = new HashMap<>();

    public Optional<Quest> register(Player p, Quest q) {
        UUID uid = p.getUniqueId();
        quests.computeIfAbsent(uid, k -> new HashSet<>()).add(q);
        return Optional.ofNullable(q);
    }

    public List<Quest> findByUUID(UUID id) {
        return quests.get(id).stream()
                .filter(q -> q.getUUID() == id).toList();
    }

    public Optional<Quest> findById(int id, Player p) {
        return quests.get(p.getUniqueId()).stream()
                .filter(q -> q.getId() == id).findFirst();
    }

    public Set<Quest> findAll() {
        return quests.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
    }

    public Set<QuestObjective> getActiveObjectives(Player player) {
        return quests.getOrDefault(player.getUniqueId(), new HashSet<>()) // 플레이어의 퀘스트 목록 가져오기
                .stream()
                .flatMap(quest -> quest.getObjectives().stream()) // 각 퀘스트의 목표를 스트림으로 변환
                .collect(Collectors.toSet());
    }
}
