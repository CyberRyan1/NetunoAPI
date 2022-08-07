package com.github.cyberryan1.netunoapi;

import com.github.cyberryan1.netunoapi.database.DatabaseConnection;
import com.github.cyberryan1.netunoapi.database.NetunoDatabases;
import com.github.cyberryan1.netunoapi.models.players.NPlayerLoader;

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
     * Gets the player loader
     * @return The {@link NPlayerLoader} instance
     */
    NPlayerLoader getPlayerLoader();
}