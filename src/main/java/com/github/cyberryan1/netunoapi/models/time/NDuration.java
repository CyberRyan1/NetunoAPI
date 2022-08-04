package com.github.cyberryan1.netunoapi.models.time;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a length of time, such as 5 minutes, 12 seconds, 5 hours, etc.
 * Can support any unit of time up to weeks. <br><br>
 *
 * Formatted strings look like: "12 hours", "6 days", or "1 minute". <br>
 * Unformatted strings look like: "12h", "6d", or "1m". <br>
 * Timestamp lengths are the number of seconds a length represents. For example,
 *      43200 = 12 hours, 518400 = 6 days, and 60 = 1 minute.
 * <br><br>
 *
 * Units Supported: s || seconds, m || minutes, h || hours, d || days, w || weeks
 */
public class NDuration {

    private long seconds = -1;
    private boolean forever = false;

    public NDuration( long seconds ) {
        this.seconds = seconds;
    }

    public NDuration( boolean forever ) {
        this.forever = forever;
    }

    /**
     * @return The length, in seconds
     */
    public long asTimestamp() {
        if ( forever ) { return -1; }
        return seconds;
    }

    /**
     * Alias for the {@link #asTimestamp()} method.
     * @return The length, in seconds
     */
    public long timestamp() {
        return asTimestamp();
    }

    /**
     * Converts the timestamp length into an unformatted length string. <br>
     * Example: 3600 -> "1h", 259200 -> "3d", etc
     * @return The unformatted length string
     */
    public String asUnformatted() {
        if ( forever ) { return "forever"; }
        long minutes = ( seconds / 60 ) % 60;
        long hours = ( seconds / 3600 ) % 24;
        long days = ( seconds / 86400 ) % 7;
        long weeks = ( seconds / 604800 );

        if ( weeks > 0 ) { return weeks + "w"; }
        if ( days > 0 ) { return days + "d"; }
        if ( hours > 0 ) { return hours + "h"; }
        if ( minutes > 0 ) { return minutes + "m"; }
        return seconds + "s";
    }

    /**
     * Alias for the {@link #asUnformatted()} method.
     * @return The unformatted length string
     */
    public String unformatted() {
        return asUnformatted();
    }

    /**
     * Converts the timestamp length into a formatted length string. <br>
     * Example: 3600 -> "1 hour", 259200 -> "3 days", etc
     * @return The formatted length string
     */
    public String asFormatted() {
        if ( forever ) { return "Forever"; }
        long secs = seconds % 60;
        long minutes = ( seconds / 60 ) % 60;
        long hours = ( seconds / 3600 ) % 24;
        long days = ( seconds / 86400 ) % 7;
        long weeks = ( seconds / 604800 );

        String toReturn = "";
        if ( weeks > 0 ) {
            toReturn += weeks + " week";
            if ( weeks > 1 ) { toReturn += "s"; }
        }
        if ( days > 0 ) {
            if ( toReturn.isEmpty() == false ) { toReturn += " "; }
            toReturn += days + " day";
            if ( days > 1 ) { toReturn += "s"; }
        }
        if ( hours > 0 ) {
            if ( toReturn.isEmpty() == false ) { toReturn += " "; }
            toReturn += hours + " hour";
            if ( hours > 1 ) { toReturn += "s"; }
        }
        if ( minutes > 0 ) {
            if ( toReturn.isEmpty() == false ) { toReturn += " "; }
            toReturn += minutes + " minute";
            if ( minutes > 1 ) { toReturn += "s"; }
        }
        if ( secs > 0 ) {
            if ( toReturn.isEmpty() == false ) { toReturn += " "; }
            toReturn += secs + " second";
            if ( secs > 1 ) { toReturn += "s"; }
        }

        return toReturn;
    }

    /**
     * Alias for the {@link #asFormatted()} method.
     * @return The formatted length string
     */
    public String formatted() {
        return asFormatted();
    }

    /**
     * Converts the timestamp length into a full formatted length string. <br>
     * Example: 3600 -> "1 hour", 259203 -> "3 days and 3 seconds", "3729" -> "1 hour, 2 minutes, and 9 seconds",
     * "forever" -> "Forever", "108201" -> "1 day, 6 hours, 3 minutes, and 21 seconds"
     * @return The formatted length string
     */
    public String asFullLength() {
        if ( forever ) { return "Forever"; }
        long secs = seconds % 60;
        long minutes = ( seconds / 60 ) % 60;
        long hours = ( seconds / 3600 ) % 24;
        long days = ( seconds / 86400 ) % 7;
        long weeks = ( seconds / 604800 );

        List<String> elements = new ArrayList<>();
        if ( weeks > 0 ) {
            String e = weeks + " week";
            if ( weeks > 1 ) { e += "s"; }
            elements.add( e );
        }

        if ( days > 0 ) {
            String e = days + " day";
            if ( days > 1 ) { e += "s"; }
            elements.add( e );
        }

        if ( hours > 0 ) {
            String e = hours + " hour";
            if ( hours > 1 ) { e += "s"; }
            elements.add( e );
        }

        if ( minutes > 0 ) {
            String e = minutes + " minute";
            if ( minutes > 1 ) { e += "s"; }
            elements.add( e );
        }

        if ( secs > 0 ) {
            String e = secs + " second";
            if ( secs > 1 ) { e += "s"; }
            elements.add( e );
        }

        if ( elements.size() == 0 ) { return "0 seconds"; }
        else if ( elements.size() == 1 ) { return elements.get( 0 ); }
        else if ( elements.size() == 2 ) { return elements.get( 0 ) + " and " + elements.get( 1 ); }
        else {
            String toReturn = "";
            for ( int i = 0; i < elements.size(); i++ ) {
                if ( i == elements.size() - 1 ) { toReturn += " and "; }
                else if ( i > 0 ) { toReturn += ", "; }
                toReturn += elements.get( i );
            }
            return toReturn;
        }
    }

    /**
     * Alias for the {@link #asFormatted()} method.
     * @return The full formatted length string
     */
    public String fullLength() {
        return asFullLength();
    }

    /**
     * @return True if the length is forever, false otherwise
     */
    public boolean isForever() {
        return forever;
    }
}