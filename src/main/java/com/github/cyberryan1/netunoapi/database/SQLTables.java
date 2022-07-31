package com.github.cyberryan1.netunoapi.database;

public enum SQLTables {

    PUNS_TABLE( "CREATE TABLE IF NOT EXISTS punishments (" +
            "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
            "player VARCHAR(42) NOT NULL," +
            "data BLOB NOT NULL," +
            "guipun VARCHAR(8) NOT NULL," +
            "reference INTEGER NOT NULL );"
    ),

    NOTIFS_TABLE( "CREATE TABLE IF NOT EXISTS notifs (" +
            "punid INTEGER PRIMARY KEY," +
            "player STRING NOT NULL );"
    ),

    IP_TABLE( "CREATE TABLE IF NOT EXISTS ip (" +
            "group INTEGER," +
            "player VARCHAR(42) NOT NULL," +
            "ip VARCHAR(20) NOT NULL );"
    ),

    NO_SIGN_NOTIFS_TABLE( "CREATE TABLE IF NOT EXISTS nosignnotifs (" +
            "player VARCHAR(42) PRIMARY KEY );"
    ),

    REPORTS_TABLE( "CREATE TABLE IF NOT EXISTS reports (" +
            "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
            "target VARCHAR(42) STRING NOT NULL," +
            "reporter VARCHAR(42) NOT NULL," +
            "date TEXT NOT NULL," +
            "reason TEXT NOT NULL );"
    ),

    RANDOM_TABLE( "CREATE TABLE IF NOT EXISTS random (" +
            "key TEXT NOT NULL," +
            "value TEXT NOT NULL );"
    );

    private String sql;
    SQLTables( String sql ) {
        this.sql = sql;
    }

    public String getSql() { return sql; }
}