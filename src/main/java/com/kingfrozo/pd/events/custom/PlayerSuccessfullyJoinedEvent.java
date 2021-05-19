package com.kingfrozo.pd.events.custom;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerSuccessfullyJoinedEvent extends Event {

    private Player player;
    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled;


    public PlayerSuccessfullyJoinedEvent(Player player) {
        this.player = player;
        isCancelled = false;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
