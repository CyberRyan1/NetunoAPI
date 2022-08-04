package com.github.cyberryan1.netunoapi;

import com.github.cyberryan1.netunoapi.database.DatabaseConnection;
import com.github.cyberryan1.netunoapi.database.NetunoDatabases;
import com.github.cyberryan1.netunoapi.models.players.NPlayerLoader;

public interface NetunoApi {

    DatabaseConnection getConnectionManager();

    default DatabaseConnection getConn() {
        return getConnectionManager();
    }

    NetunoDatabases getDatabaseManager();

    default NetunoDatabases getDatabases() {
        return getDatabaseManager();
    }

    NPlayerLoader getPlayerLoader();
}