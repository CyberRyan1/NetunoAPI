package com.github.cyberryan1.netunoapi.models.players;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface NPlayerLoader {

    /**
     * Gets a loaded user from the cache if they are present,
     * otherwise loads them into the cache and returns it.
     * @param player The player to load
     * @return The loaded user
     */
    NPlayer load( OfflinePlayer player );

    /**
     * Gets a loaded user from the cache if they are present,
     * otherwise loads them into the cache and returns it.
     * @param player The player to load
     * @return The loaded user
     */
    NPlayer load( Player player );

    /**
     * Gets a loaded user from the cache if they are present,
     * otherwise loads them into the cache and returns it.
     * @param uuid The uuid to load
     * @return The loaded user
     */
    NPlayer load( UUID uuid );

    /**
     * Gets a loaded user from the cache if they are present,
     * otherwise loads them into the cache and returns it.
     * @param uuid The uuid to load
     * @return The loaded user
     */
    NPlayer load( String uuid );

}