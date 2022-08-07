package com.github.cyberryan1.netunoapi.events.punish;

import com.github.cyberryan1.netunoapi.events.NetunoCancellableEvent;
import com.github.cyberryan1.netunoapi.events.NetunoEvent;
import com.github.cyberryan1.netunoapi.models.punishments.NPunishment;

/**
 * This event is fired before a player is punished. Note that for IP
 * punishments, this event is only fired for the main punishment,
 * not any of the reference punishments.
 */
public class NetunoPrePunishEvent implements NetunoEvent, NetunoCancellableEvent {

    private boolean cancelled = false;

    private final NPunishment punishment;
    private boolean silent;

    public NetunoPrePunishEvent( NPunishment punishment, boolean silent ) {
        this.punishment = punishment;
        this.silent = silent;
    }

    /**
     * @return The {@link NPunishment} being executed
     */
    public NPunishment getPunishment() {
        return punishment;
    }

    /**
     * @return True if the punishment will be executed silently, false otherwise
     */
    public boolean isSilent() {
        return silent;
    }

    /**
     * @param silent True if the punishment will be executed silently, false otherwise
     */
    public void setSilent( boolean silent ) {
        this.silent = silent;
    }

    /**
     * @return True if the event is cancelled, false otherwise
     */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * @param cancelled True if the event is cancelled, false otherwise
     */
    @Override
    public void setCancelled( boolean cancelled ) {
        this.cancelled = cancelled;
    }
}