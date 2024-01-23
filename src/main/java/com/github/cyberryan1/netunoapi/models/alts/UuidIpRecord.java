package com.github.cyberryan1.netunoapi.models.alts;

import java.util.UUID;

public class UuidIpRecord {

    private final UUID uuid;
    private final String ip;
    private boolean saved;

    public UuidIpRecord( UUID uuid, String ip, boolean saved ) {
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

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        UuidIpRecord that = ( UuidIpRecord ) o;

        if ( !getUuid().equals( that.getUuid() ) ) return false;
        return getIp().equals( that.getIp() );
    }

    @Override
    public int hashCode() {
        int result = getUuid().hashCode();
        result = 31 * result + getIp().hashCode();
        return result;
    }
}