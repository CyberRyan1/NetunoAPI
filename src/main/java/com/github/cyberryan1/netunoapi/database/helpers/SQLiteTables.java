package com.github.cyberryan1.netunoapi.database.helpers;

public enum SQLiteTables {

    PUNS_TABLE( "CREATE TABLE IF NOT EXISTS punishments (" +
            "`id` INTEGER PRIMARY KEY AUTOINCREMENT," +
            "`player` STRING NOT NULL," +
            "`data` BLOB NOT NULL," +
            "`guipun` STRING NOT NULL," +
            "`reference` INTEGER NOT NULL );"
    ),

    NOTIFS_TABLE( "CREATE TABLE IF NOT EXISTS notifs (" +
            "`punid` INTEGER PRIMARY KEY," +
            "`player` STRING NOT NULL );"
    ),

    IP_TABLE( "CREATE TABLE IF NOT EXISTS ip ("  +
            "`group` INTEGER," +
            "`player` STRING NOT NULL," +
            "`ip` STRING NOT NULL );"
    ),

    NO_SIGN_NOTIFS_TABLE( "CREATE TABLE IF NOT EXISTS nosignnotifs (" +
            "`player` STRING PRIMARY KEY );"
    ),

    REPORTS_TABLE( "CREATE TABLE IF NOT EXISTS reports (" +
            "`id` INTEGER PRIMARY KEY AUTOINCREMENT," +
            "`target` STRING NOT NULL," +
            "`reporter` STRING NOT NULL," +
            "`date` STRING NOT NULL," +
            "`reason` STRING NOT NULL );"
    ),

    RANDOM_TABLE( "CREATE TABLE IF NOT EXISTS random (" +
            "`key` STRING NOT NULL," +
            "`value` STRING NOT NULL );"
    );

    private String sql;
    SQLiteTables( String sql ) {
        this.sql = sql;
    }

    public String getSql() { return sql; }
}