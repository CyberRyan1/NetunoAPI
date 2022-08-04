package com.github.cyberryan1.netunoapi.database;

import java.sql.Connection;

/**
 * This class is used to connect and manage the connection to the database.
 */
public interface DatabaseConnection {

    /**
     * @return The connection to the database if it is set, null otherwise
     */
    Connection getConnection();

    /**
     * @return The connection to the database if it is set, null otherwise
     */
    Connection getConn();

    /**
     * @return True if the connection is to a SQLite database, false otherwise
     */
    boolean isSqlite();

    /**
     * @return True if the connection is to a SQL database, false otherwise
     */
    boolean isSql();
}