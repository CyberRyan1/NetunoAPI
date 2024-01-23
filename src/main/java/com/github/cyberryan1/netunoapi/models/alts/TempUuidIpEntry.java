package com.github.cyberryan1.netunoapi.models.alts;

import java.util.UUID;

public class TempUuidIpEntry {

    private final UUID uuid;
    private final String ip;
    private boolean saved;

    public TempUuidIpEntry( UUID uuid, String ip, boolean saved ) {
        this.uuid = uuid;
        this.ip = ip;
        this.saved = saved;
    }

    public boolean isSaved() { return saved; }

    public void setSaved( boolean saved ) { this.saved = saved; }

    public UUID getUuid() {
        return uuid;
    }

    public String getIp() {
        return ip;
    }
}