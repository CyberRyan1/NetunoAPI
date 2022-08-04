package com.github.cyberryan1.netunoapi;

import com.github.cyberryan1.netunoapi.database.DatabaseConnection;
import com.github.cyberryan1.netunoapi.database.NetunoDatabases;

public interface NetunoApi {

    DatabaseConnection getConnectionManager();

    NetunoDatabases getDatabaseManager();
}