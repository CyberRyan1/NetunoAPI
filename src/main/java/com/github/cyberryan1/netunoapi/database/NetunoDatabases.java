package com.github.cyberryan1.netunoapi.database;

/**
 * This class is used to manage all the different database instance classes.
 */
public class NetunoDatabases {

    private static AltsDatabase altsDatabase = new AltsDatabase();
    private static PunishmentsDatabase punDatabase = new PunishmentsDatabase();
    private static RandomDatabase randomDatabase = new RandomDatabase();
    private static ReportsDatabase reportsDatabase = new ReportsDatabase();

    /**
     * @return The {@link AltsDatabase} instance
     */
    public static AltsDatabase getAltsDatabase() {
        return altsDatabase;
    }

    /**
     * @return The {@link AltsDatabase} instance
     */
    public static AltsDatabase getAlts() {
        return altsDatabase;
    }

    /**
     * @return The {@link PunishmentsDatabase} instance
     */
    public static PunishmentsDatabase getPunDatabase() {
        return punDatabase;
    }

    /**
     * @return The {@link PunishmentsDatabase} instance
     */
    public static PunishmentsDatabase getPun() {
        return punDatabase;
    }

    /**
     * @return The {@link RandomDatabase} instance
     */
    public static RandomDatabase getRandomDatabase() {
        return randomDatabase;
    }

    /**
     * @return The {@link RandomDatabase} instance
     */
    public static RandomDatabase getRandom() {
        return randomDatabase;
    }

    /**
     * @return The {@link ReportsDatabase} instance
     */
    public static ReportsDatabase getReportsDatabase() {
        return reportsDatabase;
    }

    /**
     * @return The {@link ReportsDatabase} instance
     */
    public static ReportsDatabase getReports() {
        return reportsDatabase;
    }
}