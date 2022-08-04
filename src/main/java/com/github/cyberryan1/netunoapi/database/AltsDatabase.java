package com.github.cyberryan1.netunoapi.database;

import com.github.cyberryan1.netunoapi.models.alts.NAltGroup;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public interface AltsDatabase {

    /**
     * @return The cache of punishments
     */
    List<NAltGroup> getCache();

    /**
     * Loads a player's uuid and their current IP into the cache
     * @param player The player to load
     */
    void loadPlayer( Player player );

    /**
     * Loads a player uuid and their current IP into the cache
     * @param playerUuid The player uuid
     * @param playerIp The player ip
     */
    void loadPlayer( String playerUuid, String playerIp );

    /**
     * Returns a list of UUID strings of all alts a player
     * has logged in the cache, including their own.
     * @param player The player to get the alts of
     * @return The alts of the player
     */
    List<String> getAltUuids( OfflinePlayer player );

    /**
     * Returns a list of {@link OfflinePlayer} objects of all
     * alts a player has logged in the cache, including themselves.
     * @param player The player to get the alts of
     * @return The alts of the player
     */
    List<OfflinePlayer> getAlts( OfflinePlayer player );
}