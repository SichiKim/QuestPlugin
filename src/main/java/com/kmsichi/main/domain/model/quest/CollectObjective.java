package com.kmsichi.main.domain.model.quest;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class CollectObjective implements QuestObjective {
    private final Material item;
    private final int amount;

    public CollectObjective(Material item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    @Override
    public boolean isCompleted(Player p) {
        int total = 0;
        for (ItemStack itemStack : p.getInventory().getContents()) {
            if (itemStack != null && itemStack.getType() == item) {
                total += itemStack.getAmount();
            }
        }
        return total >= amount;
    }

    @Override
    public void onEvent(Player p, Event e) {
        if (!(e instanceof PlayerPickupItemEvent pickupItemEvent)) return;
        else if (!pickupItemEvent.getItem().getName().equals(item.name())) return;

        if (isCompleted(p)) {
            p.sendMessage(ChatColor.GREEN + "목표 " + item.name() + "를(을) " + amount + " 개 획득하기 달성");
        }
    }
}
