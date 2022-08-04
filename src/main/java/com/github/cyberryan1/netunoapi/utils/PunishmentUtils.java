package com.github.cyberryan1.netunoapi.utils;

import com.github.cyberryan1.netunoapi.models.ANetunoPunishment;
import com.github.cyberryan1.netunoapi.models.helpers.PunishmentType;

import java.util.List;
import java.util.stream.Collectors;

public class PunishmentUtils {

    /**
     * Returns if any of the punishments are active
     * @param punishments List of {@link ANetunoPunishment}
     * @return True if any of the punishments are active, false otherwise
     */
    public static boolean anyActive( List<ANetunoPunishment> punishments ) {
        return punishments.stream()
                .anyMatch( ANetunoPunishment::isActive );
    }

    /**
     * Returns all the punishments that are active
     * @param punishments List of {@link ANetunoPunishment}
     * @return List of {@link ANetunoPunishment} that are active
     */
    public static List<ANetunoPunishment> getActive( List<ANetunoPunishment> punishments ) {
        return punishments.stream()
                .filter( ANetunoPunishment::isActive )
                .collect( Collectors.toList() );
    }

    /**
     * Returns the punishment that is active and has the longest length remaining
     * @param punishments List of {@link ANetunoPunishment}
     * @param type The type of punishment to search for
     * @return The punishment that is active and has the longest length remaining
     */
    public static ANetunoPunishment getHighestActive( List<ANetunoPunishment> punishments, PunishmentType type ) {
        return punishments.stream()
                .filter( ( p ) -> p.getPunishmentType() == type )
                .filter( ANetunoPunishment::isActive )
                .max( ( a, b ) -> ( int ) ( getTimestampLengthRemaining( a ) - getTimestampLengthRemaining( b ) ) )
                .orElse( null );
    }

    /**
     * Returns the length remaining of the punishment before it expires, in seconds
     * @param punishment The punishment to get the length remaining of
     * @return The length remaining, in seconds
     */
    public static long getTimestampLengthRemaining( ANetunoPunishment punishment ) {
        if ( punishment.isActive() == false ) { return -1L; }
        return punishment.getLength() - ( TimeUtils.getCurrentTimestamp() - punishment.getTimestamp() );
    }
}