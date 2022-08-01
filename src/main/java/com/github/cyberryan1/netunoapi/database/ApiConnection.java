package com.github.cyberryan1.netunoapi.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

/**
 * This class is used to connect and manage the connection to the database.
 */
public class ApiConnection {

    private static Connection conn = null;
    private static JavaPlugin plugin = null;
    private static boolean isSqlite = false;
    private static boolean isSql = false;

    /**
     * Initializes the connection to the SQL database and creates all tables needed.
     * @param pl The plugin instance
     * @param host The host of the SQL database
     * @param port The port of the SQL database
     * @param database The name of the SQL database
     * @param username The username of the SQL database
     * @param password The password of the SQL database
     */
    public static void initializeSql( JavaPlugin pl, String host, int port, String database, String username, String password ) {
        isSql = true;
        plugin = pl;
        plugin.getLogger().log( Level.INFO, "Initializing SQL..." );

        plugin.getLogger().log( Level.INFO, "Establishing the connection for SQL..." );
        try {
            MysqlDataSource source = new MysqlDataSource();
            source.setServerName( host );
            source.setPortNumber( port );
            source.setDatabaseName( database );
            source.setUser( username );
            source.setPassword( password );

            conn = source.getConnection();
            if ( conn.isValid( 1000 ) == false ) {
                throw new SQLException( "Could not establish a connection: connection is invalid" );
            }
        } catch ( SQLException e ) {
            plugin.getLogger().log( Level.SEVERE, "SQL exception when establishing the connection. See error below for details" );
            throw new RuntimeException( e );
        }
        plugin.getLogger().log( Level.INFO, "Connection for SQL successfully established" );

        plugin.getLogger().log( Level.INFO, "Creating tables for SQL..." );
        try {
            Statement stmt = conn.createStatement();
            for ( SQLiteTables table : SQLiteTables.values() ) {
                stmt.execute( table.getSql() );
            }
            stmt.close();
        } catch ( SQLException e ) {
            plugin.getLogger().log( Level.SEVERE, "SQL exception when creating tables. See error below for details" );
            throw new RuntimeException( e );
        }
        plugin.getLogger().log( Level.INFO, "Successfully created all tables for SQLite" );

        plugin.getLogger().log( Level.INFO, "SQL initialization complete" );
    }

    /**
     * Initializes the connection to the SQLite database and creates all tables needed.
     * @param pl The plugin instance
     */
    public static void initializeSqlite( JavaPlugin pl ) {
        isSqlite = true;
        plugin = pl;
        plugin.getLogger().log( Level.INFO, "Initializing SQLite..." );
        String databaseName = plugin.getConfig().getString( "SQLite.Filename", "database" );

        plugin.getLogger().log( Level.INFO, "Establishing the connection for SQLite..." );
        try {
            File dataFolder = new File( plugin.getDataFolder(), databaseName + ".db" );
            Class.forName( "org.sqlite.JDBC" );
            conn = DriverManager.getConnection( "jdbc:sqlite:" + dataFolder );
        } catch ( SQLException e ) {
            plugin.getLogger().log( Level.SEVERE, "SQLite exception when establishing the connection. See error below for details" );
            throw new RuntimeException( e );
        } catch ( ClassNotFoundException e ) {
            plugin.getLogger().log( Level.SEVERE, "SQLite exception when establishing the connection" );
            plugin.getLogger().log( Level.SEVERE, "A potential reason why this happened is because you don't have the SQLite JDBC library" );
            plugin.getLogger().log( Level.SEVERE, "See error below for details" );
            throw new RuntimeException( e );
        }
        plugin.getLogger().log( Level.INFO, "Connection for SQLite successfully established" );

        plugin.getLogger().log( Level.INFO, "Creating tables for SQLite..." );
        try {
            Statement stmt = conn.createStatement();
            for ( SQLiteTables table : SQLiteTables.values() ) {
                plugin.getLogger().log( Level.INFO, "Creating table: " + table.getSql() );
                stmt.execute( table.getSql() );
            }
            stmt.close();
        } catch ( SQLException e ) {
            plugin.getLogger().log( Level.SEVERE, "SQLite exception when creating tables. See error below for details" );
            throw new RuntimeException( e );
        }
        plugin.getLogger().log( Level.INFO, "Successfully created all tables for SQLite" );

        plugin.getLogger().log( Level.INFO, "SQLite initialization complete" );
    }

    /**
     * @return The connection to the database if it is set, null otherwise
     */
    public static Connection getConnection() {
        return conn;
    }

    /**
     * @return The connection to the database if it is set, null otherwise
     */
    public static Connection getConn() {
        return conn;
    }

    /**
     * Closes the connection to the database
     */
    public static void closeConnection() {
        plugin.getLogger().log( Level.INFO, "Closing the connection to the database..." );
        try {
            conn.close();
        } catch ( SQLException e ) {
            plugin.getLogger().log( Level.SEVERE, "An error occurred when closing the database connection. See error below for details" );
            throw new RuntimeException( e );
        }
        plugin.getLogger().log( Level.INFO, "Successfully closed the database connection" );
    }

    /**
     * @return True if the connection is to a SQLite database, false otherwise
     */
    public static boolean isSqlite() {
        return isSqlite;
    }

    /**
     * @return True if the connection is to a SQL database, false otherwise
     */
    public static boolean isSql() {
        return isSql;
    }
}