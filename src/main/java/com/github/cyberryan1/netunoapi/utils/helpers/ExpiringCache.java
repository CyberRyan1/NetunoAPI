package com.github.cyberryan1.netunoapi.utils.helpers;

import com.github.cyberryan1.netunoapi.utils.TimeUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ExpiringCache<T> {

    private Map<T, Long> cache = new HashMap<>();
    private long expirationTime = 300; // 5 minutes

    public void add( T key ) {
        cache.put( key, TimeUtils.getCurrentTimestamp() );
    }

    public boolean isExpired( T key ) {
        if ( TimeUtils.getCurrentTimestamp() - cache.get( key ) > expirationTime ) {
            cache.remove( key );
            return true;
        }
        return false;
    }

    public List<T> searchForMany( Predicate<? super T> predicate ) {
        return cache.keySet().stream()
                .filter( ( t ) -> isExpired( t ) == false )
                .filter( predicate )
                .collect( Collectors.toList() );
    }

    public T searchForOne( Predicate<? super T> predicate ) {
        List<T> list = searchForMany( predicate );
        if ( list.size() == 0 ) { return null; }
        return list.get( 0 );
    }

    public void remove( T key ) {
        cache.remove( key );
    }

    public void setExpirationTime( long expirationTime ) {
        this.expirationTime = expirationTime;
    }

    public long getExpirationTime() {
        return expirationTime;
    }
}