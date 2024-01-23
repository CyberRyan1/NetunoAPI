package com.github.cyberryan1.netunoapi.database;

/**
 * This class is used to manage all the different database instance classes.
 */
public interface NetunoDatabases {

    /**
     * @return The {@link IpHistoryDatabase} instance
     */
    IpHistoryDatabase getIpHistoryDatabase();

    /**
     * @return The {@link PunishmentsDatabase} instance
     */
    PunishmentsDatabase getPunDatabase();

    /**
     * @return The {@link PunishmentsDatabase} instance
     */
    PunishmentsDatabase getPun();

    /**
     * @return The {@link RandomDatabase} instance
     */
    RandomDatabase getRandomDatabase();

    /**
     * @return The {@link RandomDatabase} instance
     */
    RandomDatabase getRandom();

    /**
     * @return The {@link ReportsDatabase} instance
     */
    ReportsDatabase getReportsDatabase();

    /**
     * @return The {@link ReportsDatabase} instance
     */
    ReportsDatabase getReports();
}