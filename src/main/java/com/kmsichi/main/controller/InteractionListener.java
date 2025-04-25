package com.kmsichi.main.controller;

import com.kmsichi.common.util.JSONUtil;
import com.kmsichi.main.QuestPlugin;
import com.kmsichi.main.domain.model.quest.Quest;
import com.kmsichi.main.domain.model.quest.QuestState;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static com.kmsichi.common.util.JSONUtil.dialogJsonLoad;
import static com.kmsichi.main.domain.model.Items.getItem;

public class InteractionListener implements Listener {

    // NPC와의 상호작용을 어떻게 작동하게 만들 것인가?
    // 우클릭으로 상호작용을 해야 하기 때문에.. 일단 이벤트 구현이 필요하다.
    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer(); // 클릭한 플레이어
        Entity en = e.getRightClicked(); // 클릭된 엔티티

        // 우리는 NPC가 우클릭 된 경우가 필요하기 때문에, 클릭 된 엔티티가 Player인 경우를 고려한다.
        if (en instanceof Player) {
            // 처음에는 엔티티별로 할당된 대사를 출력한다.
            // 이를 위해 모든 NPC의 이름을 코드로 지정한다. town1npc1 => 001 식으로.

            // 출력을 위해서 파일에서 NPC 코드의 대사를 불러온다.
            String[] dialogs = dialogJsonLoad(en.getName());
            String dialog = Objects.requireNonNull(dialogs)[(int) (Math.floor(Math.random() * dialogs.length) + 1)];

            String msg = en.getName()+": "+dialog;
            p.sendMessage(msg);

            // 퀘스트 작동 가능 시, 퀘스트 GUI를 불러온다
            for (Quest q : QuestPlugin.questService.findByUUID(p.getUniqueId())) {
                // Quest 객체에 인터랙션 코드를 넣어줬다. 해당 퀘스트가 이 엔티티 와 상호작용하는지 알기 위함이다.
                if (q.getStatus() == QuestState.READY && q.getInteractionCode().equals(en.getName())) {
                    // GUI 생성 및 열기1
                    Inventory inv = Bukkit.createInventory(null, 9, en.getName());
                    String questName = "";
                    ItemStack questBook = getItem("퀘스트: " + questName, Material.ENCHANTED_BOOK, 1, "");
                    p.openInventory(inv);
                }
            }
        }
    }
}
