package com.kmsichi.main.controller;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractionListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        interactTrigger(p, e);
    }

    private void interactTrigger(Player p, Event e) {

    }
}
