package com.kmsichi.main;

import com.kmsichi.main.controller.InteractionListener;
import com.kmsichi.main.domain.model.QuestManager;
import com.kmsichi.main.controller.QuestListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

import java.io.File;

public class QuestPlugin extends JavaPlugin implements Listener {
    File guiYml = new File(this.getDataFolder()+"/GUI.yml");
    FileConfiguration guiConfig = YamlConfiguration.loadConfiguration(guiYml);

    File questDir = new File(this.getDataFolder() + "/quests");
    File interactionDir = new File(this.getDataFolder() + "/interactions");

    public static QuestPlugin plugin; {
        plugin = this;
    }

    @Override
    public void onEnable() {
        initialize();

        getServer().getPluginManager().registerEvents(new QuestListener(new QuestManager()), this);
        getServer().getPluginManager().registerEvents(new InteractionListener(), this);
        getLogger().info("퀘스트 플러그인이 활성화되었습니다.");
    }

    @Override
    public void onDisable() {
        getLogger().info("퀘스트 플러그인이 비 활성화 되었습니다.");
    }

    private void initialize() {
        /*
        try {

        } catch (IOException | NullPointerException e) {
            getLogger().warning("파일을 불러오는 데 실패했습니다.");
            e.printStackTrace();
        }*/
    }

    /*@Override
    public void onPluginMessageReceived(String channel, Player player,byte[] message){
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("SomeSubChannel")) {
            // Use the code sample in the 'Response' sections below to read
            // the data.
        }
    }*/
}