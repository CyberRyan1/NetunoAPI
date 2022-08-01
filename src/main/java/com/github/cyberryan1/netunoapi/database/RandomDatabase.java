package com.github.cyberryan1.netunoapi.database;

/**
 * This class is used to add random bits of data that might need to be saved
 * between plugin reloads that is not already stored in one of the other
 * databases. <br><br>
 * <b>(!) The use of the RandomDatabase is to be implemented on your own.
 * You pretty much <u>no use</u> for this if you are not the Netuno developer.</b>
 */
public class RandomDatabase {

    public final String TABLE_NAME = "random";
    public final String TYPE_LIST = "(key, value)";
    public final String UNKNOWN_LIST = "(?, ?)";

}