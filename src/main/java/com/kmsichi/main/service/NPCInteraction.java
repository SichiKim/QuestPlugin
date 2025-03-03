package com.kmsichi.main.service;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NPCInteraction {

    public static Map<String, Object> GUI_list = new HashMap<String, Object>();
    public static Map<String, Object> NPCDialog_list = new HashMap<String, Object>();
    public static HashMap<String, List<String>> dialog = new HashMap<String, List<String>>();
    public static HashMap<String, List<Integer>> acceptable = new HashMap<String, List<Integer>>();

    // NPC와의 상호작용을 어떻게 작동하게 만들 것인가?
    // 우클릭으로 상호작용을 해야 하기 때문에.. 일단 이벤트 구현이 필요하다.

    public void onPlayerInteract(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer(); // 클릭한 플레이어
        Entity en = e.getRightClicked(); // 클릭된 엔티티

        //우리는 NPC가 우클릭 된 경우가 필요하기 때문에, 클릭 된 엔티티가 Player인 경우를 고려한다.
        if (en instanceof Player) {
            // 처음에는 엔티티별로 할당된 대사를 출력한다.
            // 이를 위해 모든 NPC의 이름을 코드로 지정한다. town1npc1 => 001 식으로. 리스트 후술.

            // 출력을 위해서 파일에서 NPC 코드의 대사를 불러온다. 이를 위해 yaml 데이터 처리를 위한 util 생성.
        }
    }

    /*
    public static void GUIManager() throws FileNotFoundException {
        Map<String, Map<String, Object>> list = new Yaml().load(new FileReader(TestPlugin.plugin.getDataFolder() + "/GUI.yml"));
        GUI_list = list.get("Inventories");
        NPCDialog_list = list.get("NPCs");
    }

    public static void DialogManager() throws FileNotFoundException {
        File folder = new File(TestPlugin.plugin.getDataFolder() + "/Dialog/");
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.getName().endsWith(".yml")) {
                Map<String, Object> list = new Yaml().load(new FileReader(fileEntry));
                dialog.put(fileEntry.getName().replace(".yml", ""), (List<String>) list.get("dialog"));
                list = new Yaml().load(new FileReader(fileEntry));
                acceptable.put(fileEntry.getName().replace(".yml", ""), (List<Integer>) list.get("quests"));
            }
        }
    }

    public static String getDialog(String Name) {
        List<String> dialoglist = dialog.get(Name);
        int random = (int) Math.round(Math.random() * (dialoglist.size()-1));

        return dialoglist.get(random);
    }

    public static void ClickInteraction(Entity entity, Player p) {
        switch (entity.getScoreboardTags().toString()) {
            case "[town1sword]":
                ItemStack sword = Items.getItem("&f무뎌진 돌 검", Material.STONE_SWORD, 1, "\n", "&7&o무뎌져버린 돌 검이다.", "&7&o마치, 오랜 시간 방치된 것 같다...");
                p.getInventory().addItem(sword);
                List<Entity> el = p.getWorld().getEntities();
                for (Entity c : el) {
                    if (c.getScoreboardTags().contains("town1sword")) {
                        PacketPlayOutEntityDestroy pa = new PacketPlayOutEntityDestroy(c.getEntityId());
                        ((CraftPlayer) p).getHandle().c.b(pa);
                    }
                }
                break;
            case "[town1egg1]":
                if (!TestPlugin.egg_list.get(p.getUniqueId().toString()).contains(1)) {
                    p.sendMessage("");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&kab &f이상현상 포착 (1/1) &kab"));
                    p.sendMessage("");
                    PacketPlayOutEntityDestroy pa = new PacketPlayOutEntityDestroy(entity.getEntityId());
                    ((CraftPlayer) p).getHandle().c.b(pa);
                }
                break;
        }
    }*/
}
