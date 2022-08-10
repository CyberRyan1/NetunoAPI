package com.github.cyberryan1.netunoapi.utils;

import com.github.cyberryan1.netunoapi.models.time.NDuration;

public class TimeUtils {

    /**
     * @return The current timestamp, in seconds (not milliseconds!)
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * Converts the unformatted length format into seconds,
     * returning it as a {@link NDuration}. <br>
     * Example: "1h" -> 3600 seconds, "1d" -> 86400 seconds, etc
     * @param unformatted The unformatted string to convert
     * @return The length in seconds
     */
    public static NDuration durationFromUnformatted( String unformatted ) {
        if ( unformatted.equalsIgnoreCase( "forever" ) ) { return new NDuration( true ); }
        String amount = unformatted.substring( 0, unformatted.length() - 1 );
        char unit = unformatted.charAt( unformatted.length() - 1 );
        long seconds;
        switch ( unit ) {
            case 'w':
                seconds = Long.parseLong( amount ) * 604800;
                break;
            case 'd':
                seconds = Long.parseLong( amount ) * 86400;
                break;
            case 'h':
                seconds = Long.parseLong( amount ) * 3600;
                break;
            case 'm':
                seconds = Long.parseLong( amount ) * 60;
                break;
            case 's':
                seconds = Long.parseLong( amount );
                break;
            default:
                seconds = -1;
                break;
        }

        return new NDuration( seconds );
    }

    /**
     * Converts the formatted length string into seconds,
     * returning it as a {@link NDuration}. <br>
     * Example: "1 hour" -> 3600, "3 days" -> 259200, etc
     * @param formatted The formatted length string to convert
     * @return The length in seconds
     */
    public static NDuration durationFromFormatted( String formatted ) {
        if ( formatted.equalsIgnoreCase( "forever" ) ) { return new NDuration( true ); }
        String[] split = formatted.split( " " );
        String amount = split[0];
        String unit = split[1];

        if ( unit.charAt( unit.length() - 1 ) == 's' ) { unit = unit.substring( 0, unit.length() - 1 ); }
        long seconds;
        switch ( unit ) {
            case "week":
                seconds = Long.parseLong( amount ) * 604800;
                break;
            case "day":
                seconds = Long.parseLong( amount ) * 86400;
                break;
            case "hour":
                seconds = Long.parseLong( amount ) * 3600;
                break;
            case "minute":
                seconds = Long.parseLong( amount ) * 60;
                break;
            case "second":
                seconds = Long.parseLong( amount );
                break;
            default:
                seconds = -1;
                break;
        }

        return new NDuration( seconds );
    }

    /**
     * Scales a duration by a given scale (e.g. 2x, 3x, etc)
     * and the given count.
     * @param start The duration to scale
     * @param scale The scale to use
     * @param count The count to use
     * @return The scaled duration
     */
    public static NDuration getScaledDuration( NDuration start, int scale, int count ) {
        if ( start.isForever() ) { return new NDuration( true ); }
        long startingSeconds = start.timestamp();
        long scaledSeconds = ( long ) ( startingSeconds * Math.pow( ( scale * 1F ), ( count - 1 ) ) );
        return new NDuration( scaledSeconds );
    }

    /**
     * Checks if a given unformatted length is valid.
     * @param unformattedLength The unformatted length to check
     * @return True if the length is valid, false otherwise.
     */
    public static boolean isAllowableLength( String unformattedLength ) {
        if ( unformattedLength == null || unformattedLength.length() <= 1 ) { return false; }
        if ( unformattedLength.equalsIgnoreCase( "forever" ) ) { return true; }

        char unit = unformattedLength.charAt( unformattedLength.length() - 1 );
        if ( unit != 'w' && unit != 'd' && unit != 'h' && unit != 'm' && unit != 's' ) { return false; }

        int amount;
        try {
            amount = Integer.parseInt( unformattedLength.substring( 0, unformattedLength.length() - 1 ) );
        } catch ( NumberFormatException ex ) {
            return false;
        }

        return amount > 0;
    }
}