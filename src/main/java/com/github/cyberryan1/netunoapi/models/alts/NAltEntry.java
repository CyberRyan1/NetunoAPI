package com.github.cyberryan1.netunoapi.models.alts;

import java.util.UUID;

public class NAltEntry {
    private final String uuid;
    private final String ip;
    private int groupId;
    private boolean saved;

    public NAltEntry( String uuid, String ip, int groupId, boolean saved ) {
        this.uuid = uuid;
        this.ip = ip;
        this.groupId = groupId;
        this.saved = saved;
    }

    public NAltEntry( UUID uuid, String ip, int groupId, boolean saved ) {
        this.uuid = uuid.toString();
        this.ip = ip;
        this.groupId = groupId;
        this.saved = saved;
    }

    public String getUuid() {
        return uuid;
    }

    public String getIp() {
        return ip;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId( int groupId ) {
        this.groupId = groupId;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved( boolean saved ) {
        this.saved = saved;
    }

    public NAltEntry copy() {
        return new NAltEntry( uuid, ip, groupId, saved );
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        NAltEntry that = ( NAltEntry ) o;

        if ( getGroupId() != that.getGroupId() ) return false;
        if ( getUuid() != null ? !getUuid().equals( that.getUuid() ) : that.getUuid() != null ) return false;
        return getIp() != null ? getIp().equals( that.getIp() ) : that.getIp() == null;
    }

    @Override
    public int hashCode() {
        int result = getUuid() != null ? getUuid().hashCode() : 0;
        result = 31 * result + ( getIp() != null ? getIp().hashCode() : 0 );
        result = 31 * result + getGroupId();
        return result;
    }
}