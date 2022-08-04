package com.github.cyberryan1.netunoapi.models.time;

import java.sql.Timestamp;

/**
 * Represents a date by a timestamp.
 */
public class NDate {

    //
    // Static Methods
    //

    /**
     * @return The current timestamp, in seconds (not milliseconds!)
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    //
    // Class Methods
    //

    private long timestamp;

    public NDate( long timestamp ) {
        this.timestamp = timestamp;
    }

    public NDate() {
        this.timestamp = getCurrentTimestamp();
    }

    /**
     * @return The timestamp of the date represented
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp The timestamp of the date represented
     */
    public void setTimestamp( long timestamp ) {
        this.timestamp = timestamp;
    }

    /**
     * @return The date represented as a string (GMT time)
     */
    public String getDateString() {
        return new Timestamp( timestamp * 1000 ).toGMTString();
    }

    /**
     * @param duration The duration
     * @return True if the date represented plus the duration exceeds the current date, false otherwise
     */
    public boolean durationSinceExceedsNow( NDuration duration ) {
        if ( duration.isForever() ) { return false; }
        return timestamp + duration.timestamp() > getCurrentTimestamp();
    }

    /**
     * @param duration The duration to add to the date represented
     * @return The date represented plus the duration, null if the duration is forever
     */
    public NDate getExpirationDate( NDuration duration ) {
        if ( duration.isForever() ) { return null; }
        return new NDate( timestamp + duration.timestamp() );
    }
}