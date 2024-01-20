package com.github.cyberryan1.netunoapi.events;

import java.util.ArrayList;
import java.util.List;

public class NetunoEventDispatcher {

    private List<NetunoEventListener> listeners = new ArrayList<>();

    public void addListener( NetunoEventListener listener ) {
        listeners.add( listener );
    }

    public void removeListener( NetunoEventListener listener ) {
        listeners.remove( listener );
    }

    public boolean containsListener( NetunoEventListener listener ) {
        return listeners.contains( listener );
    }

    public void dispatch( NetunoEvent event ) {
        for ( NetunoEventListener listener : listeners ) {
            listener.onEvent( event );
        }
    }
}