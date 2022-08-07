package com.github.cyberryan1.netunoapi.models.punishments;

public interface NPunishmentLoader {

    NPunishment createPunishment();

    NPrePunishment createPrePunishment( NPunishment punishment );
}