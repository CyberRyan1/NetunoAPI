package com.github.cyberryan1.netunoapi.events;

public interface NetunoCancellableEvent {

    /**
     * @return True if the event is cancelled, false otherwise
     */
    boolean isCancelled();

    /**
     * @param cancelled True if the event is cancelled, false otherwise
     */
    void setCancelled( boolean cancelled );
}
