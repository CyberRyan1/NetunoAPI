package com.github.cyberryan1.netunoapi.utils;

import com.github.cyberryan1.netunoapi.helpers.ANetunoPunishment;

import java.util.List;

public class PunishmentUtils {

    public static boolean anyActive( List<ANetunoPunishment> punishments ) {
        return punishments.stream().anyMatch( ANetunoPunishment::isActive );
    }
}