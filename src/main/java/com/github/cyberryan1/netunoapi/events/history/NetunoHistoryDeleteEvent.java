package com.github.cyberryan1.netunoapi.events.history;

import com.github.cyberryan1.netunoapi.events.NetunoEvent;
import com.github.cyberryan1.netunoapi.models.punishments.NPunishment;
import org.bukkit.entity.Player;

/**
 * This event is fired when a punishment is deleted from a player's history.
 * For IP punishments, this event is only fired for the main punishment, not
 * any of the reference punishments.
 */
public class NetunoHistoryDeleteEvent implements NetunoEvent {

    private boolean cancelled = false;

    private final NPunishment punishment;
    private final Player player;

    public NetunoHistoryDeleteEvent( NPunishment punishment, Player player ) {
        this.punishment = punishment;
        this.player = player;
    }

    /**
     * @return The {@link NPunishment} being deleted
     */
    public NPunishment getPunishment() {
        return punishment;
    }

    /**
     * @return The {@link Player} who is deleting the punishment
     */
    public Player getPlayer() {
        return player;
    }
}