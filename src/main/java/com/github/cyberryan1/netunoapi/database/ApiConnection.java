package com.github.cyberryan1.netunoapi.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class ApiConnection {

    private static Connection conn = null;
    private static JavaPlugin plugin = null;

    public static void initializeSql( JavaPlugin pl, String host, int port, String database, String username, String password ) {
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

    public static void initializeSqlite( JavaPlugin pl ) {
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

    public static Connection getConnection() {
        return conn;
    }

    public static Connection getConn() {
        return conn;
    }

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
}