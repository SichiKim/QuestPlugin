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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

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
                    return new Quest(num, uid, name, objectives, QuestState.READY, interactionCode);
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
