package com.kmsichi.common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kmsichi.main.QuestPlugin;
import com.kmsichi.main.domain.model.Quest;
import com.kmsichi.main.domain.model.QuestState;
import org.bukkit.Bukkit;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.UUID;

public final class JSONUtil {
    public Quest questJsonLoad(String loc, UUID uid) {
        try (Reader reader = new FileReader(loc)) {
            Gson gson = new Gson();
            Map<String, Object> data = gson.fromJson(reader, new TypeToken<Map<String, Object>>(){}.getType());
            if (data.get("objectives") instanceof Map) {
                Map<String, Integer> objectives = gson.fromJson(
                        gson.toJson(data.get("objectives")),
                        new TypeToken<Map<String, Object>>() {
                        }.getType()
                );
                try {
                    int num = Integer.parseInt(loc.replace(QuestPlugin.plugin.getDataFolder().toString(), ""));
                    return new Quest(num, uid, objectives, QuestState.READY);
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
        }
        return null;
    }
}
