package com.github.cyberryan1.netunoapi.events.history;

import com.github.cyberryan1.netunoapi.events.NetunoEvent;
import com.github.cyberryan1.netunoapi.models.players.NPlayer;
import org.bukkit.entity.Player;

/**
 * This event is fired when a player's history is reset
 */
public class NetunoHistoryResetEvent implements NetunoEvent {

    private boolean cancelled = false;

    private final NPlayer target;
    private final Player player;

    public NetunoHistoryResetEvent( NPlayer target, Player player ) {
        this.target = target;
        this.player = player;
    }

    /**
     * @return The {@link NPlayer} whose history is being reset
     */
    public NPlayer getTarget() {
        return target;
    }

    /**
     * @return The {@link Player} who is resetting the target's history
     */
    public Player getPlayer() {
        return player;
    }
}