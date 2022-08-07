package com.github.cyberryan1.netunoapi.events;

import java.util.ArrayList;
import java.util.List;

public class NetunoEventDispatcher {

    private static List<NetunoEventListener> listeners = new ArrayList<>();

    public static void addListener( NetunoEventListener listener ) {
        listeners.add( listener );
    }

    public static void removeListener( NetunoEventListener listener ) {
        listeners.remove( listener );
    }

    public static boolean containsListener( NetunoEventListener listener ) {
        return listeners.contains( listener );
    }

    public static void dispatch( NetunoEvent event ) {
        for ( NetunoEventListener listener : listeners ) {
            listener.onEvent( event );
        }
    }
}