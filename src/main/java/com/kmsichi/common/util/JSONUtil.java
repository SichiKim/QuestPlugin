package com.kmsichi.common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kmsichi.main.QuestPlugin;
import com.kmsichi.main.domain.model.quest.*;
import com.kmsichi.main.service.InteractionListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public final class JSONUtil {
    public static Quest questJsonLoad(String loc, UUID uid) {
        try (Reader reader = new FileReader(loc)) {
            Gson gson = new Gson();
            Map<String, Object> data = gson.fromJson(reader, new TypeToken<Map<String, Object>>(){}.getType());
            String name = Objects.requireNonNull(data.get("name").toString());
            String interactionCode = Objects.requireNonNull(data.get("interactionCode").toString());
            if (data.get("objectives") instanceof Map) {
                Map<String, Integer> objectives = gson.fromJson(
                        gson.toJson(data.get("objectives")),
                        new TypeToken<Map<String, Object>>() {
                        }.getType()
                );
                try {
                    int num = Integer.parseInt(loc.replace(QuestPlugin.plugin.getDataFolder().toString(), ""));
                    Set<String> condition = objectives.keySet();
                    Set<QuestObjective> cond = new HashSet<>();

                    if (!condition.isEmpty()) {
                        for (String s : condition) {
                            String[] res = s.split("_");
                            String targetName = res[1]; // 죽여야할 목표
                            if (s.startsWith("kill_")) {
                                int targetCount = Integer.parseInt(res[2]);
                                QuestObjective killObject = new KillObjective(targetName, targetCount);
                                cond.add(killObject);
                            } else if (s.startsWith("getItem_")) {
                                int targetCount = Integer.parseInt(res[2]);
                                QuestObjective collectObject = new CollectObjective(targetName, targetCount);
                                cond.add(collectObject);
                            }/* else if (s.startsWith("interactionWith_")) {
                            } else if (s.startsWith("getKilled_")) {

                            } else if (s.startsWith("completeQuest_")) {

                            }*/
                        }
                    }
                    return new Quest(num, uid, cond, QuestState.READY, interactionCode);
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().warning("퀘스트 로드 중 문제가 발생했습니다. 퀘스트 파일명이 올바르지 않습니다.");
                    e.printStackTrace();
                }
            } else {
                Bukkit.getLogger().warning("퀘스트 로드 중 문제가 발생했습니다. 목표가 올바르게 작성되지 않았습니다.\n"+loc);
            }
        } catch (FileNotFoundException e) {
            Bukkit.getLogger().warning("파일을 찾을 수 없습니다: " + loc);
            e.printStackTrace();
        } catch (IOException e) {
            Bukkit.getLogger().warning("파일을 읽을 수 없습니다: " + loc);
            e.printStackTrace();
        } catch (NullPointerException e) {
            Bukkit.getLogger().warning("Null 값을 읽을 수 없습니다.");
            e.printStackTrace();
        }
        return null;
    }

    public static String[] dialogJsonLoad(String interactionCode) {
        String loc = QuestPlugin.plugin.getDataFolder()+ "/interactions/"+interactionCode+".json";
        try (Reader reader = new FileReader(loc)){
            Gson gson = new Gson();
            Map<String, Object> data = gson.fromJson(reader, new TypeToken<Map<String, Object>>(){}.getType());
            List<String> dialogs = gson.fromJson(gson.toJson(data.get(interactionCode)), new TypeToken<List<String>>(){}.getType());
            return dialogs.toArray(String[]::new);
        } catch (FileNotFoundException e) {
            Bukkit.getLogger().warning("파일을 찾을 수 없습니다: " + loc);
            e.printStackTrace();
        } catch (IOException e) {
            Bukkit.getLogger().warning("파일을 읽을 수 없습니다: " + loc);
            e.printStackTrace();
        }
        return null;
    }
}
