package com.github.cyberryan1.netunoapi.models.alts;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TempIpEntry {

    private final Set<UUID> uuids = new HashSet<>();
    private final String ip;
    private boolean saved;

    public TempIpEntry( String ip, boolean saved ) {
        this.ip = ip;
        this.saved = saved;
    }

    public boolean isSaved() { return saved; }

    public void setSaved( boolean saved ) { this.saved = saved; }

    public Set<UUID> getUuids() {
        return uuids;
    }

    public String getIp() {
        return ip;
    }
}