package com.kmsichi.main.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InteractionListener {

    public static Map<String, Object> GUI_list = new HashMap<String, Object>();
    public static Map<String, Object> NPCDialog_list = new HashMap<String, Object>();
    public static HashMap<String, List<String>> dialog = new HashMap<String, List<String>>();
    public static HashMap<String, List<Integer>> acceptable = new HashMap<String, List<Integer>>();

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
    }
    */
}
