package com.kmsichi.main.domain.model;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Items {
    public static ItemStack getItem(String name, Material mat, int amount, String... lore){
        ItemStack i = new ItemStack(mat, amount);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> lorelist = new ArrayList<>();
        for (String l : lore) {
            lorelist.add(ChatColor.translateAlternateColorCodes('&', l));
        }
        meta.setLore(lorelist);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack getItem(String name, Material mat, int amount, List<String> lore){
        ItemStack i = new ItemStack(mat, amount);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> lorelist = new ArrayList<>();
        for (String l : lore) {
            lorelist.add(ChatColor.translateAlternateColorCodes('&', l));
        }
        meta.setLore(lorelist);
        i.setItemMeta(meta);
        return i;
    }
}
