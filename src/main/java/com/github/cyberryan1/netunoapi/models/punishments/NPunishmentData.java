package com.github.cyberryan1.netunoapi.models.punishments;

import org.bukkit.OfflinePlayer;

public interface NPunishmentData /*extends SQLData*/ {

    /**
     * @return The ID of the punishment.
     */
    int getId();

    /**
     * @return The string type of punishment.
     */
    String getPunishmentTypeStr();

    /**
     * @return The UUID of the player who was punished.
     */
    String getPlayerUuid();

    /**
     * @return The UUID of the staff member who punished the player.
     * May be "CONSOLE" to represent console punishments.
     */
    String getStaffUuid();

    /**
     * @return The length of the punishment in seconds. May be -1 to
     * represent permanent punishments or punishments with no length,
     * such as warns, kicks, unmutes, etc.
     */
    long getLength();

    /**
     * @return The timestamp of the punishment.
     */
    long getTimestamp();

    /**
     * @return The reason for the punishment.
     */
    String getReason();

    /**
     * @return Whether the punishment is active (true) or not (false)
     */
    boolean dataIsActive();

    /**
     * @return Whether the punishment was executed via the punishment
     * GUI (true) or not (false)
     */
    boolean isGuiPun();

    /**
     * @return The ID of the punishment that this punishment is a
     * reference to. Should only be set if the punishment is an
     * IP punishment, like an IP ban, IP mute, etc. Otherwise, it
     * should be -1.
     */
    int getReferencePunId();

    /**
     * @return Whether the notification has been sent for this
     * punishment (true) or not (false). Should only be set to true
     * if the punishment type is a warning.
     */
    boolean needsNotifSent();

    /**
     * @param id The ID of the punishment. Must be greater than 0.
     */
    void setId( int id );

    /**
     * @param punishmentTypeStr The type of punishment.
     */
    void setPunishmentTypeStr( String punishmentTypeStr );

    /**
     * @param playerUuid The UUID of the player who was punished.
     */
    void setPlayerUuid( String playerUuid );

    /**
     * @param player The player who was punished.
     */
    void setPlayer( OfflinePlayer player );

    /**
     * @param staffUuid The UUID of the staff member who punished
     *                  the player. May be "CONSOLE" to represent
     *                  console punishments.
     */
    void setStaffUuid( String staffUuid );

    /**
     * @param staff The staff member who punished the player.
     */
    void setStaff( OfflinePlayer staff );

    /**
     * @param length The length of the punishment in seconds.
     *               May be -1 to represent permanent punishments
     *               or punishments with no length, such as warns,
     *               kicks, unmutes, etc.
     */
    void setLength( long length );

    /**
     * @param timestamp The timestamp of the punishment.
     */
    void setTimestamp( long timestamp );

    /**
     * @param reason The reason for the punishment.
     */
    void setReason( String reason );

    /**
     * @param active Whether the punishment is active (true) or
     *               not (false).
     */
    void setActive( boolean active );

    /**
     * @param guiPun Whether the punishment was executed via the
     *              punishment GUI (true) or not (false)
     */
    void setGuiPun( boolean guiPun );

    /**
     * @param referencePunId The ID of the punishment that this
     *                        punishment is a reference to. Should
     *                        only be set if the punishment is an
     *                        IP punishment, like an IP ban, IP
     *                        mute, etc. Otherwise, it should be -1.
     */
    void setReferencePunId( int referencePunId );

    /**
     * @param needsNotifSent Whether the notification has been sent
     *                       for this punishment (true) or not
     *                       (false). Should only be set to true if
     *                       the punishment type is a warning.
     */
    void setNeedsNotifSent( boolean needsNotifSent );

    /**
     * I hope this is obvious.
     */
    @Override
    String toString();

    //
    // SQL Stuff
    //
//
//    @Override
//    String getSQLTypeName() throws SQLException;
//
//    @Override
//    void readSQL( SQLInput stream, String typeName ) throws SQLException;
//
//    @Override
//    void writeSQL( SQLOutput stream ) throws SQLException;
}