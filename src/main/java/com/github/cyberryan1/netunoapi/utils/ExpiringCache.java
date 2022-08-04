package com.github.cyberryan1.netunoapi.utils;

import com.github.cyberryan1.netunoapi.models.time.NTimeLength;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ExpiringCache<T> {

    private Map<T, Long> cache = new HashMap<>();
    private long expirationTime = 300; // 5 minutes

    /**
     * Adds a new entry to the cache
     * @param key The new entry
     */
    public void add( T key ) {
        cache.put( key, NTimeLength.getCurrentTimestamp() );
    }

    /**
     * Checks if an entry is expired. If they key is expired and the
     * cache still contains the key, it is automatically removed. If
     * the key is not in the cache, false is returned
     * @param key The key to check
     * @return True if the key is expired or not in the cache, false otherwise
     */
    public boolean isExpired( T key ) {
        if ( cache.containsKey( key ) == false ) { return true; }
        if ( NTimeLength.getCurrentTimestamp() - cache.get( key ) > expirationTime ) {
            cache.remove( key );
            return true;
        }
        return false;
    }

    /**
     * Gets all elements in the cache that are not expired and also
     * satisfy the given predicate.
     * @param predicate The predicate to check with
     * @return A list of all unexpired elements that satisfy the predicate
     */
    public List<T> searchForMany( Predicate<? super T> predicate ) {
        return cache.keySet().stream()
                .filter( ( t ) -> isExpired( t ) == false )
                .filter( predicate )
                .collect( Collectors.toList() );
    }

    /**
     * Gets the first element in the cache that is not expired and
     * also satisfies the given predicate.
     * @param predicate The predicate to check with
     * @return The first unexpired element that satisfies the predicate, null if none were found
     */
    public T searchForOne( Predicate<? super T> predicate ) {
        List<T> list = searchForMany( predicate );
        if ( list.size() == 0 ) { return null; }
        return list.get( 0 );
    }

    /**
     * Removes the given element from the cache
     * @param key The element to remove
     */
    public void remove( T key ) {
        cache.remove( key );
    }

    /**
     * Removes all elements from the cache that satisfy the given predicate
     * @param predicate The predicate to check with
     */
    public void removeAllWhere( Predicate<? super T> predicate ) {
        cache.keySet().stream()
                .filter( predicate )
                .forEach( ( t ) -> cache.remove( t ) );
    }

    /**
     * Sets the expiration timer of the cache, in seconds.
     * The default is 300 seconds (5 minutes).
     * @param expirationTime The expiration time in seconds
     */
    public void setExpirationTime( long expirationTime ) {
        this.expirationTime = expirationTime;
    }

    /**
     * @return The expiration time of the cache, in seconds
     */
    public long getExpirationTime() {
        return expirationTime;
    }
}