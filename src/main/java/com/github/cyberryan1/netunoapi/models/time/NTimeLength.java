package com.github.cyberryan1.netunoapi.models.time;

public class NTimeLength {

    //
    // Static Methods
    //

    /**
     * @return The current timestamp, in seconds (not milliseconds!)
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * Converts the unformatted length format into seconds,
     * returning it as a {@link NTimeLength}. <br>
     * Example: "1h" -> 3600 seconds, "1d" -> 86400 seconds, etc
     * @param unformatted The unformatted string to convert
     * @return The length in seconds
     */
    public static NTimeLength fromUnformatted( String unformatted ) {
        if ( unformatted.equalsIgnoreCase( "forever" ) ) { return new NTimeLength( true ); }
        String amount = unformatted.substring( 0, unformatted.length() - 1 );
        char unit = unformatted.charAt( unformatted.length() - 1 );
        long seconds = switch ( unit ) {
            case 'w' -> Long.parseLong( amount ) * 604800;
            case 'd' -> Long.parseLong( amount ) * 86400;
            case 'h' -> Long.parseLong( amount ) * 3600;
            case 'm' -> Long.parseLong( amount ) * 60;
            case 's' -> Long.parseLong( amount );
            default -> -1;
        };

        return new NTimeLength( seconds );
    }


    /**
     * Converts the formatted length string into seconds,
     * returning it as a {@link NTimeLength}. <br>
     * Example: "1 hour" -> 3600, "3 days" -> 259200, etc
     * @param formatted The formatted length string to convert
     * @return The length in seconds
     */
    public static NTimeLength fromFormatted( String formatted ) {
        if ( formatted.equalsIgnoreCase( "forever" ) ) { return new NTimeLength( true ); }
        String[] split = formatted.split( " " );
        String amount = split[0];
        String unit = split[1];

        if ( unit.charAt( unit.length() - 1 ) == 's' ) { unit = unit.substring( 0, unit.length() - 1 ); }
        long seconds = switch( unit ) {
            case "week" -> Long.parseLong( amount ) * 604800;
            case "day" -> Long.parseLong( amount ) * 86400;
            case "hour" -> Long.parseLong( amount ) * 3600;
            case "minute" -> Long.parseLong( amount ) * 60;
            case "second" -> Long.parseLong( amount );
            default -> -1;
        };

        return new NTimeLength( seconds );
    }

    //
    // Class Methods & Variables
    //
    private long seconds = -1;
    private boolean forever = false;

    public NTimeLength( long seconds ) {
        this.seconds = seconds;
    }

    public NTimeLength( boolean forever ) {
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
     * Converts the timestamp length into a formatted length string. <br>
     * Example: 3600 -> "1h", 259200 -> "3d", etc
     * @return The formatted length string
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
}