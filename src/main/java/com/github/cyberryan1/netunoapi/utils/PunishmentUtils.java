package com.github.cyberryan1.netunoapi.utils;

import com.github.cyberryan1.netunoapi.helpers.ANetunoPunishment;

import java.util.List;
import java.util.stream.Collectors;

public class PunishmentUtils {

    public static boolean anyActive( List<ANetunoPunishment> punishments ) {
        return punishments.stream()
                .anyMatch( ANetunoPunishment::isActive );
    }

    public static List<ANetunoPunishment> getActive( List<ANetunoPunishment> punishments ) {
        return punishments.stream()
                .filter( ANetunoPunishment::isActive )
                .collect( Collectors.toList() );
    }

    public static ANetunoPunishment getHighestActive( List<ANetunoPunishment> punishments ) {
        return punishments.stream()
                .filter( ANetunoPunishment::isActive )
                .max( ( a, b ) -> ( int ) ( getTimestampLengthRemaining( a ) - getTimestampLengthRemaining( b ) ) )
                .orElse( null );
    }

    public static long getTimestampLengthRemaining( ANetunoPunishment punishment ) {
        if ( punishment.isActive() == false ) { return -1L; }
        return punishment.getLength() - ( TimeUtils.getCurrentTimestamp() - punishment.getTimestamp() );
    }
}