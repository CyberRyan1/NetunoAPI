package com.github.cyberryan1.netunoapi.events.history;

import com.github.cyberryan1.netunoapi.events.NetunoEvent;
import com.github.cyberryan1.netunoapi.models.punishments.NPunishment;
import org.bukkit.entity.Player;

/**
 * This event is fired when a player edits a punishment. For IP
 * punishments, this event is only fired for the main punishment,
 * not any of the reference punishments.
 */
public class NetunoHistoryEditEvent implements NetunoEvent {

    private boolean cancelled = false;

    private final NPunishment previousPunishment;
    private final NPunishment newPunishment;
    private final Player player;
    private final HistoryEditAction action;

    public NetunoHistoryEditEvent( NPunishment previousPunishment, NPunishment newPunishment, Player player, HistoryEditAction action ) {
        this.previousPunishment = previousPunishment;
        this.newPunishment = newPunishment;
        this.player = player;
        this.action = action;
    }

    /**
     * @return The {@link NPunishment} before the edit
     */
    public NPunishment getOldPunishment() {
        return previousPunishment;
    }

    /**
     * @return The {@link NPunishment} after the edit
     */
    public NPunishment getNewPunishment() {
        return newPunishment;
    }

    /**
     * @return The {@link Player} who is editing the punishment
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return The action that was performed
     */
    public HistoryEditAction getAction() {
        return action;
    }
}