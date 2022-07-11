package com.github.cyberryan1.netunoapi.utils;

public class TimeUtils {

    // Returns the current timestamp, in seconds
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    // Converts the unformatted punishment length format into seconds
    // Example: 1h -> 3600, 1d -> 86400, etc
    public static long getTimestampLengthFromUnformattedString( String unformattedString ) {
        // Strings are formatted with only the length and only one unit, for example 1w, 1d, 1h, 1m, 1s
        // The unit is the last character of the string, for example w, d, h, m, s
        // The length is the number before the unit
        // Return the length of time represented
        String amount = unformattedString.substring( 0, unformattedString.length() - 1 );
        char unit = unformattedString.charAt( unformattedString.length() - 1 );
        return switch ( unit ) {
            case 'w' -> Long.parseLong( amount ) * 604800;
            case 'd' -> Long.parseLong( amount ) * 86400;
            case 'h' -> Long.parseLong( amount ) * 3600;
            case 'm' -> Long.parseLong( amount ) * 60;
            case 's' -> Long.parseLong( amount );
            default -> -1;
        };
    }

    // Converts the unformatted punishment length format into a formatted length string
    // Example: 1h -> 1 hour, 3d -> 3 days, etc
    public static String getFormattedLengthFromUnformattedLength( String unformattedString ) {
        // Strings are formatted with only the length and only one unit, for example 1w, 1d, 1h, 1m, 1s
        // The unit is the last character of the string, for example w, d, h, m, s
        // The length is the number before the unit
        // Return the length of time represented
        String amount = unformattedString.substring( 0, unformattedString.length() - 1 );
        char unit = unformattedString.charAt( unformattedString.length() - 1 );
        String toReturn = switch ( unit ) {
            case 'w' -> amount + " weeks";
            case 'd' -> amount + " days";
            case 'h' -> amount + " hours";
            case 'm' -> amount + " minutes";
            case 's' -> amount + " seconds";
            default -> "";
        };

        if ( amount.equals( "1" ) ) {
            toReturn = toReturn.substring( 0, toReturn.length() - 1 );
        }
        return toReturn;
    }

    // Converts the formatted length string into a timestamp of length
    // Example: 1 hour -> 3600, 3 days -> 259200, etc
    public static long getTimestampLengthFromFormattedLength( String formattedLength ) {
        // Strings are formatted with the length and the unit, for example "1 day", "3 hours", "5 minutes", etc
        // The amount is the number before the unit, for example "1", "3", "5", etc
        // The unit is the string after the amount, for example "day", "hours", "minutes", etc
        // Return the length of time represented
        String[] split = formattedLength.split( " " );
        String amount = split[0];
        String unit = split[1];

        if ( unit.charAt( unit.length() - 1 ) == 's' ) { unit = unit.substring( 0, unit.length() - 1 ); }
        return switch( unit ) {
            case "week" -> Long.parseLong( amount ) * 604800;
            case "day" -> Long.parseLong( amount ) * 86400;
            case "hour" -> Long.parseLong( amount ) * 3600;
            case "minute" -> Long.parseLong( amount ) * 60;
            case "second" -> Long.parseLong( amount );
            default -> -1;
        };
    }

    // Converts the timestamp length into a formatted length string
    // Example: 3600 -> 1 hour, 259200 -> 3 days, etc
    public static String getFormattedLengthFromTimestampLength( long timestampLength ) {
        // Timestamp lengths are in seconds
        // Timestamps need to be converted into the correct length string
        // Example: 3600 -> 1 hour, 259200 -> 3 days, etc
        // More examples: 3603 -> 1 hour 3 seconds, 3663 -> 1 hour 1 minute 3 seconds, etc
        // Units can go all the way to weeks, days, hours, minutes, seconds
        // Return the length of time represented

        long seconds = timestampLength % 60;
        long minutes = ( timestampLength / 60 ) % 60;
        long hours = ( timestampLength / 3600 ) % 24;
        long days = ( timestampLength / 86400 ) % 7;
        long weeks = ( timestampLength / 604800 );

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
        if ( seconds > 0 ) {
            if ( toReturn.isEmpty() == false ) { toReturn += " "; }
            toReturn += seconds + " second";
            if ( seconds > 1 ) { toReturn += "s"; }
        }

        return toReturn;
    }
}