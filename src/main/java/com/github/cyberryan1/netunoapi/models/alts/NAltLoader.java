//package com.github.cyberryan1.netunoapi.models.alts;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//public interface NAltLoader {
//
//    /**
//     * Initializes the cache and loads all entries from the database
//     */
//    void initialize();
//
//    /**
//     * Loads a player into the cache (should only be ran when the player joins).
//     * @param uuid The {@link UUID} of the player to load
//     * @param ip The IP of the player to load
//     */
//    void loadPlayer( UUID uuid, String ip );
//
//    /**
//     * Searches for a group with the given ID
//     * @param groupId The ID of the group to search for
//     * @return An optional containing the group if it exists, or an empty optional if it does not
//     */
//    Optional<NAltGroup> searchByGroupId( int groupId );
//
//    /**
//     * Searches for a singular group that contains the given UUID
//     * @param uuid The UUID to search for
//     * @return An optional containing the group if it exists, or an empty optional if it does not
//     */
//    Optional<NAltGroup> searchByUuid( UUID uuid );
//
//    /**
//     * Alias for {@link #searchByUuid(UUID)}, but takes a string instead of a UUID
//     * @param uuid The UUID to search for
//     * @return An optional containing the group if it exists, or an empty optional if it does not
//     */
//    default Optional<NAltGroup> searchByUuid( String uuid ) {
//        return searchByUuid( UUID.fromString( uuid ) );
//    }
//
//    /**
//     * Searches for all groups that contain the given UUID
//     * @param uuid the UUID to search for
//     * @return A list of all groups that contain the given UUID
//     */
//    List<NAltGroup> searchManyByUuid( UUID uuid );
//
//    /**
//     * Alias for {@link #searchManyByUuid(UUID)}, but takes a string instead of a UUID
//     * @param uuid the UUID to search for
//     * @return A list of all groups that contain the given UUID
//     */
//    default List<NAltGroup> searchManyByUuid( String uuid ) {
//        return searchManyByUuid( UUID.fromString( uuid ) );
//    }
//
//    /**
//     * Searches for a singular group that contains the given IP address
//     * @param ip The IP address to search for
//     * @return An optional containing the group if it exists, or an empty optional if it does not
//     */
//    Optional<NAltGroup> searchByIp( String ip );
//
//    /**
//     * Searches for all groups that contain the given IP address
//     * @param ip The IP address to search for
//     * @return A list of all groups that contain the given IP address
//     */
//    List<NAltGroup> searchManyByIp( String ip );
//}