package com.github.cyberryan1.netunoapi.utils;

import com.github.cyberryan1.netunoapi.models.punishments.NPunishment;
import com.github.cyberryan1.netunoapi.models.punishments.PunishmentType;
import com.github.cyberryan1.netunoapi.models.time.NTimeLength;

import java.util.List;
import java.util.stream.Collectors;

public class PunishmentUtils {

    /**
     * Returns if any of the punishments are active
     * @param punishments List of {@link NPunishment}
     * @return True if any of the punishments are active, false otherwise
     */
    public static boolean anyActive( List<NPunishment> punishments ) {
        return punishments.stream()
                .anyMatch( NPunishment::isActive );
    }

    /**
     * Returns all the punishments that are active
     * @param punishments List of {@link NPunishment}
     * @return List of {@link NPunishment} that are active
     */
    public static List<NPunishment> getActive( List<NPunishment> punishments ) {
        return punishments.stream()
                .filter( NPunishment::isActive )
                .collect( Collectors.toList() );
    }

    /**
     * Returns the punishment that is active and has the longest length remaining
     * @param punishments List of {@link NPunishment}
     * @param type The type of punishment to search for
     * @return The punishment that is active and has the longest length remaining
     */
    public static NPunishment getHighestActive( List<NPunishment> punishments, PunishmentType type ) {
        // Get all the active punishments of the specified type
        // From that list, get the punishment with the highest length remaining
        // Note that -1 means the length is permanent, so it will always be the highest
        final List<NPunishment> active = getActive( punishments );

        NPunishment highest = null;
        for ( NPunishment punishment : active ) {
            if ( punishment.getPunishmentType() == type ) {
                if ( punishment.getSecondsRemaining() == -1 ) {
                    return punishment;
                }

                if ( highest == null ) { highest = punishment; }
                else if ( punishment.getSecondsRemaining() > highest.getSecondsRemaining() ) { highest = punishment; }
            }
        }

        return highest;
    }

    /**
     * Returns the length remaining of the punishment before it expires, in seconds
     * @param punishment The punishment to get the length remaining of
     * @return The length remaining, in seconds
     */
    public static long getTimestampLengthRemaining( NPunishment punishment ) {
        if ( punishment.isActive() == false ) { return -1L; }
        return punishment.getLength() - ( NTimeLength.getCurrentTimestamp() - punishment.getTimestamp() );
    }
}