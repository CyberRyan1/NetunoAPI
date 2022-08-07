package com.github.cyberryan1.netunoapi.models.punishments;

public interface NPunishmentLoader {

    /**
     * @return A new {@link NPunishment} object
     */
    NPunishment createPunishment();

    /**
     * Creates a {@link NPrePunishment} from the given {@link NPunishment}
     * @param punishment The {@link NPunishment} to create the {@link NPrePunishment} from
     * @return The created {@link NPrePunishment}
     */
    NPrePunishment createPrePunishment( NPunishment punishment );
}