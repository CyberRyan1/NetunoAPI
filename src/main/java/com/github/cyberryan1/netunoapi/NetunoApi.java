package com.github.cyberryan1.netunoapi;

import com.github.cyberryan1.netunoapi.database.DatabaseConnection;
import com.github.cyberryan1.netunoapi.database.NetunoDatabases;
import com.github.cyberryan1.netunoapi.events.NetunoEventDispatcher;
import com.github.cyberryan1.netunoapi.models.alts.NAltLoader;
import com.github.cyberryan1.netunoapi.models.players.NPlayerLoader;
import com.github.cyberryan1.netunoapi.models.punishments.NPrePunishment;
import com.github.cyberryan1.netunoapi.models.punishments.NPunishment;

public interface NetunoApi {

    /**
     * Gets the database connection
     * @return The {@link DatabaseConnection} instance
     */
    DatabaseConnection getConnectionManager();

    /**
     * Alias for {@link #getConnectionManager()}
     * @return The {@link DatabaseConnection} instance
     */
    default DatabaseConnection getConn() {
        return getConnectionManager();
    }

    /**
     * Gets the database manager
     * @return The {@link NetunoDatabases} instance
     */
    NetunoDatabases getDatabaseManager();

    /**
     * Alias for {@link #getDatabaseManager()}
     * @return The {@link NetunoDatabases} instance
     */
    default NetunoDatabases getDatabases() {
        return getDatabaseManager();
    }

    /**
     * Gets the alt loader
     * @return The {@link NAltLoader} instance
     */
    NAltLoader getAltLoader();

    /**
     * Gets the player loader
     * @return The {@link NPlayerLoader} instance
     */
    NPlayerLoader getPlayerLoader();

    /**
     * Converts the given punishment to a prepunishment
     * @param punishment The {@link NPunishment} to convert
     * @return The {@link NPrePunishment} for the given punishment
     */
    NPrePunishment getPrePunishment( NPunishment punishment );

    /**
     * @return Gets the event dispatcher
     */
    NetunoEventDispatcher getEventDispatcher();
}