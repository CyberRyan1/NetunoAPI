package com.github.cyberryan1.netunoapi.models.punishments;

import com.github.cyberryan1.netunoapi.exceptions.ClassIncompleteException;
import com.github.cyberryan1.netunoapi.models.time.NDuration;
import org.bukkit.OfflinePlayer;

public interface NPunishment extends NPunishmentData {

    /**
     * @return The {@link PunishmentType} of this punishment
     */
    PunishmentType getPunishmentType();

    /**
     * @return The {@link PunishmentType} of this punishment
     */
    PunishmentType getType();

    /**
     * @param type The {@link PunishmentType} to set
     */
    void setPunishmentType( PunishmentType type );

    /**
     * @param type The {@link PunishmentType} to set
     */
    void setType( PunishmentType type );

    /**
     * @return The amount of seconds remaining in the punishment. Returns
     * 0 if the punishment has no length or if the punishment is not active.
     * Returns -1 if the punishment length is permanent.
     */
    long getSecondsRemaining();

    /**
     * @return False if the punishment has been designated as inactive in
     * the database, if the punishment has no length, or if the punishment
     * has expired. True otherwise.
     */
    boolean isActive();

    /**
     * @return The amount of seconds remaining in the punishment as a
     * {@link NDuration} object.
     */
    NDuration getLengthRemaining();

    /**
     * @return The length of the punishment as a {@link NDuration} object.
     */
    NDuration getTimeLength();

    /**
     * @return The player as an {@link OfflinePlayer}
     */
    OfflinePlayer getPlayer();

    /**
     * @return The staff as an {@link OfflinePlayer}
     */
    OfflinePlayer getStaff();

    /**
     * Checks if the punishment is completely filled with the correct information
     * @param requireValidId If the punishment id should be above 0 (true) or less than or equal to 0 (false)
     * @throws ClassIncompleteException If the punishment is incomplete
     */
    void ensureValid( boolean requireValidId );

    /**
     * @return A copy of this punishment
     */
    NPunishment copy();
}